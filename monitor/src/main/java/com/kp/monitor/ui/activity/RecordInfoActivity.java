package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hl.foundation.library.utils.LogUtils;
import com.kp.monitor.R;
import com.kp.monitor.contract.RecordInfoContract;
import com.kp.monitor.data.vo.RecUpProInfoVo;
import com.kp.monitor.model.RecordInfoModel;
import com.kp.monitor.presenter.RecordInfoPresenter;
import com.kp.monitor.service.RecordService;
import com.kp.monitor.ui.BaseAppActivity;

import butterknife.Bind;

/**
 * des:录音文件信息界面  其实这个界面主要是上传文件界面 并且在该界面展示上传中的信息
 * Created by HL
 * on 2017-06-28.
 */

public class RecordInfoActivity extends BaseAppActivity<RecordInfoModel, RecordInfoPresenter> implements RecordInfoContract.View {

    @Bind(R.id.txt_info)
    TextView txtInfo;
    @Bind(R.id.txt_start_upload)
    TextView txtStartUpload;

    public static final String TOTAL_RECORD_FILE_SIZE = "total_record_file_size";

    public int totalRecordFileSize;

    @Bind(R.id.txt_reord)
    TextView txtReord;


    @Bind(R.id.txt_upload_test)
    TextView txtUploadTest;


    public static void startAction(Context context, int totalRecordFileSize) {

        Intent intent = new Intent(context, RecordInfoActivity.class);
        intent.putExtra(TOTAL_RECORD_FILE_SIZE, totalRecordFileSize);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {

        totalRecordFileSize = getIntent().getIntExtra(TOTAL_RECORD_FILE_SIZE, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_info;
    }

    @Override
    protected void initViews() {

        txtInfo.setText("一共有 " + totalRecordFileSize + "个文件需要上传");
    }

    @Override
    protected void initEvents() {

        txtStartUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLoading();
                mPresenter.startUploadFiles();
                updateUploadView(0);
            }
        });

        txtReord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordTestActivity.startAction(mContext);
            }
        });

        txtUploadTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RxBus.getInstance().post(Events.UPLOAD_FILE_SIZE_CHANGE, 2);
                RecordService.getInstance().postUploadFileSize();
//                RxBus.getInstance().post(Events.TEST,4);


            }
        });
    }

    /**
     * @param uploadStatue 0:  上传中
     *                     1： 全部上传成功  此时不要继续让他点击上传了  当然文件也应该都删除了
     *                     <p>
     *                     2： 全部上传过  但是只成功了一部分  还可以让他继续点
     */
    private void updateUploadView(int uploadStatue) {

        if (0 == uploadStatue) {  // 开始上传
            txtStartUpload.setEnabled(false);
        } else if (1 == uploadStatue) { // 上传完成 并不代表全部上传成功
            txtStartUpload.setEnabled(false);
        } else if (2 == uploadStatue) {
            txtStartUpload.setEnabled(true);
        }
    }

    @Override
    public void onRecordFilesUploadProgress(RecUpProInfoVo vo) {


        LogUtils.logi(TAG, "vo: " + vo);
        int totalNum = vo.getTotalNum();
        int finishNum = vo.getFinishNum();
        int successNum = vo.getSuccessNum();


        if (finishNum == totalRecordFileSize) {  //  代表都传完了
            dismissLoading();

            if (successNum == totalRecordFileSize) {  //  代表都成功了
                updateUploadView(1);
            } else {
                updateUploadView(2); // 代表没有都成功
            }
        }
        txtInfo.setText("一共有 " + vo.getTotalNum() + "个文件需要上传  已经上传" + vo.getSuccessNum() + "/" + totalRecordFileSize);
    }

    @Override
    public void onNotUploadFileUpdate(int currentNum) {

        // TODO: 2017-07-04  以后可能会用到  暂时不对其做任何处理
//        txtInfo.setText("一共有 " + currentNum + "个文件需要上传");
    }


}
