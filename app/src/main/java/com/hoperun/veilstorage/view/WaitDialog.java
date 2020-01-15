package com.hoperun.veilstorage.view;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hoperun.veilstorage.R;


public class WaitDialog {

    private Dialog mDialog = null;

    private Context mContext;

    public WaitDialog(Context context) {
        mContext = context;
        mDialog = createLoadingDialog(mContext);
    }

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @return
     */
    @SuppressLint("ResourceType")
    public Dialog createLoadingDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.waitdialog, null);// 得到加载view

        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.loading);

        ImageView mImg = (ImageView) v.findViewById(R.id.waitdialog_img);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.drawable.anim_wait);

        mImg.startAnimation(hyperspaceJumpAnimation);

        Dialog loadingDialog = new Dialog(context, R.style.Mip_loading_dialog);// 创建自定义样式dialog

        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        loadingDialog.setCanceledOnTouchOutside(false);

        return loadingDialog;

    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public boolean isDialogShowing() {
        if (mDialog != null) {
            return mDialog.isShowing();
        }
        return false;
    }

    public void setOndismissListener(OnDismissListener listener) {
        if (mDialog != null) {
            mDialog.setOnDismissListener(listener);
        }
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCancelEable(boolean flag) {
        if (mDialog != null) {
            mDialog.setCancelable(flag);
        }
    }
}
