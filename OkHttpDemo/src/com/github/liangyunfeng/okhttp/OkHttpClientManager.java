package com.github.liangyunfeng.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientManager {

	private static final String TAG = "OkHttpClientManager";

	private static volatile OkHttpClientManager mInstance;
	private OkHttpClient mOkHttpClient;
	// private Handler mDelibery;
	private Gson mGson;

	private OkHttpClientManager() {
		mOkHttpClient = new OkHttpClient();
		// cookie enabled
		// mOkHttpClient.setCookieHandler(new CookieManager(null,
		// CookiePolicy.ACCEPT_ORIGINAL_SERVER));
		// mDelibery = new Handler(Looper.getMainLooper());
		mGson = new Gson();
	}

	public static OkHttpClientManager getInstance() {
		if (mInstance == null) {
			synchronized (OkHttpClientManager.class) {
				if (mInstance == null) {
					mInstance = new OkHttpClientManager();
				}
			}
		}
		return mInstance;
	}

	/**
	 * ͬ��Get����
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private Response _get(String url) throws IOException {
		final Request request = new Request.Builder().url(url).build();
		Call call = mOkHttpClient.newCall(request);
		Response response = call.execute();
		return response;
	}

	/**
	 * ͬ��Get����
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private String _getAsString(String url) throws IOException {
		Response response = _get(url);
		return response.body().string();
	}

	/**
	 * �첽��Get����
	 * 
	 * @param url
	 * @param callback
	 * @throws IOException
	 */
	private void _getAsync(String url, final ResultCallback callback)
			throws IOException {
		final Request request = new Request.Builder().url(url).build();
		deliveryResult(callback, request);
	}

	/**
	 * ͬ����Post����
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private Response _post(String url, Param... params) throws IOException {
		Request request = buildPostRequest(url, params);
		Response response = mOkHttpClient.newCall(request).execute();
		return response;
	}

	/**
	 * ͬ����Post����
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private String _postAsString(String url, Param... params)
			throws IOException {
		Response response = _post(url, params);
		return response.body().string();
	}

	/**
	 * �첽��Post����
	 * 
	 * @param url
	 * @param callback
	 * @param params
	 */
	private void _postAsync(String url, final ResultCallback callback,
			Param... params) {
		Request request = buildPostRequest(url, params);
		deliveryResult(callback, request);
	}

	/**
	 * �첽��Post����
	 * 
	 * @param url
	 * @param callback
	 * @param params
	 */
	private void _postAsync(String url, final ResultCallback callback,
			Map<String, String> params) {
		Param[] paramsArr = map2array(params);
		Request request = buildPostRequest(url, paramsArr);
		deliveryResult(callback, request);
	}

	/**
	 * ͬ������Post���ļ��ϴ�
	 * 
	 * @param url
	 * @param files
	 * @param fileKeys
	 * @param params
	 * @return
	 * @throws IOException
	 */
	private Response _post(String url, File[] files, String[] fileKeys,
			Param... params) throws IOException {
		Request request = buildMultipartFormRequest(url, files, fileKeys,
				params);
		return mOkHttpClient.newCall(request).execute();
	}

	private Response _post(String url, File file, String fileKey)
			throws IOException {
		Request request = buildMultipartFormRequest(url, new File[] { file },
				new String[] { fileKey }, null);
		return mOkHttpClient.newCall(request).execute();
	}

	private Response _post(String url, File file, String fileKey,
			Param... params) throws IOException {
		Request request = buildMultipartFormRequest(url, new File[] { file },
				new String[] { fileKey }, params);
		return mOkHttpClient.newCall(request).execute();
	}

	/**
	 * �첽����post���ļ��ϴ�
	 * 
	 * @param url
	 * @param callback
	 * @param files
	 * @param fileKeys
	 * @param params
	 * @throws IOException
	 */
	private void _postAsync(String url, ResultCallback callback, File[] files,
			String[] fileKeys, Param... params) throws IOException {
		Request request = buildMultipartFormRequest(url, files, fileKeys,
				params);
		deliveryResult(callback, request);
	}

	/**
	 * �첽����post���ļ��ϴ������ļ����������ϴ�
	 *
	 * @param url
	 * @param callback
	 * @param file
	 * @param fileKey
	 * @throws IOException
	 */
	private void _postAsync(String url, ResultCallback callback, File file,
			String fileKey) throws IOException {
		Request request = buildMultipartFormRequest(url, new File[] { file },
				new String[] { fileKey }, null);
		deliveryResult(callback, request);
	}

	/**
	 * �첽����post���ļ��ϴ������ļ���Я������form�����ϴ�
	 *
	 * @param url
	 * @param callback
	 * @param file
	 * @param fileKey
	 * @param params
	 * @throws IOException
	 */
	private void _postAsync(String url, ResultCallback callback, File file,
			String fileKey, Param... params) throws IOException {
		Request request = buildMultipartFormRequest(url, new File[] { file },
				new String[] { fileKey }, params);
		deliveryResult(callback, request);
	}
	
	/**
	 * �첽�����ļ�
	 * 
	 * @param url
	 * @param destFileDir
	 * @param callback
	 */
	private void _downloadAsync(final String url, final String destFileDir,
			final ResultCallback callback) {
		final Request request = new Request.Builder().url(url).build();
		final Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				sendFailedStringCallback(call.request(), e, callback);
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				InputStream is = null;
				byte[] buf = new byte[2048];
				int len = 0;
				FileOutputStream fos = null;
				try {
					is = response.body().byteStream();
					File file = new File(destFileDir, getFileName(url));
					fos = new FileOutputStream(file);
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
					}
					fos.flush();
					// ��������ļ��ɹ�����һ������Ϊ�ļ��ľݶ�·��
					sendSuccessResultCallback(file.getAbsolutePath(), callback);
				} catch (IOException e) {
					sendFailedStringCallback(response.request(), e, callback);
				} finally {
					try {
						if (is != null)
							is.close();
					} catch (IOException e) {
					}
					try {
						if (fos != null)
							fos.close();
					} catch (IOException e) {
					}
				}
			}
		});
	}

	private String getFileName(String path) {
		int separatorIndex = path.lastIndexOf("/");
		return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1,
				path.length());
	}


	/*private void _displayImage(final ImageView view, final String url,
			final int errorResId) {
		final Request request = new Request.Builder().url(url).build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				setErrorResId(view, errorResId);
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				InputStream is = null;
				try {
					is = response.body().byteStream();
					ImageUtils.ImageSize actualImageSize = ImageUtils.getImageSize(is);
					ImageUtils.ImageSize imageViewSize = ImageUtils.getImageViewSize(view);
					int inSampleSize = ImageUtils.calculateInSampleSize(actualImageSize, imageViewSize);
					try {
						is.reset();
					} catch (IOException e) {
						response = _get(url);
						is = response.body().byteStream();
					}
					BitmapFactory.Options ops = new BitmapFactory.Options();
					ops.inJustDecodeBounds = false;
					ops.inSampleSize = inSampleSize;
					final Bitmap bm = BitmapFactory.decodeStream(is, null, ops);
					mDelivery.post(new Runnable() {
						@Override
						public void run() {
							view.setImageBitmap(bm);
						}
					});
				} catch (Exception e) {
					setErrorResId(view, errorResId);

				} finally {
					if (is != null)
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		});
	}
	
	private void setErrorResId(final ImageView view, final int errorResId) {
		mDelivery.post(new Runnable() {
			@Override
			public void run() {
				view.setImageResource(errorResId);
			}
		});
	}*/

	// *************���⹫���ķ���************

	public static Response getAsyn(String url) throws IOException {
		return getInstance()._get(url);
	}

	public static String getAsString(String url) throws IOException {
		return getInstance()._getAsString(url);
	}

	public static void getAsyn(String url, ResultCallback callback)
			throws IOException {
		getInstance()._getAsync(url, callback);
	}

	public static Response post(String url, Param... params) throws IOException {
		return getInstance()._post(url, params);
	}

	public static String postAsString(String url, Param... params)
			throws IOException {
		return getInstance()._postAsString(url, params);
	}

	public static void postAsyn(String url, final ResultCallback callback,
			Param... params) {
		getInstance()._postAsync(url, callback, params);
	}

	public static void postAsyn(String url, final ResultCallback callback,
			Map<String, String> params) {
		getInstance()._postAsync(url, callback, params);
	}

	public static Response post(String url, File[] files, String[] fileKeys,
			Param... params) throws IOException {
		return getInstance()._post(url, files, fileKeys, params);
	}

	public static Response post(String url, File file, String fileKey)
			throws IOException {
		return getInstance()._post(url, file, fileKey);
	}

	public static Response post(String url, File file, String fileKey,
			Param... params) throws IOException {
		return getInstance()._post(url, file, fileKey, params);
	}

	public static void postAsyn(String url, ResultCallback callback,
			File[] files, String[] fileKeys, Param... params)
			throws IOException {
		getInstance()._postAsync(url, callback, files, fileKeys, params);
	}

	public static void postAsyn(String url, ResultCallback callback, File file,
			String fileKey) throws IOException {
		getInstance()._postAsync(url, callback, file, fileKey);
	}

	public static void postAsyn(String url, ResultCallback callback, File file,
			String fileKey, Param... params) throws IOException {
		getInstance()._postAsync(url, callback, file, fileKey, params);
	}

	/*public static void displayImage(final ImageView view, String url,
			int errorResId) throws IOException {
		getInstance()._displayImage(view, url, errorResId);
	}

	public static void displayImage(final ImageView view, String url) {
		getInstance()._displayImage(view, url, -1);
	}*/

	public static void downloadAsyn(String url, String destDir,
			ResultCallback callback) {
		getInstance()._downloadAsync(url, destDir, callback);
	}

	// ****************************

	private void deliveryResult(final ResultCallback callback, Request request) {
		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				sendFailedStringCallback(call.request(), e, callback);
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				try {
					final String content = response.body().string();
					if (callback.mType == String.class) {
						sendSuccessResultCallback(content, callback);
					} else {
						Object o = mGson.fromJson(content, callback.mType);
						sendSuccessResultCallback(o, callback);
					}
				} catch (IOException e) {
					sendFailedStringCallback(response.request(), e, callback);
				} catch (com.google.gson.JsonParseException e) { // Json�����Ĵ���
					sendFailedStringCallback(response.request(), e, callback);
				}
			}
		});
	}
	
	private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
		/*mDelivery.post(new Runnable() {
			@Override
			public void run() {
				if(callback != null)
					callback.onError(request, e);
			}
		});*/
	}
	
	private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
		/*mDelivery.post(new Runnable() {
			@Override
			public void run() {
				if(callback != null)
					callback.onResponse(object);
			}
		});*/
	}
	
	private Request buildPostRequest(String url, Param[] params) {
		if (params == null) {
			params = new Param[0];
		}
		FormBody.Builder builder = new FormBody.Builder();
		for (Param param : params) {
			builder.add(param.key, param.value);
		}
		RequestBody requestBody = builder.build();
		return new Request.Builder().url(url).post(requestBody).build();
	}
	
	private Param[] map2array(Map<String, String> params) {
		if (params == null)
			return new Param[0];
		int size = params.size();
		Param[] res = new Param[size];
		Set<Map.Entry<String, String>> entries = params.entrySet();
		int i = 0;
		for (Map.Entry<String, String> entry : entries) {
			res[i++] = new Param(entry.getKey(), entry.getValue());
		}
		return res;
	}
	
	private Request buildMultipartFormRequest(String url, File[] files,
			String[] fileKeys, Param[] params) {
		params = validateParam(params);

		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);

		for (Param param : params) {
			builder.addPart(
					Headers.of("Content-Disposition", "form-data; name=\""
							+ param.key + "\""),
					RequestBody.create(null, param.value));

		}

		if (files != null) {
			RequestBody fileBody = null;
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				String fileName = file.getName();
				fileBody = RequestBody.create(
						MediaType.parse(guessMimeType(fileName)), file);
				// �����ļ�������contentType
				builder.addPart(
						Headers.of("Content-Disposition", "form-data; name=\""
								+ fileKeys[i] + "\"; filename=\"" + fileName
								+ "\""), fileBody);
			}
		}

		RequestBody requestBody = builder.build();
		return new Request.Builder().url(url).post(requestBody).build();
	}

	private Param[] validateParam(Param... params) {
		if (params == null)
			return new Param[0];
		else
			return params;
	}

	private String guessMimeType(String path) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentTypeFor = fileNameMap.getContentTypeFor(path);
		if (contentTypeFor == null) {
			contentTypeFor = "application/octet-stream";
		}
		return contentTypeFor;
	}
}
