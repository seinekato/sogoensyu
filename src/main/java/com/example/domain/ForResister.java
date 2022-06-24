package com.example.domain;

import java.time.LocalDateTime;

public class ForResister {
	private Integer id;
	private String mail;
	private String key;
	private LocalDateTime resisterDateTime;
	private boolean doneflag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public LocalDateTime getResisterDateTime() {
		return resisterDateTime;
	}

	public void setResisterDateTime(LocalDateTime resisterDateTime) {
		this.resisterDateTime = resisterDateTime;
	}

	public boolean isDoneflag() {
		return doneflag;
	}

	public void setDoneflag(boolean doneflag) {
		this.doneflag = doneflag;
	}

	@Override
	public String toString() {
		return "ForResister [id=" + id + ", mail=" + mail + ", key=" + key + ", resisterDateTime=" + resisterDateTime
				+ ", doneflag=" + doneflag + "]";
	}


}
