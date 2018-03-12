package com.kyle.spider.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;

public class APICaller {

	private static final long CLIENT_CONNECT_TIME_OUT = 3000;
	private static final long CLIENT_READ_TIME_OUT = 3000;
	
	private static APICaller sAPICaller;
	
	private OkHttpClient mOkHttpClient;

	private APICaller() {
		OkHttpClient.Builder build = new OkHttpClient.Builder();
		build.connectTimeout(CLIENT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
		build.readTimeout(CLIENT_READ_TIME_OUT, TimeUnit.MILLISECONDS);
		mOkHttpClient = build.build();
	}

	public static APICaller getInstance() {
		if (null == sAPICaller) {
			synchronized (APICaller.class) {
				if (null == sAPICaller) {
					sAPICaller = new APICaller();
				}
			}
		}
		return sAPICaller;
	}
	
	/**
	 * 调用API URL，返回JSON解析完之后的关联类
	 * @param api 要调用的API URL
	 * @param clazz 返回JSON的Class
	 * @return 返回JSON解析完之后的关联类
	 * @throws IOException
	 */
	public <T> T callAPI(String api, Class<T> clazz) throws IOException {
		Request request = new Request.Builder()
				.url(api)
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:52.0) Gecko/20100101 Firefox/52.0")
				.addHeader("Cookie", "q_c1=5e8fdedfe11f4f69b46c8e7f107aad4c|1519375892000|1510035061000; _zap=c1a40029-0f64-4a13-994e-5233da07587b; d_c0=\"AGDCd5LMpwyPTrqMG06-Fh9BQAH4r3IWT9c=|1510199237\"; __utma=51854390.1184304547.1510199235.1515467939.1516781093.6; __utmz=51854390.1516781093.6.6.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmv=51854390.100-1|2=registration_date=20140618=1^3=entry_date=20140618=1; z_c0=Mi4xREZOa0FBQUFBQUFBWU1KM2tzeW5EQmNBQUFCaEFsVk44eDN4V2dBQXc0VWFoRW9nWUtIeXNHZWdxSWNXenpNTlBR|1510199283|c81e5a7c93bf68472494e496f832ae2775d5b")
				.addHeader("Authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20")
				.build();
		Response response = mOkHttpClient.newCall(request).execute();
		String content = response.body().string();
		T t = (T) JSON.parseObject(content, clazz);
		return t;
	}

}
