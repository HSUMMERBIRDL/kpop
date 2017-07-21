package com.kp.monitor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hl.foundation.library.manager.InterfaceMgr;
import com.hl.foundation.library.utils.NetWorkUtils;
import com.hl.foundation.library.widget.ToastUitl;
import com.kp.monitor.R;
import com.kp.monitor.contract.MsgDetailContract;
import com.kp.monitor.data.vo.MessageItemVO;
import com.kp.monitor.data.vo.MessageVO;
import com.kp.monitor.data.vo.MsgVo;
import com.kp.monitor.model.MsgDetailModel;
import com.kp.monitor.presenter.MsgDetailPresenter;
import com.kp.monitor.ui.BaseAppActivity;

import butterknife.Bind;

/**
 * des:
 * Created by HL
 * on 2017-05-11.
 */

public class MsgDetailActivity extends BaseAppActivity<MsgDetailModel,
        MsgDetailPresenter> implements MsgDetailContract.View, View.OnClickListener {

    private String MSG_ItemVO = "MSG_ItemVO";
    private MessageItemVO messageItemVO;
    private int position;

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.read_message)
    TextView readMessage;


    @Bind(R.id.msg_send_time)
    TextView msgTime;

    @Bind(R.id.msg_status)
    TextView msgStatus;

    @Bind(R.id.send_person)
    TextView sendPerson;

    @Bind(R.id.msg_title)
    TextView msgTitle;

    public static void startAction(Context context, MessageItemVO messageItemVO,int position) {
        Intent intent = new Intent(context, MsgDetailActivity.class);
        intent.putExtra("MSG_ItemVO", messageItemVO);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        messageItemVO = intent.getExtras().getParcelable(MSG_ItemVO);
        position = intent.getIntExtra("position",-1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_msg;
    }

    @Override
    protected void initViews() {
        mPresenter.sendGetMsgDetail(messageItemVO.messageId);
        msgTime.setText(messageItemVO.time);
        sendPerson.setText(messageItemVO.createUserName);
        msgStatus.setText(messageItemVO.status);
        msgTitle.setText(messageItemVO.newsTitle);
        webview.requestFocus();
        webview.setHorizontalScrollBarEnabled(false);
        webview.setVerticalScrollBarEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        if (NetWorkUtils.isNetConnected(this)) {
            webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        //@TargetApi(11)
        webview.setOverScrollMode(View.OVER_SCROLL_NEVER);

        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 支持多窗口
        webview.getSettings().setSupportMultipleWindows(true);
        // 开启 DOM storage API 功能
        webview.getSettings().setDomStorageEnabled(true);
        // 开启 Application Caches 功能
        webview.getSettings().setAppCacheEnabled(true);

        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //就是这句
        if (TextUtils.equals(messageItemVO.status, "已读")) {
            readMessage.setVisibility(View.GONE);
        } else {
            readMessage.setVisibility(View.VISIBLE);
            msgStatus.setTextColor(getResources().getColor(R.color.red));
            readMessage.setText("确认已读");
        }
    }

    @Override
    protected void initEvents() {
        readMessage.setOnClickListener(this);
    }

    @Override
    public void onGetMsgDetailComplete(MessageVO messageVO) {

        webview.loadDataWithBaseURL(null, messageVO.content, "text/html", "utf-8", null);
    }

    @Override
    public void onGetMsgStutasComplete(MsgVo msgVo) {
        if (null != msgVo && msgVo.code == 200) {
            readMessage.setText("已读");
            msgStatus.setText("已读");
            readMessage.setVisibility(View.GONE);
            msgStatus.setTextColor(getResources().getColor(R.color.colorTxtGray));
            MsgListActivity.startAction(this);
            InterfaceMgr.getInstance().changeMsg(position);
            finish();
        } else {
            ToastUitl.show(msgVo.msg, 0);
        }
    }

    @Override
    public void onClick(View view) {
        mPresenter.sendReadMessage(messageItemVO.messageId);
    }

    @Override
    public void onBackPressed() {
        MsgListActivity.startAction(this);
        finish();
    }
}
