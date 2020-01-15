/*
 * File name:  PMIPActivit.java
 * Copyright:  Copyright (c) 2006-2013 hoperun Inc,  All rights reserved
 * Description:  <描述>
 * Author:  ren_qiujing
 * Last modified date:  2013-9-12
 * Version:  <版本编号>
 * Edit history:  <修改内容><修改人>
 */
package com.hoperun.veilstorage.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.hoperun.veilstorage.view.WaitDialog;

/**
 * 2019-12-30
 * ZHUYU
 * Activity封装
 */
public abstract class BaseActivity extends AppCompatActivity {
    private WaitDialog mWaitDialog;

    protected String TAG = "";

    protected boolean isActivityDestroyed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TAG = this.getClass().getName();
        // 设置界面无标�?
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置界面常亮
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mWaitDialog = new WaitDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutID());
        initViewAndEvent();
        initData();

    }


    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID != 0) {
            super.setContentView(layoutResID);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
    }


    /**
     * 显示加载圈圈...
     */
    public void showWait() {
        mWaitDialog.show();
    }

    /**
     * 停掉加载圈圈...
     */
    public void stopWait() {
        mWaitDialog.dismiss();
    }

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    @LayoutRes
    protected abstract int getContentViewLayoutID();

    /**
     * init all views and add events
     * before initData()
     */
    protected abstract void initViewAndEvent();

    /**
     * init data
     * after initViewAndEvent()
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {

        isActivityDestroyed = true;
        super.onDestroy();
    }

    @SuppressLint("HandlerLeak")
    protected Handler mMIPHandler = new Handler() {

        /*
         * (non-Javadoc)
         *
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            int requestType = msg.what;
            int errorCode = msg.arg1;

            Object retObj = msg.obj;
            try {
                if (errorCode == 0) {

                    onPostHandle(requestType, retObj, true);

                } else {
                    onPostHandle(requestType, retObj, false);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }

    };

    abstract public void onPostHandle(int requestType, Object objBody,
                                      boolean success) throws Exception;
}
