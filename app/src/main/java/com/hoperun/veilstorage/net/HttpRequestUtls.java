package com.hoperun.veilstorage.net;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtls {
	private static String TAG = "HttpRequestUtls";

	// private void requestGet(String requestUrl) {
	// try {
	// // String baseUrl = "https://xxx.com/getUsers?";
	// // StringBuilder tempParams = new StringBuilder();
	// // int pos = 0;
	// // for (String key : paramsMap.keySet()) {
	// // if (pos > 0) {
	// // tempParams.append("&");
	// // }
	// // tempParams.append(String.format("%s=%s", key,
	// // URLEncoder.encode(paramsMap.get(key), "utf-8")));
	// // pos++;
	// // }
	// // String requestUrl = baseUrl + tempParams.toString();
	// // 新建一个URL对象
	// URL url = new URL(requestUrl);
	// // 打开一个HttpURLConnection连接
	// HttpURLConnection urlConn = (HttpURLConnection) url
	// .openConnection();
	// // 设置连接主机超时时间
	// urlConn.setConnectTimeout(5 * 1000);
	// // 设置从主机读取数据超时
	// urlConn.setReadTimeout(5 * 1000);
	// // 设置是否使用缓存 默认是true
	// urlConn.setUseCaches(true);
	// // 设置为Post请求
	// urlConn.setRequestMethod("GET");
	// // urlConn设置请求头信息
	// // 设置请求中的媒体类型信息。
	// urlConn.setRequestProperty("Content-Type", "application/json");
	// // 设置客户端与服务连接类型
	// urlConn.addRequestProperty("Connection", "Keep-Alive");
	// // 开始连接
	// urlConn.connect();
	// // 判断请求是否成功
	// if (urlConn.getResponseCode() == 200) {
	// // 获取返回的数据
	// String result = streamToString(urlConn.getInputStream());
	// Log.e(TAG, "Get方式请求成功，result--->" + result);
	// } else {
	// Log.e(TAG, "Get方式请求失败");
	// }
	// // 关闭连接
	// urlConn.disconnect();
	// } catch (Exception e) {
	// Log.e(TAG, e.toString());
	// }
	// }

	public static boolean downloadFile(String fileUrl, String filepath) {
		try {
			// 新建一个URL对象
			URL url = new URL(fileUrl);
			// 打开一个HttpURLConnection连接
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接主机超时时间
			urlConn.setConnectTimeout(5 * 1000);
			// 设置从主机读取数据超时
			urlConn.setReadTimeout(5 * 1000);
			// 设置是否使用缓存 默认是true
			urlConn.setUseCaches(true);
			// 设置为Post请求
			urlConn.setRequestMethod("GET");
			// urlConn设置请求头信息
			// 设置请求中的媒体类型信息。
			urlConn.setRequestProperty("Content-Type", "application/json");
			// 设置客户端与服务连接类型
			urlConn.addRequestProperty("Connection", "Keep-Alive");
			// 开始连接
			urlConn.connect();
			// 判断请求是否成功
			if (urlConn.getResponseCode() == 200) {
				// String filePath = Environment.getExternalStorageDirectory()
				// .getAbsolutePath() + "/ADownload/test.xml";
				File descFile = new File(filepath);
				FileOutputStream fos = new FileOutputStream(descFile);
				;
				byte[] buffer = new byte[1024];
				int len;
				InputStream inputStream = urlConn.getInputStream();
				while ((len = inputStream.read(buffer)) != -1) {
					// 写到本地
					fos.write(buffer, 0, len);
				}

				if (fos != null) {
					fos.flush();
					fos.close();
					fos = null;
				}

				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} else {
				Log.e(TAG, "文件下载失败");
				return false;
			}
			// 关闭连接
			urlConn.disconnect();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
			return false;
		}

		return true;
	}

}
