package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;

import com.uyt.ying.rxhttp.net.interceptor.HttpBaseUrlInterceptor;
import com.uyt.ying.rxhttp.net.interceptor.HttpHeaderInterceptor;
import com.uyt.ying.rxhttp.net.utils.SSL;
import com.uyt.ying.rxhttp.net.utils.SSLSocketClient;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


/**
 *
 * @author smz
 *
 */
public class OkHttp3Utils {

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

	private volatile static OkHttp3Utils mInstance;

	private OkHttpClient mOkHttpClient;

	OkHttp3Utils() {
		super();
		Builder clientBuilder= new OkHttpClient().newBuilder();
		clientBuilder.readTimeout(20, TimeUnit.SECONDS);
		clientBuilder.connectTimeout(20, TimeUnit.SECONDS);
		clientBuilder.writeTimeout(20, TimeUnit.SECONDS);
//		clientBuilder .sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
		clientBuilder .hostnameVerifier(SSLSocketClient.getHostnameVerifier());
		clientBuilder.sslSocketFactory(new SSL(trustAllCert), trustAllCert);
		clientBuilder.addInterceptor(new HttpHeaderInterceptor());
		SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
		if(sp.getAppVestFlag()==0){
        clientBuilder.addInterceptor(new HttpBaseUrlInterceptor());
		}
		clientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
		//设置缓存
		//clientBuilder .cache(new Cache(cacheDir.getAbsoluteFile(), cacheSize));
				//允许重定向
		clientBuilder	.followRedirects(true);
				//设置拦截器
	//	clientBuilder	.addInterceptor(new RequetInterceptor());
		clientBuilder.connectionPool(new ConnectionPool(20, 100, TimeUnit.SECONDS));

		mOkHttpClient = clientBuilder.build();
	}

