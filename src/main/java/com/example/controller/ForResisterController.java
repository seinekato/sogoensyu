package com.example.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ForResister;
import com.example.domain.User;
import com.example.form.ForResisterForm;
import com.example.service.Service;

@Controller
@RequestMapping("/forresister")
public class ForResisterController {
	@Autowired
	private Service service;

	@Autowired
	private MailSender mailSender;


	@ModelAttribute
	public ForResisterForm setForResisterForm() {
		return new ForResisterForm();
	}

	@RequestMapping("")
	public String request() {
		return "user_request";

	}

	@RequestMapping("/sent")
	public String sent() {
		return "sent_mail";
	}

	@RequestMapping("/mail")
	public String mail(@Validated ForResisterForm forResisterForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user_request";
		}
		
		ForResister forResister = service.findByRmail(forResisterForm.getMail());
		ForResister forResister2 = new ForResister();

//		forResister経由済み　　まずnullあるないで判断しなければnullPointerに
		if (forResister != null) {
		LocalDateTime nowDatetime = LocalDateTime.now();
		Duration duration = Duration.between(nowDatetime, forResister.getResisterDateTime());


//　　　　　24時間以内
		if (duration.toHours() <= 24) {
			result.rejectValue("urlTimeError", null, "有効期限内の本登録用メールが存在します。送信済みメールの本登録URLからご登録ください");
			return "user_request";
		} else {
//			24時間以上
			User user = service.findByUmail(forResisterForm.getMail());
			if (user == null) {
				String key = UUID.randomUUID().toString();
				forResister2.setMail(forResisterForm.getMail());
				forResister2.setKey(key);
				forResister2.setResisterDateTime(nowDatetime);
				forResister2.setDoneflag(false);
				service.updateForResister(forResister);
				SimpleMailMessage msg = new SimpleMailMessage();
				try {
					msg.setFrom("coffeeShopMaster2022@gmail.com");
					msg.setTo(forResisterForm.getMail());
					msg.setSubject("本登録用URLのご案内");
					msg.setText("Hogehogeシステム、新規ユーザー登録依頼を受け付けました。\n" + "以下のURLから本登録処理を行ってください。\n" + "\n"
							+ "Hogehogeシステム、ユーザー登録URL\n" + "https://localhost:8080/sogoensyu/user/resister?key=" + key
							+ "\n"
							+ "※上記URLの有効期限は24時間以内です\n");

					mailSender.send(msg);
				} catch (MailException e) {
					e.printStackTrace();
				}
				return "redirect:/forresister/sent";

			} else {
				return "redirect:/forresister/sent";
			}
		}
	}
//		resisterがnull
		User user = service.findByUmail(forResisterForm.getMail());
		LocalDateTime nowDatetime = LocalDateTime.now();

		if (user == null) {
			String key = UUID.randomUUID().toString();
			forResister2.setMail(forResisterForm.getMail());
			forResister2.setKey(key);
			forResister2.setResisterDateTime(nowDatetime);
			forResister2.setDoneflag(false);
			service.insertForResister(forResister2);
			SimpleMailMessage msg = new SimpleMailMessage();
			try {
				msg.setFrom("coffeeShopMaster2022@gmail.com");
				msg.setTo(forResisterForm.getMail());
				msg.setSubject("本登録用URLのご案内");
				msg.setText("Hogehogeシステム、新規ユーザー登録依頼を受け付けました。\n" + "以下のURLから本登録処理を行ってください。\n" + "\n"
						+ "Hogehogeシステム、ユーザー登録URL\n" + "http://localhost:8080/sogoensyu/user/resister?key=" + key
						+ "\n"
						+ "※上記URLの有効期限は24時間以内です\n");

				mailSender.send(msg);
			} catch (MailException e) {
				e.printStackTrace();
			}
			return "redirect:/forresister/sent";

		} else {
			return "redirect:/forresister/sent";
		}



	}

}
