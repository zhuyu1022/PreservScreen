package com.hoperun.veilstorage.net;

import android.os.Handler;

import org.json.JSONObject;

public class RequestServiceImpl {

    /**
     * 获取新版本
     *
     * @param handler
     * @param requestType
     */
    public static void getNewVersion(Handler handler, int requestType) {

        JSONObject inputInfo = new JSONObject();

        String methodName = "GetUpgradeInfoByPreserveShop";

        IServiceImpl.callWebMethod(handler, requestType, inputInfo, methodName);
    }



/**************************************************************分割线，下面是2019年12月24日开始新做的保全呼叫功能******************************************************************************/


    /**
     * 	保全大屏查询
     * @param handler
     * @param requestType
     */
    public static void BQGL_GetAllBQXX(Handler handler, int requestType) {

        JSONObject inputInfo = new JSONObject();
        try {

        } catch (Exception e) {

        }
        String methodName = "BQGL_GetAllBQXX";

        IServiceImpl.callWebMethod(handler, requestType, inputInfo, methodName);
    }


}