	public OkHttp3Utils(Long readTimeout,long connectTimeout,long writeTimeout){
		Builder clientBuilder = new OkHttpClient().newBuilder();
		clientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);
		clientBuilder.connectTimeout(connectTimeout,TimeUnit.SECONDS);
		clientBuilder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
		clientBuilder.connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS));
		mOkHttpClient = clientBuilder.build();
	}

	public static OkHttp3Utils getInstance() {
		OkHttp3Utils temp = mInstance;
		if (temp == null) {
			synchronized (OkHttp3Utils.class) {
				temp = mInstance;
				if (temp == null) {
					temp = new OkHttp3Utils();
					mInstance = temp;
				}
			}
		}
		return temp;
	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	/**
	 * 设置请求头
	 *
	 * @param headersParams
	 * @return
	 */
	private Headers SetHeaders(Map<String, Object> headersParams) {
		Headers headers = null;
		Headers.Builder headersbuilder = new Headers.Builder();
		if (headersParams != null) {
			Iterator<String> iterator = headersParams.keySet().iterator();
			String key = "";
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				headersbuilder.add(key, String.valueOf(headersParams.get(key)));
			}
		}
		headers = headersbuilder.build();
		return headers;
	}

	/**
	 * post请求参数
	 *
	 * @param BodyParams
	 * @return
	 */
	private RequestBody SetPostRequestBody(Map<String, Object> BodyParams) {
		RequestBody body = null;
		FormBody.Builder formEncodingBuilder = new FormBody.Builder();
		if (BodyParams != null) {
			Iterator<String> iterator = BodyParams.keySet().iterator();
			String key = "";
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				formEncodingBuilder.add(key, String.valueOf(BodyParams.get(key)));
			}
		}
		body = formEncodingBuilder.build();
		return body;
	}
	/**
	 * get方法连接拼加参数
	 *
	 * @param mapParams
	 * @return
	 */
	public static String setGetUrlParams(Map<String, Object> mapParams) {
		String strParams = "";
		if (mapParams != null) {
			Iterator<String> iterator = mapParams.keySet().iterator();
			String key = "";
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				strParams += "&" + key + "=" + String.valueOf(mapParams.get(key));
			}
		}
		return strParams;
	}

	public static void main(String[] args) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("awb", "123");
		params.put("order", "25");
		params.put("username", "kkli");
		params.put("password", "fwe");
	}

	/**
	 * 实现post请求 同步
	 *
	 * @param reqUrl
	 * @param headersParams
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String doPost_Execute(String reqUrl, Map<String, Object> headersParams, Map<String, Object> params)
			throws IOException {
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl);// 添加URL地址
		RequestBuilder.post(SetPostRequestBody(params));
		RequestBuilder.headers(SetHeaders(headersParams));
/*		RequestBuilder.removeHeader("User-Agent");
		RequestBuilder.addHeader("User-Agent", WebSettings.getDefaultUserAgent(MyApplication.getInstance()));*/  //设置User - Agent
		Request request = RequestBuilder.build();
		Call call = mOkHttpClient.newCall(request);
		Response response = call.execute();
		byte[] bytes = response.body().bytes();
		String result = new String(bytes, Charset.forName("UTF-8"));
		return result;
	}


	/**
	 * 实现post请求 异步
	 *
	 * @param reqUrl
	 * @param headersParams
	 * @param params
	 * @param callback
	 */
	public void doPost_Enqueue(String reqUrl, Map<String, Object> headersParams, Map<String, Object> params,
			final NetCallback callback) {
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl);// 添加URL地址
		RequestBuilder.post(SetPostRequestBody(params));
		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();

		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				callback.onSuccess(0, response.body().toString());
				call.cancel();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				callback.onFailure(-1, e.getMessage());
				call.cancel();
			}
		});
	}

	public String doGet_Execute(String reqUrl, Map<String, Object> headersParams, Map<String, Object> params) {
		/*System.out.println(reqUrl);*/
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl + setGetUrlParams(params));// 添加URL地址 自行加 ?
		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();
		String result = "";
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				byte[] bytes = response.body().bytes();
				result = new String(bytes, Charset.forName("UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 实现get请求
	 *
	 * @param reqUrl
	 * @param headersParams
	 * @param params
	 * @param callback
	 */
	public void doGet_Enqueue(String reqUrl, Map<String, Object> headersParams, Map<String, Object> params,
			final NetCallback callback) {
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl + setGetUrlParams(params));// 添加URL地址 自行加 ?
		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				callback.onSuccess(0, response.body().toString());
				call.cancel();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				callback.onFailure(-1, e.getMessage());
				call.cancel();
			}
		});
	}

	/**
	 * 参数为 json格式
	 *
	 * @param BodyParams
	 * @return
	 */
	private RequestBody SetPostRequestBodyByJson(String BodyParams) {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		return RequestBody.create(JSON, BodyParams);
	}

	/**
	 * post 提交String
	 *
	 * @param postBody
	 * @return
	 */
	private RequestBody SetPostRequestBodyByString(String postBody) {
		return RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody);
	}

	private RequestBody SetPostRequestBodyByStream() {
		return new RequestBody() {
			@Override
			public MediaType contentType() {
				return MEDIA_TYPE_MARKDOWN;
			}

			@Override
			public void writeTo(BufferedSink sink) throws IOException {
				sink.writeUtf8("Numbers\n");
				sink.writeUtf8("-------\n");
				for (int i = 2; i <= 997; i++) {
					sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
				}
			}

			private String factor(int n) {
				for (int i = 2; i < n; i++) {
					int x = n / i;
					if (x * i == n)
						return factor(x) + " × " + i;
				}
				return Integer.toString(n);
			}

		};
	}

	/**
	 * 实现post请求 同步
	 *
	 * @param reqUrl
	 * @param headersParams
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String doPost_Execute(String reqUrl, Map<String, Object> headersParams, String params) throws IOException {
		/*System.out.println(reqUrl);
		System.out.println(headersParams);
		System.out.println(params);*/
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl);// 添加URL地址
		RequestBuilder.post(SetPostRequestBodyByJson(params));

		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();
		Call call = mOkHttpClient.newCall(request);
		Response response = call.execute();
		byte[] bytes = response.body().bytes();
		String result = new String(bytes, Charset.forName("UTF-8"));
		return result;
	}

	/**
	 * 实现post请求 异步
	 *
	 * @param reqUrl
	 * @param headersParams
	 * @param params
	 * @param callback
	 */
	public void doPost_Enqueue(String reqUrl, Map<String, Object> headersParams, String params,
			final NetCallback callback) {
		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl);// 添加URL地址
		RequestBuilder.post(SetPostRequestBodyByJson(params));
		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();

		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				callback.onSuccess(0, response.body().toString());
				call.cancel();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				callback.onFailure(-1, e.getMessage());
				call.cancel();
			}
		});
	}

	public abstract static class NetCallback {
		public abstract void onFailure(int code, String msg);

		public abstract void onSuccess(int code, String content);

	}

	public String doPost_ExecuteByString(String reqUrl, Map<String, Object> headersParams, String postBody)
			throws Exception {

		Request.Builder RequestBuilder = new Request.Builder();
		RequestBuilder.url(reqUrl);// 添加URL地址
		RequestBuilder.post(SetPostRequestBodyByString(postBody));

		RequestBuilder.headers(SetHeaders(headersParams));// 添加请求头
		Request request = RequestBuilder.build();
		Call call = mOkHttpClient.newCall(request);
		Response response = call.execute();
		byte[] bytes = response.body().bytes();
		String result = new String(bytes, Charset.forName("UTF-8"));
		return result;
	}


	public void setCertificates(InputStream... certificates) {
		try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null);
			int index = 0;
			for (InputStream certificate : certificates) {
				String certificateAlias = Integer.toString(index++);
				keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

				try {
					if (certificate != null)
						certificate.close();
				} catch (IOException e) {
				}
			}

			SSLContext sslContext = SSLContext.getInstance("TLS");

			TrustManagerFactory trustManagerFactory =
					TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

			trustManagerFactory.init(keyStore);
			sslContext.init
					(
							null,
							trustManagerFactory.getTrustManagers(),
							new SecureRandom()
					);

			//.setSslSocketFactory(sslContext.getSocketFactory());


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	final X509TrustManager trustAllCert = new X509TrustManager() {
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[]{};
		}
	};
}