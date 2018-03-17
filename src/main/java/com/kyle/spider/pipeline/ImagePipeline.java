package com.kyle.spider.pipeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
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
//	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	private static final long KEEP_ALIVE = 1L;
	private static final long CLIENT_CONNECT_TIME_OUT = 3000;
	private static final long CLIENT_READ_TIME_OUT = 3000;

	private OkHttpClient mOkHttpClient;
	private ThreadPoolExecutor mExecutor;
	private NameGenerator mNameGenerator;
	private Map<String, String> mHeaders;

	/**
	 * create a ImagePipeline with default path"/data/webmagic/"
	 */
	public ImagePipeline() {
		this("/data/webmagic/");
	}

	public ImagePipeline(String path) {
		setPath(path);
		mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, Integer.MAX_VALUE, KEEP_ALIVE, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		mExecutor.allowCoreThreadTimeOut(true);
		OkHttpClient.Builder build = new OkHttpClient.Builder();
		build.connectTimeout(CLIENT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
		build.readTimeout(CLIENT_READ_TIME_OUT, TimeUnit.MILLISECONDS);
		mOkHttpClient = build.build();
	}

	public void process(ResultItems resultItems, Task task) {
		Log.debug("Process Image Pipeline : " + resultItems.getRequest().getUrl());
		String imageName;
		String url;
		int point;
		String type;
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			if (entry.getValue() instanceof String) {
				url = (String) entry.getValue();
				if (!isValidateImageURL(url)) {
					continue;
				} else {
					point = url.lastIndexOf('.');
					type = url.substring(point);
					if (null == mNameGenerator) {
						UUID uuid = UUID.randomUUID();
						imageName = uuid.toString();
					} else {
						imageName = mNameGenerator.generateName(entry, task);
					}
					mExecutor.execute(new DownloadThread(path + PATH_SEPERATOR + imageName + type, url));
				}
			}
		}
	}
	
	public void setNameGenerator(NameGenerator nameGenerator) {
		mNameGenerator = nameGenerator;
	}
	
	public void addRequestHeader(String name, String value) {
		if (null == mHeaders) {
			mHeaders =  new LinkedHashMap<String, String>();
		}
		mHeaders.put(name, value);
	}
	
	public void destory() {
		mExecutor.shutdown();
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
			try {
				File file = getFile(fileName);
				// TODO 文件重复检查好像应该检查URL，检查文件名不准确啊
				if (file.exists()) {
					return;
				}
				okhttp3.Request.Builder build = new Request.Builder().url(url);
				if (null != mHeaders) {
					for(Map.Entry<String, String> entry : mHeaders.entrySet()) {
						build.addHeader(entry.getKey(), entry.getValue());
					}
				}
				Request request = build.build();
				Response response = mOkHttpClient.newCall(request).execute();
				InputStream is = response.body().byteStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int length = 0;
				long fileSize = 0;
				while ((length = is.read(buffer)) != -1) {
					fileSize += length;
					fos.write(buffer, 0, length);
				}
				fos.close();
				Log.info("Download " + url + ", size : " + fileSize);
			} catch (IOException e) {
				Log.error("Download " + url + "failed!");
				recordDownloadFailedImage(url);
			}
		}
		
		private void recordDownloadFailedImage(String url) {
			synchronized (ImagePipeline.class) {
				File file = getFile(getPath() + PATH_SEPERATOR + "FailedURL.txt");
				try {
					PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
					printWriter.println(url);
					printWriter.close();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public interface NameGenerator {
		/**
		 * 生成照片文件名，允许包含父目录，不用包含文件类型的后缀，会根据URL自动生成。
		 * @param imageInfo 照片信息，为添加Pipeline时用的键值对
		 * @param task 本次爬取的Task
		 * @return 照片文件名
		 */
		public String generateName(Map.Entry<String, Object> imageInfo, Task task);
	}
	
}
