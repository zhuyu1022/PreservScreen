package com.hoperun.veilstorage.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hoperun.veilstorage.common.Setting;

import org.json.JSONException;
import org.json.JSONObject;

public class IServiceImpl {

    public static final String TAG = "IServiceImpl";

    public static final int LOGIN_R = 0;




    public static final int GET_NEW_VERSION = 1;


    public static final int BQGL_GetAllBQXX = 2;


    public static void callWebMethod(final Handler mHandler,
                                     final int requestType, final Object inputInfo, final String methodName) {
        new Thread() {
            @Override
            public void run() {

                //String jsonStr = callRequest(requestType, inputInfo);

                String jsonStr = WebServiceAccessUtils.call(methodName, inputInfo.toString());

                Message msg = Message.obtain();
                msg.what = requestType;
                msg.arg1 = 1;
                try {
                    JSONObject json = new JSONObject(jsonStr);

                    String falg = json.optString(Setting.OPT_FLAG);
                    String info = json.optString("MSG_INFO");

                    Log.d(TAG, "OnWebSercieRequest **** info=" + info);

                    if (falg.equals("0")) {
                        msg.arg1 = 0;
                        if (info != null && info instanceof String
                                && info.startsWith("SUCCESS:")) {
                            info = info.substring("SUCCESS:".length(),
                                    info.length());
                        }
                    } else {
                        msg.arg1 = 1;
                    }
                    if (info != null) {
                        msg.obj = info;
                    }

                    if (info != null && info instanceof String
                            && info.contains("FAILED")) {
                        msg.arg1 = 1;
                        msg.obj = info;
                    }
                } catch (JSONException e) {
                    msg.arg1 = 1;
                    msg.obj = e.toString();
                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
