package com.kyle.spider.pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.kyle.spider.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class ImagePipeline extends FilePersistentBase implements Pipeline{

	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	private static final int KEEP_ALIVE = 1;
	private static final long CLIENT_CONNECT_TIME_OUT = 3000;
	private static final long CLIENT_READ_TIME_OUT = 3000;

	private OkHttpClient mOkHttpClient;
	ExecutorService nExecutor;

	/**
	 * create a ImagePipeline with default path"/data/webmagic/"
	 */
	public ImagePipeline() {
		this("/data/webmagic/");
	}

	public ImagePipeline(String path) {
		setPath(path);
		nExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(128));
		OkHttpClient.Builder build = new OkHttpClient.Builder();
		build.connectTimeout(CLIENT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
		build.readTimeout(CLIENT_READ_TIME_OUT, TimeUnit.MILLISECONDS);
		mOkHttpClient = build.build();
	}

	public void process(ResultItems resultItems, Task task) {
		Log.debug("Process Image Pipeline : " + resultItems.getRequest().getUrl());
		String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
		String url;
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			if (entry.getValue() instanceof String) {
				url = (String) entry.getValue();
				if (!isValidateImageURL(url)) {
					continue;
				} else {
					String title = entry.getKey();
					int i = title.lastIndexOf("(");
					path = path + title.substring(0, i) + PATH_SEPERATOR + title.substring(i +1, title.length() - 1);
					nExecutor.execute(new DownloadThread(path, url));
				}
			}
		}
	}

	private boolean isValidateImageURL(String url) {
		if (url.trim().toLowerCase().endsWith(".jpg") || url.trim().toLowerCase().endsWith(".png")) {
			return true;
		}
		return false;
	}

	private class DownloadThread implements Runnable {

		String fileName;
		String url;

		public DownloadThread(String fileName, String url) {
			this.fileName = fileName;
			this.url = url;
		}

		public void run() {
			Log.info("Download " + url);
			int point = url.lastIndexOf('.');
			String type = url.substring(point);
			fileName = fileName + type;
			try {
				Request request = new Request.Builder().url(url).addHeader("Referer", "http://www.mm131.com/").build();
				Response response = mOkHttpClient.newCall(request).execute();
				InputStream is = response.body().byteStream();
				FileOutputStream fos = new FileOutputStream(getFile(fileName));
				byte[] buffer = new byte[1024];
				int length = 0;
				long fileSize = 0;
				while ((length = is.read(buffer)) != -1) {
					fileSize += length;
					fos.write(buffer, 0, length);
				}
				fos.close();
				if (fileSize < 20480) {
					getFile(fileName).delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
