package com.example.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ForResisterForm {
	@NotBlank(message = "メールアドレスを入力してください")
	@Size(min = 1, max = 50, message = "メールアドレスは50文字までで入力してください")
	@Email(message = "メールアドレス形式で入力してください")
	private String mail;
	private Integer id;
	private String key;
	private LocalDateTime resisterDateTime;
	private boolean doneflag;
	private String urlTimeError;
	public String getMail() {
		return mail;
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

	public String getUrlTimeError() {
		return urlTimeError;
	}
	public void setUrlTimeError(String urlTimeError) {
		this.urlTimeError = urlTimeError;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "ForResisterForm [mail=" + mail + ", id=" + id + ", key=" + key + ", resisterDateTime="
				+ resisterDateTime + ", doneflag=" + doneflag + ", urlTimeError=" + urlTimeError + "]";
	}



}
