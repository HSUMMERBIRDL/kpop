package com.kp.monitor.service;

import android.media.MediaRecorder;
import android.util.Log;

import com.hl.foundation.frame.net.FileRequestBody;
import com.hl.foundation.frame.net.RequestTypeBuilder;
import com.hl.foundation.frame.net.UpLoadCallback;
import com.hl.foundation.library.rx.RxBus;
import com.hl.foundation.library.utils.CollectionUtils;
import com.hl.foundation.library.utils.FileUtil;
import com.hl.foundation.library.utils.LogUtils;
import com.hl.foundation.library.utils.NetWorkUtils;
import com.hl.foundation.library.utils.StringUtils;
import com.hl.foundation.library.utils.TimeUtil;
import com.hl.foundation.library.widget.ToastUitl;
import com.kp.monitor.basis.app.AppAplication;
import com.kp.monitor.basis.db.DBUtils;
import com.kp.monitor.basis.db.RecordFileDao;
import com.kp.monitor.basis.http.Api;
import com.kp.monitor.basis.http.ApiConstants;
import com.kp.monitor.basis.http.FileUploadConstants;
import com.kp.monitor.basis.http.HttpSubscriber;
import com.kp.monitor.data.event.Events;
import com.kp.monitor.data.po.RecordFile;
import com.kp.monitor.data.vo.RecUpProInfoVo;
import com.kp.monitor.service.handler.DTOFunc1;
import com.kp.monitor.service.handler.RecUpProInfoCallBack;
import com.kp.monitor.service.handler.RecordCallBack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 简单参考：http://blog.csdn.net/cxf7394373/article/details/8313980
 * des: 录音
 * Created by HL
 * on 2017-06-19.
 */

public class RecordService {

    private static final String TAG = RecordService.class.getSimpleName();
    private static RecordService INSTANCE;
    private RecUpProInfoVo recUpProInfoVo = new RecUpProInfoVo();

    private RecordCallBack recordCallBack;  // 录音回调监听

    private static final String RECORD_FOLDER_NAME = "KpRecord"; // 录音文件存的文件夹
    private String recordFilePath = ""; // 录音文件存放位置 见init方法
    private MediaRecorder mediaRecorder;
    private File recordFile = null;

    public static RecordService getInstance() {
        if (null == INSTANCE) {
            synchronized (RecordService.class) {
                if (null == INSTANCE) {
                    INSTANCE = new RecordService();
                }
            }
        }
        return INSTANCE;
    }


    public void init(RecordCallBack callBack) {
//        recordFilePath = FileUtil.getCacheFolder(RECORD_FOLDER_NAME).getAbsolutePath();
        recordFilePath = FileUtil.getCacheFolder().getAbsolutePath();
        this.recordCallBack = callBack;
    }


