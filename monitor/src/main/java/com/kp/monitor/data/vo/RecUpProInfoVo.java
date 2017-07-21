package com.kp.monitor.data.vo;

/**
 * des: 录音上传过程中情况 对应于 RecordInfoActivity 界面
 * Created by HL
 * on 2017-06-28.
 */

public class RecUpProInfoVo {

    private int totalNum; // 总共需要上传的文件个数（这个数目并不随着上传过程而变化）

    private int notStartNum; // 还未上传的文件个数 （事实上一开始就会启动全部上传）
    private int hasStartNum; // 开始了上传的文件个数

    private int notFinishNum; // 已经上传过的文件但是还没有上传完  相当于上传进行中的文件个数
    private int finishNum; // 已经上传过并且返回了的文件个数  注意 是要返回（是指发送了上传请求 并且返回  但是可能成功也可能失败 的文件个数）

    private int successNum; // 上传成功的文件个数
    private int failureNum; // 上传失败的文件个数  上传了 但是上传失败

//    totalNum = notStartNum + hasStartNum
//    hasStartNum = notFinishNum +　finishNum
//    finishNum = successNum + failureNum；


//    如果 totalNum = finishNum 代表上传结束


    public int getNotFinishNum() {
        return notFinishNum;
    }

    public void setNotFinishNum(int notFinishNum) {
        this.notFinishNum = notFinishNum;
    }

    public int getHasStartNum() {
        return hasStartNum;
    }

    public void setHasStartNum(int hasStartNum) {
        this.hasStartNum = hasStartNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getNotStartNum() {
        return notStartNum;
    }

    public void setNotStartNum(int notStartNum) {
        this.notStartNum = notStartNum;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(int failureNum) {
        this.failureNum = failureNum;
    }


    @Override
    public String toString() {
        return "RecUpProInfoVo{" +
                "totalNum=" + totalNum +
                ", notStartNum=" + notStartNum +
                ", hasStartNum=" + hasStartNum +
                ", notFinishNum=" + notFinishNum +
                ", finishNum=" + finishNum +
                ", successNum=" + successNum +
                ", failureNum=" + failureNum +
                '}';
    }
}
