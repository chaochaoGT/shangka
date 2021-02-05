package com.geek.shengka.content.request;

import java.io.Serializable;


public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String url;
	private String bitrate;
	private String height;
	private String durationOrigin;
	private String fps;
	private String duration;
	private String width;
	private String size;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBitrate() {
		return bitrate;
	}
	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}

	public String getDurationOrigin() {
		return durationOrigin;
	}
	public void setDurationOrigin(String durationOrigin) {
		this.durationOrigin = durationOrigin;
	}
	public String getFps() {
		return fps;
	}
	public void setFps(String fps) {
		this.fps = fps;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

}
