package com.github.liangyunfeng.okhttp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import com.google.gson.Gson;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.TlsVersion;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public class OkHttpTest {

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");

	private final OkHttpClient client = new OkHttpClient();

	public static void main(String[] args) {
		OkHttpTest t = new OkHttpTest();
		try {
			// t.runPostWithGson();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// CacheResponse.test();

		// CancelCall.test();

		// ConfigureTimeout.test();

		// OkHttpClientCopy.test();

		// Authenticate.test();

		// ApplicationInterceptor.test();

		// NetworkInterceptor.test();

		// GzipRequestInterceptor.test();

		// RewriteRequestWithInterceptor.test();

		// RewriteResponseWithInterceptor.test();

		// HttpsSecuty.test();

		// CertificatePinning.test();

		// CustomTrust.test();
	}

	public void run1() throws Exception {
		Request request = new Request.Builder().url(
				"http://publicobject.com/helloworld.txt").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		Headers responseHeaders = response.headers();
		for (int i = 0; i < responseHeaders.size(); i++) {
			System.out.println(responseHeaders.name(i) + ": "
					+ responseHeaders.value(i));
		}

		System.out.println(response.body().string());
	}

	public void run2() throws Exception {
		Request request = new Request.Builder().url(
				"http://publicobject.com/helloworld.txt").build();

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);

				Headers responseHeaders = response.headers();
				for (int i = 0, size = responseHeaders.size(); i < size; i++) {
					System.out.println(responseHeaders.name(i) + ": "
							+ responseHeaders.value(i));
				}

				System.out.println(response.body().string());
			}
		});
	}

	public void run3() throws Exception {
		Request request = new Request.Builder()
				.url("https://api.github.com/repos/square/okhttp/issues")
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println("Server: " + response.header("Server"));
		System.out.println("Date: " + response.header("Date"));
		System.out.println("Vary: " + response.headers("Vary"));
	}

	public void runPostString() throws Exception {
		String postBody = "" + "Release\n" + "-------\n" + "\n"
				+ "* _1.0_ May 6, 2013\n" + "* _1.1_ June 15, 2013\n"
				+ "* _1.2_ August 11, 2013\n";

		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public void runPostStream() throws Exception {
		RequestBody requestBody = new RequestBody() {

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
						return factor(x) + " x " + i;
				}
				return Integer.toString(n);
			}
		};

		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw").post(requestBody)
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public void runPostFile() throws Exception {
		File file = new File("README.MD");

		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public void runPostForm() throws Exception {
		RequestBody formBody = new FormBody.Builder().add("search",
				"Jurassic Park").build();

		Request request = new Request.Builder()
				.url("https://en.wikipedia.org/w/index.php").post(formBody)
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	private static final String IMGUR_CLIENT_ID = "...";
	private static final MediaType MEDIA_TYPE_PNG = MediaType
			.parse("image/png");

	public void runMultiPart() throws Exception {
		// Use the imgur image upload API as documented at
		// https://api.imgur.com/endpoints/image
		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("title", "Square Log")
				.addFormDataPart(
						"image",
						"logo-square.png",
						RequestBody.create(MEDIA_TYPE_PNG, new File(
								"website/static/logo-square.png"))).build();

		Request request = new Request.Builder()
				.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
				.url("https://api.imgur.com/3/image").post(requestBody).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	private final Gson gson = new Gson();

	public void runPostWithGson() throws IOException {
		Request request = new Request.Builder().url(
				"https://api.github.com/gists/c2a7c39532239ff261be").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
		for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().content);
		}
	}

	static class Gist {
		Map<String, GistFile> files;
	}

	static class GistFile {
		String content;
	}

}

class CacheResponse {
	private final OkHttpClient client;

	public CacheResponse(File cacheDirectory) throws Exception {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		Cache cache = new Cache(cacheDirectory, cacheSize);

		client = new OkHttpClient.Builder().cache(cache).build();
	}

