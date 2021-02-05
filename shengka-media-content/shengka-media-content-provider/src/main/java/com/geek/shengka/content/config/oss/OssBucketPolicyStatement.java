package com.geek.shengka.content.config.oss;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class OssBucketPolicyStatement {
	
	@JSONField(name="Action")
	private List<String> Action;
	@JSONField(name="Effect")
	private String Effect="Allow";
	@JSONField(name="Resource")
	private List<String> Resource;
}
