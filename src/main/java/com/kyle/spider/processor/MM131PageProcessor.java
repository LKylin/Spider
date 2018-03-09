package com.kyle.spider.processor;

import java.util.List;

import com.kyle.spider.util.Log;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class MM131PageProcessor implements PageProcessor {

	private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
			.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setCharset("GB2312");

	public void process(Page page) {
		List<String> relativeUrl = page.getHtml().xpath("//a/@href").all();
		page.getRawText();
		String pageURL = page.getUrl().get();
		int lastSlash = pageURL.lastIndexOf('/');
		pageURL = pageURL.substring(0, lastSlash + 1);
		for (String url : relativeUrl) {
			if (isValidateURL(url)) {
				System.out.println("Fetch : " + url);
				page.addTargetRequest(url);
			}
			if (isValidatePageURL(url)) {
				System.out.println("Fetch Album: " + url);
 				page.addTargetRequest(pageURL + url);
			}
		}
		Selectable imageDiv = page.getHtml().xpath("//div[@class='content']");
		if(null == imageDiv) {
			page.setSkip(true);
			return;
		} 
		String title = imageDiv.xpath("//img/@alt").toString();
		if (null == title) {
			page.setSkip(true);
			return;
		}
		Log.info("title : " + title);
		String imgURL = imageDiv.xpath("//img/@src").toString();
		if (null == imgURL) {
			page.setSkip(true);
			return;
		}
		page.putField(title, imgURL);
	}

	public Site getSite() {
		return site;
	}


	private boolean isValidateURL(String url) {
		url = url.trim().toLowerCase();
		if (!url.startsWith("http://www.mm131.com")) {
			return false;
		}
		if (url.endsWith(".jpg")) {
			return false;
		}
		return true;
	}
	
	private boolean isValidatePageURL(String url) {
		if(url.toLowerCase().endsWith(".html")) {
			return true;
		} else {
			return false;
		}
	}
	
}
