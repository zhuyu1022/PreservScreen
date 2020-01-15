/*
 * File name:  BaseFragment.java
 * Copyright:  Copyright (c) 2006-2014 hoperun Inc,  All rights reserved
 * Description:  <描述>
 * Author:  ren_qiujing
 * Last modified date:  2014-5-5
 * Version:  <版本编号>
 * Edit history:  <修改内容><修改人>
 */
package com.hoperun.veilstorage.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * <一句话功能简述>
 *
 * @author ren_qiujing
 * @Description<功能详细描述>
 * @Version [版本号, 2014-5-5]
 */
public abstract class BaseFragment extends Fragment {

    public abstract void onKYScanListen(String resetStr);

    public abstract Object backValue();

    public abstract boolean onKeyBackDown();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutID = getContentViewLayoutID();
        if (layoutID != 0) {
            return inflater.inflate(layoutID, null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewAndEvent();
        initData();
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

            if (errorCode == 0) {
                onPostHandle(requestType, retObj, true);
            } else {
                onPostHandle(requestType, retObj, false);
            }
            super.handleMessage(msg);
        }

    };

    abstract public void onPostHandle(int requestType, Object objBody,
                                      boolean error);

}
