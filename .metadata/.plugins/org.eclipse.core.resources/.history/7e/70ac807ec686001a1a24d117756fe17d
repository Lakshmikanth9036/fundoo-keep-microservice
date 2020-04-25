package com.bridgelabz.userservice.service;

import com.bridgelabz.userservice.dto.LoginDTO;
import com.bridgelabz.userservice.dto.LoginResponse;
import com.bridgelabz.userservice.dto.RegistrationDTO;
import com.bridgelabz.userservice.entity.User;

public interface UserService {

	public void saveUser(RegistrationDTO register);
	public int updateVerificationStatus(String token);
	public LoginResponse loginByEmailOrMobile(LoginDTO login);
	public void sendTokentoMail(String emailAddress);
	public int resetPassword(String token, String newPassword);
	public Long getUserByToken(String token);
	
}
