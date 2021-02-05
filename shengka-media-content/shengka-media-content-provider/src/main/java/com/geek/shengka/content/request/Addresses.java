package com.geek.shengka.content.request;

import java.io.Serializable;


public class Addresses implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Address hd;
	
	private Address sd;
	
	private Address ld;

	public Address getHd() {
		return hd;
	}

	public void setHd(Address hd) {
		this.hd = hd;
	}

	public Address getSd() {
		return sd;
	}

	public void setSd(Address sd) {
		this.sd = sd;
	}

	public Address getLd() {
		return ld;
	}

	public void setLd(Address ld) {
		this.ld = ld;
	}
	
	
	
}