    /**
     * 获取存放文件
     */
    private File getTheRecordFile() {
        String parent = recordFilePath;
        File file = new File(parent, TimeUtil.getCurrentDay3() + ".mp3");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 1: 查询数据库是否有需要上传的文件
     * 2：如果有则启动上传
     */
    public void startUploadFile(RecUpProInfoCallBack callBack) {


        updateRecUpProInfoVo(0);

        if(null != callBack){
            callBack.onRecordUploadProgress(recUpProInfoVo);
        }

        if (!NetWorkUtils.isNetConnected(AppAplication.getAppContext())) {

            ToastUitl.showLong("当前无网络，无法上传");
            return;
        }

        List<RecordFile> recordFiles = selectAllEnsureRecords();
        if (CollectionUtils.isNullOrEmpty(recordFiles)) {

            // todo 应该发出通知  使得界面的size 变成0  并且通知客户所有的文件都已上传完成
            return;
        }

        for (int i = 0; i < recordFiles.size(); i++) {

            final RecordFile recordFile = recordFiles.get(i);
            uploadFile(recordFile,callBack);
        }

//        CThreadUtils.runInBackground(new Runnable() {
//            @Override
//            public void run() {
//                List<RecordFile> recordFiles = selectAllEnsureRecords();
//                if(CollectionUtils.isNullOrEmpty(recordFiles)){
//
//                    // todo 应该发出通知  使得界面的size 变成0  并且通知客户所有的文件都已上传完成
//                    return;
//                }
//
//                for (int i = 0; i < recordFiles.size(); i++) {
//
//                    final RecordFile recordFile = recordFiles.get(i);
//                    uploadFile(recordFile);
//                }
//            }
//        });
    }


//    public static int uploadFileSize = 0; // 需要上传的文件个数


    /**
     * 发送上传文件的个数
     */

    public void postUploadFileSize() {

        //  每一次增加和删除都会引起这个值的变化  所有也只需要在增加和删除方法中去执行该方法
        int uploadFileSize = getUploadFileSize();
        // TODO: 2017-06-26   post到相关界面


        RxBus.getInstance().post(Events.UPLOAD_FILE_SIZE_CHANGE, uploadFileSize);

    }


    /************************************************************
     * 录音操作
     *************************************************************/

    private String recordStartTime;

    /**
     * 启动录音
     */
    public void startRecord() {

        File recordFile = getTheRecordFile();
        if (null != recordFile) {
            mediaRecorder = new MediaRecorder();
            if (prepareRecord(mediaRecorder, recordFile)) {
                this.recordFile = recordFile;
                recordStartTime = TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMS);
                if (null != recordCallBack) {
                    recordCallBack.onRecordStart(recordFile);
                }
            } else {
                LogUtils.loge(TAG, "录音准备失败");
            }
        } else {
            LogUtils.loge(TAG, "文件获取失败");
        }
    }

    /**
     * 结束录音
     */
    public void stopRecord() {

        try {
            if (null != mediaRecorder) {
                mediaRecorder.stop();
                mediaRecorder.release();
            }
            mediaRecorder = null;

            if (null != recordCallBack) {
                recordCallBack.onRecordComplete(recordFile, recordStartTime);
            }
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        }
    }


    /**
     * 录音文件
     *
     * @param mr
     * @param file 录音文件
     * @return
     */
    private boolean prepareRecord(MediaRecorder mr, File file) {

        boolean isPrepare = false;

        try {
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            mr.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mr.setOutputFile(file.getAbsolutePath());
            mr.prepare();
            mr.start();
            isPrepare = true;
        } catch (Exception e) {
            LogUtils.loge(TAG, e);
        }

        return isPrepare;
    }


    public static RecordFile createRecordFile(RecordFile recordFile) {

//        recordFile.setFileId(StringUtils.getUUID());
        recordFile.setStartTime(TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMS_f));

        return recordFile;

    }


    /********************************************************************************
     * 录音文件操作
     ***********************************************************/

    public void deleteFile(String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }


    /**************************************************************************        数据库操作       ******************************************************************************/

    /**
     * 插入或更新记录
     *
     * @param recordFile
     */
    public void saveAndUpdateRecordFile(RecordFile recordFile) {

        if (null == recordFile) {
            return;
        }
        String fileId = recordFile.getFileId();
        if (StringUtils.isEmpty(fileId)) {
            return;
        }

        boolean b = isTheRecordInRecordTable(fileId);
        if (b) {
            DBUtils.getRecordFileDao().update(recordFile);
        } else {
            DBUtils.getRecordFileDao().insert(recordFile);
        }

        postUploadFileSize();
    }

    /**
     * 删除录音文件
     *
     * @param fileId
     */
    public void deleteRecordFile(String fileId) {

        if (StringUtils.isEmpty(fileId)) {
            return;
        }
        DBUtils.getRecordFileDao().deleteByKey(fileId);
        postUploadFileSize();
    }

    /**
     * 通过ID查找录音文件
     *
     * @param fileId
     * @return
     */
    public RecordFile selectRecordById(String fileId) {

        if (StringUtils.isEmpty(fileId)) {
            return null;
        }
        RecordFile recordFile = DBUtils.getRecordFileDao().queryBuilder().where(RecordFileDao.Properties.FileId.eq(fileId)).unique();
        return recordFile;
    }

    /**
     * 查询所有录音文件
     *
     * @return
     */
    public List<RecordFile> selectAllRecords() {

        List<RecordFile> list = DBUtils.getRecordFileDao().queryBuilder().list();
        return list;
    }

    /**
     * 获取所有必定需要上传的文件 不包括： 1 不需要的（其实已经被删除了） 2 和不确定的待定的
     *
     * @return
     */
    public List<RecordFile> selectAllEnsureRecords() {

        List<RecordFile> recordFiles = selectAllRecords();
        if (!CollectionUtils.isNullOrEmpty(recordFiles)) {
            for (int i = 0; i < recordFiles.size(); i++) {

                RecordFile recordFile = recordFiles.get(i);

                if (1 != recordFile.getNeedUploadStatue()) {
                    recordFiles.remove(i);
                }
            }
        }
        return recordFiles;
    }

    /**
     * 更新已经上传的长度 即开始上传的地方
     *
     * @param fileId
     * @param hasUploadLength
     */
    public void updateHasUploadLength(String fileId, long hasUploadLength) {


        RecordFile recordFile = selectRecordById(fileId);

        if (null != recordFile) {
            recordFile.setHasUploadLength(hasUploadLength);
        }
        DBUtils.getRecordFileDao().update(recordFile);
    }


