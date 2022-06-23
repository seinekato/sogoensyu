package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;

public class ForResisterForm {
	@Email(message = "メールアドレス形式で入力してください")
	private String mail;
	private Integer id;
	private String key;
	private Date date;
	private boolean done;

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "ForResisterForm [mail=" + mail + ", id=" + id + ", key=" + key + ", date=" + date + ", done=" + done
				+ "]";
	}


}
