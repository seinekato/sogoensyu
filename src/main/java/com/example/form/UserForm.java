package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
	@NotBlank(message = "名前を入力してください")
	@Size(min = 0, max = 20, message = "名前は20文字までで入力してください")
	private String name;
	
	@NotBlank(message = "ふりがなを入力してください")
	@Size(min = 0, max = 20, message = "ふりがなは20文字までで入力してください")
	@Pattern(regexp = "^[\\u3040-\\u309F]*$", message = "ひらがなで入力してください")
	private String hurigana;
	
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipCode;

	@NotBlank(message = "住所を入力してください")
	@Size(min = 0, max = 50, message = "住所は50文字までで入力してください")
	private String address;

	@NotBlank(message = "電話番号を入力してください")
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{4}$", message = "ハイフンを含む電話形式で入力してください")
	private String tel;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 0, max = 20, message = "パスワードは20文字までで入力してください")
	@Pattern(regexp = "^[A-Za-z0-9]*$", message = "パスワードは半角英数で入力してください")
	private String password;

	@NotBlank(message = "確認パスワードを入力してください")
	private String confirm;

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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	@Override
	public String toString() {
		return "UserForm [name=" + name + ", hurigana=" + hurigana + ", code=" + zipCode + ", address=" + address
				+ ", tel=" + tel + ", password=" + password + ", confirm=" + confirm + "]";
	}

}
