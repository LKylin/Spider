package com.kyle.spider.util;

public class Log {

	public static void info(String msg) {
		System.out.println(msg);
	}

	public static void debug(String msg) {
		System.out.println(msg);
	}

	public static void warn(String msg) {
		System.out.println(msg);
	}

	public static void warn(String msg, Exception e) {
		System.out.println(msg);
		e.printStackTrace();
	}

	public static void error(String msg) {
		System.err.println(msg);
	}
	
}
