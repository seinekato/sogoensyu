package com.example.domain;

import java.util.Date;

public class ForResister {
	private Integer id;
	private String mail;
	private String key;
	private Date date;
	private boolean done;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "ForResister [id=" + id + ", mail=" + mail + ", key=" + key + ", date=" + date + ", done=" + done + "]";
	}


}
