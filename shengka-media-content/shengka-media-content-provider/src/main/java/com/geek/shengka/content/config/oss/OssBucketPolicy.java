package com.geek.shengka.content.config.oss;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OssBucketPolicy {
	
	public OssBucketPolicy() {
	}
	
	public OssBucketPolicy(String backetName) {
		Statement =new ArrayList<OssBucketPolicyStatement>();
		OssBucketPolicyStatement ossBucketPolicyStatement=new OssBucketPolicyStatement();
		Statement.add(ossBucketPolicyStatement);
		List<String> action=new ArrayList<>();
		ossBucketPolicyStatement.setAction(action);
	    List<String> resource=new ArrayList<>();
		ossBucketPolicyStatement.setResource(resource);
		action.add("oss:GetObject");
		action.add("oss:PutObject");
		//action.add("oss:DeleteObject");
		action.add("oss:ListParts");
		action.add("oss:AbortMultipartUpload");
		action.add("oss:ListObjects");
		
		resource.add("acs:oss:*:*:"+backetName+"/*");
		resource.add("acs:oss:*:*:"+backetName);
	}
	@JSONField(name="Statement")
	private List<OssBucketPolicyStatement> Statement;
	
	@JSONField(name="Version")
	private String Version="1";
	 
}
