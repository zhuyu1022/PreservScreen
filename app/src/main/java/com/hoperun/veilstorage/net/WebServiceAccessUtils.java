package com.hoperun.veilstorage.net;

import android.util.Log;

import com.hoperun.veilstorage.common.Setting;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * 调用 webservice �?
 * 
 * @author wang_donghai
 * 
 */
public class WebServiceAccessUtils {


	private static String TAG = "WebServiceAccessUtils";

	
	
	
	private static String url="";
	private static int timeout;
	// private static String nameSpace = "http://tempuri.org/";
	//名空间
	private static String nameSpace = "http://www.FKM.PADService/";
	//服务名
	private static String service="IFKMService/";

	public static String callWebMethod(Object inParam, String methodName) {
		String result = "";
		JSONObject rJson = new JSONObject();
		try {
			if (inParam != null) {
				rJson.put("inParam", inParam);
			}
			rJson.put("methodName", methodName);

			result = call("callWebMethod", rJson.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}



	/**
	 * 调用服务器的方法
	 * 
	 * @param methodName
	 * @param param
	 * @return
	 */
	public static String call(final String methodName, String param) {
		 url = Setting.getUrl();
		timeout = Setting.getSocketTimeout();
		SoapObject request = new SoapObject(nameSpace, methodName);
		if (param != null) {
			request.addProperty("arg0", param);

			Log.d(TAG, "OnWebSercieRequest **** param=" + param);
		}

		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		final HttpTransportSE ht = new HttpTransportSE(url, timeout);
		ht.debug = true;

		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() {
				String OPT_FLAG = "0";
				String MSG_INFO = "";
				try {
					ht.call(nameSpace +service+ methodName, envelope);
					if (envelope.getResponse() != null) {
						OPT_FLAG = "0";
						MSG_INFO = envelope.getResponse().toString();

						Log.d(TAG, "OnWebSercieRequest **** MSG_INFO=" + MSG_INFO);
					}
				} catch (IOException e) {
					OPT_FLAG = "1";
					MSG_INFO = e.toString();
				} catch (XmlPullParserException e) {
					OPT_FLAG = "1";
					MSG_INFO = e.toString();
				} catch (Exception e) {
					OPT_FLAG = "1";
					MSG_INFO = e.toString();
				}

				String result = "";
				JSONObject retJson = new JSONObject();
				try {
					retJson.put(Setting.OPT_FLAG, OPT_FLAG);
					retJson.put(Setting.MSG_INFO, MSG_INFO);

					result = retJson.toString();
				} catch (Exception e) {
					// TODO: handle exception
					result = "{\"OPT_FLAG\":1,\"MSG_INFO\":\"" + e.toString() + "\"}";
				}
				if (OPT_FLAG.equals("1")) {
					Log.d(TAG, "OnWebSercieRequest **** MSG_INFO=" + MSG_INFO);
				}
				return result;
			}
		});
		new Thread(future).start();
		try {
			return future.get();
		} catch (InterruptedException e) {
			return "{\"OPT_FLAG\":1,\"MSG_INFO\":\"" + e.toString() + "\"}";
		} catch (ExecutionException e) {
			return "{\"OPT_FLAG\":1,\"MSG_INFO\":\"" + e.toString() + "\"}";
		}
	}
}