	public void run() throws Exception {
		/*
		 * final CacheControl.Builder builder = new CacheControl.Builder();
		 * builder.noCache();//不使用缓存，全部走网络 builder.noStore();//不使用缓存，也不存储缓存
		 * builder.onlyIfCached();//只使用缓存 builder.noTransform();//禁止转码
		 * builder.maxAge(10, TimeUnit.MILLISECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
		 * builder.maxStale(10, TimeUnit.SECONDS);//指示客户机可以接收超出超时期间的响应消息
		 * builder.minFresh(10,
		 * TimeUnit.SECONDS);//指示客户机可以接收响应时间小于当前时间加上指定时间的响应。 CacheControl
		 * cacheControl = builder.build();//cacheControl
		 */

		// CacheControl.FORCE_CACHE; //仅仅使用缓存
		// CacheControl.FORCE_NETWORK;// 仅仅使用网络

		Request request = new Request.Builder().url(
				"http://publicobject.com/helloworld.txt")
		// .cacheControl(cacheControl)
		// .cacheControl(CacheControl.FORCE_CACHE)
				.build();

		Response response1 = client.newCall(request).execute();
		if (!response1.isSuccessful())
			throw new IOException("Unexpected code " + response1);

		String response1Body = response1.body().string();
		System.out.println("Response 1 response:		" + response1);
		System.out.println("Response 1 cache response:	"
				+ response1.cacheResponse());
		System.out.println("Response 1 network response:"
				+ response1.networkResponse());

		Response response2 = client.newCall(request).execute();
		if (!response2.isSuccessful())
			throw new IOException("Unexpected code " + response2);

		String response2Body = response2.body().string();
		System.out.println("Response 2 response:		" + response2);
		System.out.println("Response 2 cache response:	"
				+ response2.cacheResponse());
		System.out.println("Response 2 network response:"
				+ response2.networkResponse());

		System.out.println("Response 2 equals Response 1? "
				+ response1Body.equals(response2Body));
	}

