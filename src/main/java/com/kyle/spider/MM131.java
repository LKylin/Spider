package com.kyle.spider;

import java.io.File;
import java.util.Map.Entry;

import com.kyle.spider.pipeline.ImagePipeline;
import com.kyle.spider.pipeline.ImagePipeline.NameGenerator;
import com.kyle.spider.processor.MM131PageProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;

public class MM131 {

	public static void main(String[] args) {
		ImagePipeline imagePipeline = new ImagePipeline("D:\\WebMagic\\");
		imagePipeline.addRequestHeader("Referer", "http://www.mm131.com/");
		imagePipeline.setNameGenerator(new NameGenerator() {
			public String generateName(Entry<String, Object> imageInfo, Task task) {
				String title = imageInfo.getKey();
				int i = title.lastIndexOf("(");
				return title.substring(0, i) + File.separatorChar + title.substring(i + 1, title.length() - 1);
			}
		});
		Spider.create(new MM131PageProcessor()).addUrl("http://www.mm131.com/")
				.addPipeline(imagePipeline).thread(1).run();
	}

}
