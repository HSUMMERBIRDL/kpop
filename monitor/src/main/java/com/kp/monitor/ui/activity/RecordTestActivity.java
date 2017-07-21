package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hl.foundation.library.utils.CThreadUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.kp.monitor.R;
import com.kp.monitor.data.bean.Record;
import com.kp.monitor.data.po.RecordFile;
import com.kp.monitor.service.MediaService;
import com.kp.monitor.service.RecordService;
import com.kp.monitor.service.handler.RecordCallBack;
import com.kp.monitor.ui.BaseAppActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.kp.monitor.service.MediaService;
//import com.kp.monitor.service.RecordService;
//import com.kp.monitor.service.handler.RecordCallBack;

/**
 * des: 录音测试界面
 * Created by HL
 * on 2017-06-20.
 */

public class RecordTestActivity extends BaseAppActivity {

    @Bind(R.id.btn_start_record)
    Button btnStartRecord;
    @Bind(R.id.txt_file_address)
    TextView txtFileAddress;
    @Bind(R.id.txt_file_address2)
    TextView txtFileAddress2;
    @Bind(R.id.btn_stop_record)
    Button btnStopRecord;
    @Bind(R.id.btn_start_play)
    Button btnStartPlay;
    @Bind(R.id.txt_play_address)
    TextView txtPlayAddress;
    @Bind(R.id.btn_stop_play)
    Button btnStopPlay;
    @Bind(R.id.btn_slect_record_num)
    Button btnSlectRecordNum;


    private RecordService recordService;
    private MediaService mediaService;

    private File theRecordFile;

    public static void startAction(Context context) {

        Intent intent = new Intent(context, RecordTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
//
        recordService = RecordService.getInstance();
        mediaService = MediaService.getInstance();


        CThreadUtils.runInBackground(new Runnable() {
            @Override
            public void run() {

                recordService.init(new RecordCallBack() {
                    @Override
                    public void onRecordStart(File file) {
                        if (null != file) {
                            txtFileAddress.setText("开始录音，将存在于地址：" + file.getAbsolutePath());
                        } else {
                            txtFileAddress.setText("开始录音，将存在于地址  无返回地址");
                        }
                    }

                    @Override
                    public void onRecordComplete(File file, String startTime) {
                        if (null != file) {

                            theRecordFile = file;

                            Record record = new Record();
                            record.setFile(file);
                            record.setStartTime(startTime);
//                    int randomIntBeteen = NumberUtils.getRandomIntBeteen(1, 9);
//                    record.setPhone("18664381895" + randomIntBeteen);
                            record.setPhone("18664381895");
                            String currentDate = TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMS);
                            record.setEndTime(currentDate);

                            RecordFile recordFile = RecordFile.createInitRecordFile(record);
                            RecordService.getInstance().saveAndUpdateRecordFile(recordFile);


                            txtFileAddress2.setText("停止录音，录音存放在地址：" + file.getAbsolutePath());
                        } else {
                            txtFileAddress2.setText("停止录音，录音存放在地址  无返回地址");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_record;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

        btnStartRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordService.startRecord();
            }
        });

        btnStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordService.stopRecord();
            }
        });

        btnStartPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaService.playVoice(theRecordFile);
            }
        });

        btnStopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaService.stopVoice();
            }
        });

        btnSlectRecordNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RecordService.getInstance().postUploadFileSize();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