	public static void test() {
		try {
			File file = new File("okhttp-cache");
			if (!file.exists()) {
				file.mkdirs();
			}
			CacheResponse cr = new CacheResponse(file);
			cr.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class CancelCall {

	private OkHttpClient client = new OkHttpClient();

	private final ScheduledExecutorService executor = Executors
			.newScheduledThreadPool(1);

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"http://httpbin.org/delay/2") // This URL is served with a 2
												// second delay.
				.build();

		final long startNanos = System.nanoTime();
		final Call call = client.newCall(request);

		// Schedule a job to cancel the call in 1 second.
		executor.schedule(new Runnable() {

			@Override
			public void run() {
				System.out.printf("%.2f Canceling call.\n",
						(System.nanoTime() - startNanos) / 1e9f);
				call.cancel();
				System.out.printf("%.2f Canceled call.\n",
						(System.nanoTime() - startNanos) / 1e9f);
			}

		}, 1, TimeUnit.SECONDS);

		try {
			System.out.printf("%.2f Executing call.\n",
					(System.nanoTime() - startNanos) / 1e9f);
			Response response = call.execute();
			System.out.printf(
					"%.2f Call was expected to fail, but completed: %s\n",
					(System.nanoTime() - startNanos) / 1e9f, response);
		} catch (IOException e) {
			System.out.printf("%.2f Call failed as expected: %s\n",
					(System.nanoTime() - startNanos) / 1e9f, e);
		}
	}

	public static void test() {
		try {
			CancelCall cc = new CancelCall();
			cc.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ConfigureTimeout {
	private final OkHttpClient client;

	public ConfigureTimeout() {
		client = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS).build();
	}

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"http://httpbin.org/delay/2") // This URL is served with a 2
												// second delay
				.build();

		Response response = client.newCall(request).execute();
		System.out.println("Response completed: " + response);
	}

	public static void test() {
		try {
			ConfigureTimeout ct = new ConfigureTimeout();
			ct.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class OkHttpClientCopy {
	private final OkHttpClient client = new OkHttpClient();

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"http://httpbin.org/delay/1") // This URL is served with a 1
												// second delay.
				.build();

		try {
			// Copy to customize OkHttp for this request.
			OkHttpClient copy = client.newBuilder()
					.readTimeout(500, TimeUnit.MILLISECONDS).build();
			Response response = copy.newCall(request).execute();
			System.out.println("Response 1 succeeded: " + response);
		} catch (IOException e) {
			System.out.println("Response 1 failed: " + e);
		}

		try {
			// Copy to customize OkHttp for this request.
			OkHttpClient copy = client.newBuilder()
					.readTimeout(3000, TimeUnit.MILLISECONDS).build();

			Response response = copy.newCall(request).execute();
			System.out.println("Response 2 succeeded: " + response);
		} catch (IOException e) {
			System.out.println("Response 2 failed: " + e);
		}
	}

	public static void test() {
		try {
			OkHttpClientCopy cc = new OkHttpClientCopy();
			cc.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Authenticate {
	private final OkHttpClient client;

	public Authenticate() {
		client = new OkHttpClient.Builder().authenticator(new Authenticator() {

			@Override
			public Request authenticate(Route route, Response response)
					throws IOException {
				System.out.println("Authenticating for response: " + response);
				System.out.println("Challenges: " + response.challenges());
				String credential = Credentials.basic("jesse", "password1");
				/*
				 * if(credential.equals(response.request().header("Authorization"
				 * ))) { return null; // 如果我们尝试过在失败，放弃 }
				 */
				if (responseCount(response) > 3) {
					return null; // 如果我们已经失败了3次，放弃
				}
				return response.request().newBuilder()
						.header("Authorization", credential).build();
			}

		}).build();
	}

	private int responseCount(Response response) {
		int result = 1;
		while ((response = response.priorResponse()) != null) {
			result++;
		}
		return result;
	}

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"http://publicobject.com/secrets/hellosecret.txt").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		System.out.println(response.body().string());
	}

	public static void test() {
		try {
			Authenticate au = new Authenticate();
			au.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class LoggingInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();

		long t1 = System.nanoTime();
		System.out.println(String.format("Sending request %s on %s%n%s",
				request.url(), chain.connection(), request.headers()));

		Response response = chain.proceed(request);

		long t2 = System.nanoTime();
		System.out.println(String.format(
				"Received respose for %s in %.1fms%n%s", response.request()
						.url(), (t2 - t1) / 1e6d, request.headers()));

		return response;
	}

}

class ApplicationInterceptor {
	public static void test() {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
				new LoggingInterceptor()).build();

		Request request = new Request.Builder()
				.url("http://www.publicobject.com/helloworld.txt")
				.header("User-Agent", "Okhttp Example").build();

		try {
			Response response = client.newCall(request).execute();
			response.body().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class NetworkInterceptor {
	public static void test() {
		OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(
				new LoggingInterceptor()).build();

		Request request = new Request.Builder()
				.url("http://www.publicobject.com/helloworld.txt")
				.header("User-Agent", "Okhttp Example").build();

		try {
			Response response = client.newCall(request).execute();
			response.body().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// This interceptor compresses the HTTP request body. Many webserver can't
// handle this!
final class GzipRequestInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request originalRequest = chain.request();
		if (originalRequest.body() == null
				|| originalRequest.header("Content-Encoding") != null) {
			return chain.proceed(originalRequest);
		}

		Request compressedRequest = originalRequest.newBuilder()
				.header("Content-Encoding", "gzip")
				.method(originalRequest.method(), gzip(originalRequest.body()))
				.build();

		return chain.proceed(compressedRequest);
	}

	private RequestBody gzip(final RequestBody body) {
		return new RequestBody() {

			@Override
			public MediaType contentType() {
				return body.contentType();
			}

			@Override
			public void writeTo(BufferedSink sink) throws IOException {
				BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
				body.writeTo(gzipSink);
				gzipSink.close();
			}

			@Override
			public long contentLength() throws IOException {
				return -1; // We don't know the compressed length in advance!
			}
		};
	}
}

class RewriteRequestWithInterceptor {
	public static void test() {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
				new GzipRequestInterceptor()).build();

		Request request = new Request.Builder().url(
				"http://www.publicobject.com/helloworld.txt").build();

		try {
			Response response = client.newCall(request).execute();
			response.body().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class RewriteResponseWithInterceptor {
	// Dangerous interceptor that rewrites the server's cache-control header.
	private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

		@Override
		public Response intercept(Chain chain) throws IOException {
			Response originalResponse = chain.proceed(chain.request());

			return originalResponse.newBuilder()
					.header("Cache-Control", "max-age=60").build();
		}

	};

	public static void test() {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
				REWRITE_CACHE_CONTROL_INTERCEPTOR).build();

		Request request = new Request.Builder().url(
				"http://www.publicobject.com/helloworld.txt").build();

		try {
			Response response = client.newCall(request).execute();
			response.body().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class HttpsSecuty {
	public static void test() {
		ConnectionSpec spec = new ConnectionSpec.Builder(
				ConnectionSpec.MODERN_TLS)
				.tlsVersions(TlsVersion.TLS_1_2)
				.cipherSuites(
						CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256,
						CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
						CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
				.build();

		OkHttpClient client = new OkHttpClient.Builder().connectionSpecs(
				Collections.singletonList(spec)).build();
	}
}

class CertificatePinning {

	private final OkHttpClient client;

	public CertificatePinning() {
		client = new OkHttpClient.Builder().certificatePinner(
				new CertificatePinner.Builder().add("publicobject.com",
						"sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
						.build()).build();
	}

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"https://publicobject.com/robots.txt").build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code" + response);

		for (Certificate certificate : response.handshake().peerCertificates()) {
			System.out.println(CertificatePinner.pin(certificate));
		}
	}

	public static void test() {
		try {
			CertificatePinning cp = new CertificatePinning();
			cp.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class CustomTrust {
	private final OkHttpClient client;

	public CustomTrust() {
		SSLContext sslContext = sslContextForTrustedCertificates(trustedCertificatesInputStream());
		client = new OkHttpClient.Builder().sslSocketFactory(
				sslContext.getSocketFactory()).build();
	}

	public void run() throws Exception {
		Request request = new Request.Builder().url(
				"https://publicobject.com/helloworld.txt").build();

		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}

	private InputStream trustedCertificatesInputStream() {
		// ... // Full source omitted. See sample.
		return null;
	}

	public SSLContext sslContextForTrustedCertificates(InputStream in) {
		// ... // Full source omitted. See sample.
		return null;
	}
	
	public static void test() {
		try {
			CustomTrust ct = new CustomTrust();
			ct.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
