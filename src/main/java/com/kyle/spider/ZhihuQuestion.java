package com.kyle.spider;

import java.util.Map.Entry;

import com.kyle.spider.pipeline.ImagePipeline;
import com.kyle.spider.pipeline.ImagePipeline.NameGenerator;
import com.kyle.spider.processor.ZhihuAnswerProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;

public class ZhihuQuestion {
	
	public static void main(String[] args) {
		long questionID = 27621722;
		String originURL = ZhihuAnswerProcessor.URL_PREFIX + questionID 
				+ ZhihuAnswerProcessor.URL_SUFFIX 
				+ ZhihuAnswerProcessor.SORT_BY + '&'
				+ ZhihuAnswerProcessor.INCLUDE + '&'
				+ ZhihuAnswerProcessor.LIMEIT_PREFIX + 5 + '&'
				+ ZhihuAnswerProcessor.OFFSET_PREFIX + 0;
		ImagePipeline imagePipeline = new ImagePipeline("E:\\WebMagic\\Zhihu\\" + questionID);
		imagePipeline.setNameGenerator(new NameGenerator() {
			
			public String generateName(Entry<String, Object> imageInfo, Task task) {
				String title = imageInfo.getKey();
				int i = title.lastIndexOf('/');
				int j = title.lastIndexOf('.');
				return title.substring(i + 1, j);
			}
			
		});
		Spider.create(new ZhihuAnswerProcessor()).addUrl(originURL)
				.addPipeline(imagePipeline).thread(1).run();
	}
}