//    public List<RecordFile> selectRecords(int start,int size){
//
//        List<RecordFile> list = DBUtils.getRecordFileDao().queryBuilder().list();

//        return list;
//    }

    /**
     * 判断某个文件记录是否已经存在于表中
     *
     * @param fileId
     * @return
     */
    private boolean isTheRecordInRecordTable(String fileId) {

        RecordFileDao recordFileDao = DBUtils.getRecordFileDao();
        RecordFile recordFile = recordFileDao.queryBuilder().where(RecordFileDao.Properties.FileId.eq(fileId)).unique();
        if (null != recordFile) {
            return true;
        }
        return false;
    }


    /**
     * 获取需要上传文件的个数
     * 私有  如果需要获取数值 请调用
     *
     * @return
     */
    private int getUploadFileSize() {

        List<RecordFile> recordFiles = selectAllRecords();
        int needUploadFileSize = 0;
        if (CollectionUtils.isNullOrEmpty(recordFiles)) {
            return needUploadFileSize;
        } else {

            for (int i = 0; i < recordFiles.size(); i++) {

                RecordFile recordFile = recordFiles.get(i);

                if (1 == recordFile.getNeedUploadStatue()) {
                    needUploadFileSize++;
                }
            }

            return needUploadFileSize;
        }
    }

    /**
     * 是否有需要上传的文件
     *
     * @return
     */
    public boolean isHaveUploadFile() {

        if (getUploadFileSize() > 0) {
            return true;
        }
        return false;
    }

    /************
     * 上传操作
     ********************/

    public void uploadFile(RecordFile recordFile,RecUpProInfoCallBack callBack) {

        String url = ApiConstants.FILE_UPLOAD_URL;

        String phone = recordFile.getPhone();
        String fileId = recordFile.getFileId();

        String filePath = recordFile.getFilePath();
        File file = new File(recordFile.getFilePath());


        Map<String, String> descriptions = new HashMap<>();
        descriptions.put(ApiConstants.RECORD_PHONE, phone);
        descriptions.put(ApiConstants.RECORD_FILE_ID, fileId);
        descriptions.put(ApiConstants.RECORD_START_TIME, recordFile.getStartTime());
        descriptions.put(ApiConstants.RECORD_END_TIME, recordFile.getEndTime());

        if (null != file) {
             uploadFile(url, fileId, filePath, descriptions, file,callBack);
        } else {
            LogUtils.loge(TAG, filePath + "下的file不存在");
        }
    }


    /**
     * 上传单个文件 多个description
     *
     * @param url
     * @param descriptions
     * @param file
     */
    public void uploadFile(String url, final String fileId, final String fileLocalPath, Map<String, String> descriptions, File file, final RecUpProInfoCallBack callBack) {

        Map<String, RequestBody> partMap = new HashMap<>();
        String phone = "";
        for (String key : descriptions.keySet()) {
            String value = descriptions.get(key);
            if (StringUtils.equals(FileUploadConstants.PHONE, key)) {
                phone = value;
            }
            RequestBody descriptionRequestBody = RequestTypeBuilder.createRequestBody(value);
            partMap.put(key, descriptionRequestBody);
        }

        String tag = fileId + phone;


        final long[] hasUploadLength = {0};
        FileRequestBody fileRequestBody = RequestTypeBuilder.createFileRequestBody(tag, RequestTypeBuilder.createRequestBody(file), new UpLoadCallback() {
            @Override
            public void onProgress(Object tag, long bytesWritten, int progress, long speed, boolean done) {


                hasUploadLength[0] = bytesWritten;

                Log.i(TAG, "tag: " + tag.toString());
                Log.i(TAG, "bytesWritten: " + bytesWritten);
                Log.i(TAG, "progress: " + progress);
                Log.i(TAG, "speed: " + speed);
                Log.i(TAG, "done: " + done);
                Log.i(TAG, "_________________________________________________________");
            }
        });

      /*  传这个类型有问题  不知道是不是retrofit版本的问题*/
        MultipartBody.Part part = RequestTypeBuilder.createMultipartBodyPart("file", file.getName(), fileRequestBody);
        Api.getApiService().uploadFlie(partMap,part)
                .map(new DTOFunc1<>())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                        // 上传成功  删除数据记录  并且删除文件
                        deleteRecordFile(fileId);

                        deleteFile(fileLocalPath);
                        Log.i(TAG, "doOnNext: " + hasUploadLength[0]);
                        updateRecUpProInfoVo(1);
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // 上传失败  更新上传的起始位置
                        updateHasUploadLength(fileId, hasUploadLength[0]);
                        updateRecUpProInfoVo(2);

                        Log.i(TAG, "doOnError: " + hasUploadLength[0]);

                    }
                })
                .map(new Func1<Object, RecUpProInfoVo>() {
                    @Override
                    public RecUpProInfoVo call(Object o) {
                        return recUpProInfoVo;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<RecUpProInfoVo>() {
                    @Override
                    protected void _onNext(RecUpProInfoVo recUpProInfoVo) {
                        Log.i(TAG, "_onNext: ");
                        if(null != callBack){
                            callBack.onRecordUploadProgress(recUpProInfoVo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.i(TAG, "onError: ");
                        if(null != callBack){
                            callBack.onRecordUploadProgress(recUpProInfoVo);
                        }
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }
                });

    }

    /**
     * @param type 改变方式
     *             0： 初始的时候  还未开始上传
     *             1：上传成功一个
     *             2：上传失败一个
     */
    private void updateRecUpProInfoVo(int type) {

        synchronized (recUpProInfoVo) {

            //  一对开始结束的顺序则意味着只有一个线程进入 否则就有问题
            LogUtils.logi("updateRecUpProInfoVo", "------开始-----");

            int uploadFileSize = getUploadFileSize();
            recUpProInfoVo.setTotalNum(uploadFileSize);

            if (0 == type) {

//                recUpProInfoVo.setNotStartNum(uploadFileSize);
                recUpProInfoVo.setFinishNum(0);
                recUpProInfoVo.setSuccessNum(0);
                recUpProInfoVo.setFailureNum(0);
                recUpProInfoVo.setNotFinishNum(uploadFileSize);

            } else if (1 == type) {  // 上传成功

                // 完成 增加1 成功增加1  没完成减1  其余不变

                recUpProInfoVo.setFinishNum(recUpProInfoVo.getFinishNum() + 1);
                recUpProInfoVo.setSuccessNum(recUpProInfoVo.getSuccessNum() + 1);
                recUpProInfoVo.setNotFinishNum(recUpProInfoVo.getNotFinishNum() - 1);
            } else if (2 == type) {  // 上传失败

                // 完成+1  失败+1 没完成-1  其余布标
                recUpProInfoVo.setFinishNum(recUpProInfoVo.getFinishNum() + 1);
                recUpProInfoVo.setFailureNum(recUpProInfoVo.getSuccessNum() + 1);
                recUpProInfoVo.setNotFinishNum(recUpProInfoVo.getNotFinishNum() - 1);
            }

            LogUtils.logi("updateRecUpProInfoVo", "------结束-----");
        }
    }
}
