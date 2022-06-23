package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ForResisterForm;
import com.example.repository.ForResisterRepository;
import com.example.service.Service;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private Service service;

	@ModelAttribute
	public ForResisterForm setForResisterForm() {
		return new ForResisterForm();
	}

	public String request() {
		return "user_request";

	}

	@RequestMapping("/mail")
	public String mail(@Validated ForResisterForm forResisterForm, BindingResult result, Model model) {
		if (forResisterForm.hasErrors()) {
			return "user_request";
		}
		ForResisterRepository forResister = Service.findByEmail(forResisterForm.getMail());


	}

}
