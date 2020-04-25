package com.bridgelabz.userservice.service;

import com.bridgelabz.userservice.dto.LoginDTO;
import com.bridgelabz.userservice.dto.LoginResponse;
import com.bridgelabz.userservice.dto.RegistrationDTO;
import com.bridgelabz.userservice.entity.User;
import com.bridgelabz.userservice.exception.TokenException;

public interface UserService {

	public void saveUser(RegistrationDTO register);
	public int updateVerificationStatus(String token) throws TokenException;
	public LoginResponse loginByEmailOrMobile(LoginDTO login);
	public void sendTokentoMail(String emailAddress);
	public int resetPassword(String token, String newPassword) throws TokenException;
	public Long getUserByToken(String token) throws TokenException;
	
}
