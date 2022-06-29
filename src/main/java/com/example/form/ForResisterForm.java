package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ForResisterForm {
	@NotBlank(message = "メールアドレスを入力してください")
	@Size(min = 0, max = 50, message = "メールアドレスは50文字までで入力してください")
	@Email(message = "メールアドレス形式で入力してください")
	private String mail;
	private String urlTimeError;
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUrlTimeError() {
		return urlTimeError;
	}

	public void setUrlTimeError(String urlTimeError) {
		this.urlTimeError = urlTimeError;
	}
	@Override
	public String toString() {
		return "ForResisterForm [mail=" + mail + ", urlTimeError=" + urlTimeError + "]";
	}




}
