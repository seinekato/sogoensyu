package com.example.domain;

import java.util.Date;

public class User {
	private String name;
	private String hurigana;
	private String code;
	private String address;
	private String tel;
	private String password;
	private String mail;
	private String resisterUser;
	private Date resisterDate;
	private String updateUser;
	private Date updateDate;
	private boolean delete;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHurigana() {
		return hurigana;
	}
	public void setHurigana(String hurigana) {
		this.hurigana = hurigana;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getResisterUser() {
		return resisterUser;
	}

	public void setResisterUser(String resisterUser) {
		this.resisterUser = resisterUser;
	}

	public Date getResisterDate() {
		return resisterDate;
	}

	public void setResisterDate(Date resisterDate) {
		this.resisterDate = resisterDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", hurigana=" + hurigana + ", code=" + code + ", address=" + address + ", tel="
				+ tel + ", password=" + password + ", mail=" + mail + ", resisterUser=" + resisterUser
				+ ", resisterDate=" + resisterDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", delete=" + delete + "]";
	}




	}

