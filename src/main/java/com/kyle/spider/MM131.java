package com.kyle.spider;

import com.kyle.spider.pipeline.ImagePipeline;
import com.kyle.spider.processor.MM131PageProcessor;

import us.codecraft.webmagic.Spider;

public class MM131 {

	public static void main(String[] args) {
		Spider.create(new MM131PageProcessor()).addUrl("http://www.mm131.com/")
				.addPipeline(new ImagePipeline("E:\\WebMagic\\")).thread(1).run();
	}

}
