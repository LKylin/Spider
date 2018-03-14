package com.kyle.spider.processor;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.kyle.spider.bean.ZHAnswerData;
import com.kyle.spider.bean.ZHAnswerListResponse;
import com.kyle.spider.util.Log;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class ZhihuAnswerProcessor implements PageProcessor{

	public static final String URL_PREFIX = "https://www.zhihu.com/api/v4/questions/";
	public static final String URL_SUFFIX = "/answers?";
	public static final String SORT_BY = "sort_by=default";
	public static final String INCLUDE = "include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,upvoted_followees;data[*].mark_infos[*].url;data[*].author.follower_count,badge[?(type=best_answerer)].topics";
	public static final String LIMEIT_PREFIX = "limit=";
	public static final String OFFSET_PREFIX = "offset=";
	
	// TODO 请将下面变量填充为自己的cookie，可以下登陆之后通过浏览器开发者工具查看下面两个字段
	private static final String AUTHORIZATION = "";
	private static final String COOKIE = "";
	
	private Site site;
	
	public ZhihuAnswerProcessor() {
		this(AUTHORIZATION, COOKIE);
	}
	
	public ZhihuAnswerProcessor(String authorization, String cookie) {
		if (null == authorization || null == cookie || "".equals(authorization) || "".equals(cookie)) {
			throw new RuntimeException("Invalid arguments!");
		}
		site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
	            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36")
	            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
	            .addHeader("authorization", authorization)
	            .addHeader("Cookie", cookie)
	            .setCharset("UTF-8");
	}
	
	public void process(Page page) {
		Html html;
		String jsonString = page.getRawText();
		ZHAnswerListResponse zhAnswerListResponse = JSON.parseObject(jsonString, ZHAnswerListResponse.class);
		if (null != zhAnswerListResponse) {
			for(ZHAnswerData data : zhAnswerListResponse.getData()) {
				html = new Html(data.getContent(), page.getRequest().getUrl());
				List<String> relativeUrl = html.xpath("//img[@data-actualsrc]/@data-actualsrc").all();;
				for (String imgUrl : relativeUrl) {
					Log.info("ImageURL : " + imgUrl);
					page.putField(imgUrl, imgUrl);
				}
			}
			if(!zhAnswerListResponse.getPaging().getIs_end()) {
				page.addTargetRequest(zhAnswerListResponse.getPaging().getNext());
			}
		} else {
			Log.error("Requset Fail!!\n");
		}
		
	}

	public Site getSite() {
		return site;
	}

}
