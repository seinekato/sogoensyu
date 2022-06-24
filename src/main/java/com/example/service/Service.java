package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.ForResister;
import com.example.domain.User;
import com.example.repository.ForResisterRepository;
import com.example.repository.UserRepository;

@org.springframework.stereotype.Service
@Transactional
public class Service {

	@Autowired
	private ForResisterRepository forResisterRepository;

	@Autowired
	private UserRepository userRepository;


	public ForResister findByRmail(String mail) {
		return forResisterRepository.findByRmail(mail);
	}

	public void insertForResister(ForResister forResister) {
		forResisterRepository.insertForResister(forResister);
	}

	public ForResister findByRkey(String key) {
		return forResisterRepository.findByRkey(key);
	}

	public void updateForResister(ForResister forResister) {
		forResisterRepository.updateForResister(forResister);
	}

	public User findByUmail(String mail) {
		return userRepository.findByUmail(mail);
	}

	public void insertUser(User user) {
		userRepository.insertUser(user);
	}

}
