package com.example.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ForResister;
import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.Service;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Service service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public UserForm setUserForm() {
		return new UserForm();
	}

	@RequestMapping("/resister")
	public String resisterIndex(String key, Model model) {
		ForResister forResister = service.findByRkey(key);
		if (forResister == null) {
			model.addAttribute("urlError", "本登録用URLが無効です。登録用URLリクエスト画面から再度お試しください");
			return "forward:/forresister";
		}
		LocalDateTime nowDatetime = LocalDateTime.now();
		long localDiffDays = ChronoUnit.SECONDS.between(forResister.getResisterDateTime(), nowDatetime);
		Long oneDayTime = 86400L;
		if (localDiffDays < oneDayTime) {
			session.setAttribute("key", key);
			return "user_resister";
		} else {
			model.addAttribute("urlError", "本登録用URLが無効です。登録用URLリクエスト画面から再度お試しください");
			return "forward:/forresister";
		}
	}

	@RequestMapping("/complete")
	public String complete() {
		return "user_complete";
	}

	@RequestMapping("/forcomplete")
	public String forcomplete(@Validated UserForm userForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user_resister";
		}
		if (!(userForm.getConfirm().equals(userForm.getPassword()))) {
			result.rejectValue("confirm", null, "確認パスワードとパスワードが一致しません");
			return "user_resister";
		}
		User user = new User();
		user.setName(userForm.getName());
		user.setHurigana(userForm.getHurigana());
		user.setZipCode(userForm.getZipCode());
		user.setAddress(userForm.getAddress());
		user.setTel(userForm.getTel());
		user.setPassword(userForm.getPassword());

		String key = (String) session.getAttribute("key");
		String mail = service.findByRkey(key).getMail();
		user.setMail(mail);
		user.setResisterDateTime(LocalDateTime.now());
		user.setUpdateDateTime(LocalDateTime.now());
		user.setDeleteflag(false);

		service.insertUser(user);
		
		ForResister forResister = service.findByRkey(key);
		forResister.setDoneflag(true);
		service.updateForResister(forResister);
		
		return "redirect:/user/complete";


	}

}
