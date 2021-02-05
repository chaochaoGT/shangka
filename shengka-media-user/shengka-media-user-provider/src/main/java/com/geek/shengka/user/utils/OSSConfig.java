package com.geek.shengka.user.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class OSSConfig {

	private OSSConfig() {
	}

	public static String endpoint;
	public static String accesskeyid;
	public static String accesskeysecret;
	public static String backetname;
	public static String folder;
	public static String httpTitle;


	@Value("${oss.endpoint}")
	public void setEndpoint(String endpoint) {
		OSSConfig.endpoint = endpoint;
	}

	@Value("${oss.accesskeyid}")
	public  void setAccesskeyid(String accesskeyid) {
		OSSConfig.accesskeyid = accesskeyid;
	}

	@Value("${oss.accesskeysecret}")
	public  void setAccesskeysecret(String accesskeysecret) {
		OSSConfig.accesskeysecret = accesskeysecret;
	}

	@Value("${oss.backetname:shengka}")
	public  void setBacketname(String backetname) {
		OSSConfig.backetname = backetname;
	}

	@Value("${oss.folder}")
	public  void setFolder(String folder) {
		OSSConfig.folder = folder;
	}

	@Value("${oss.httpTitle}")
	public  void setHttpTitle(String httpTitle) {
		OSSConfig.httpTitle = httpTitle;
	}
}
