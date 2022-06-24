package com.example.domain;

import java.time.LocalDateTime;

public class User {
	private Integer id;
	private String name;
	private String hurigana;
	private String zipCode;
	private String address;
	private String tel;
	private String password;
	private String mail;
	private Integer resisterUser;
	private LocalDateTime resisterDateTime;
	private Integer updateUser;
	private LocalDateTime updateDateTime;
	private boolean deleteflag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String code) {
		this.zipCode = code;
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
	public Integer getResisterUser() {
		return resisterUser;
	}
	public void setResisterUser(Integer resisterUser) {
		this.resisterUser = resisterUser;
	}
	public LocalDateTime getResisterDateTime() {
		return resisterDateTime;
	}
	public void setResisterDateTime(LocalDateTime resisterDateTime) {
		this.resisterDateTime = resisterDateTime;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public boolean isDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", hurigana=" + hurigana + ", code=" + zipCode + ", address="
				+ address + ", tel=" + tel + ", password=" + password + ", mail=" + mail + ", resisterUser="
				+ resisterUser + ", resisterDateTime=" + resisterDateTime + ", updateUser=" + updateUser
				+ ", updateDateTime=" + updateDateTime + ", deleteflag=" + deleteflag + "]";
	}

	}

