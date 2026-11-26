package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Đăng ký tài khoản
	public String register(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			return "Tên đăng nhập đã tồn tại!";
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			return "Email đã được sử dụng!";
		}

		// Tài khoản đăng ký mới mặc định có quyền USER
		user.setRole("USER");

		// Mã hóa mật khẩu trước khi lưu
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);

		return "Đăng ký thành công!";
	}

	// Đăng nhập
	public User login(String username, String password) {

		// Tìm tài khoản theo username
		User user = userRepository.findByUsername(username);

		// Kiểm tra tài khoản và mật khẩu
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {

			return user;
		}

		return null;
	}
}