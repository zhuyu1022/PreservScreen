package com.hoperun.veilstorage.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

abstract public class CustomRelativeLayout extends RelativeLayout {

	public CustomRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

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
