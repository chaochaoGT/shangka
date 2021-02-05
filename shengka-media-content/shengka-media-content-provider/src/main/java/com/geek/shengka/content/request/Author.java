package com.geek.shengka.content.request;


import java.io.Serializable;

public class Author implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 作者ID **/
	private String id;
	/** 作者名称 **/
	private String nickname;
	/** 作者图像 **/
	private String avatar;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
