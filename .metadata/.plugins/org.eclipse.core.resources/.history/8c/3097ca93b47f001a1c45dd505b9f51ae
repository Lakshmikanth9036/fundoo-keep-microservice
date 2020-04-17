package com.bridgelabz.fundookeep.service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundookeep.config.Consumer;
import com.bridgelabz.fundookeep.config.Producer;
import com.bridgelabz.fundookeep.constants.Constants;
import com.bridgelabz.fundookeep.dao.Note;
import com.bridgelabz.fundookeep.dao.User;
import com.bridgelabz.fundookeep.dto.LoginDTO;
import com.bridgelabz.fundookeep.dto.LoginResponse;
import com.bridgelabz.fundookeep.dto.Mail;
import com.bridgelabz.fundookeep.dto.ProfileDTO;
import com.bridgelabz.fundookeep.dto.RegistrationDTO;
import com.bridgelabz.fundookeep.exception.UserException;
import com.bridgelabz.fundookeep.repository.UserRepository;
import com.bridgelabz.fundookeep.utils.JwtUtils;

@Service
@PropertySource("classpath:message.properties")
public class UserServiceProvider implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private Producer producer;

	@Autowired
	private Consumer consumer;

	@Autowired
	private Environment env;
	
	@Autowired
	private JwtUtils jwt;

	/**
	 * Saves the user details
	 */
	@Override
	public void saveUser(RegistrationDTO register) {

		if (repository.findByEmailAddress(register.getEmailAddress()).isPresent())
			throw new UserException(HttpStatus.FOUND.value(), env.getProperty("103"));

		register.setPassword(encoder.encode(register.getPassword()));
		User user = new User(register);
		Mail mail = new Mail();
		try {
			User usr = repository.save(user);
			if (usr != null) {
				mail.setTo(user.getEmailAddress());
				mail.setSubject(Constants.REGISTRATION_STATUS);
				mail.setContext("Hi " + user.getFirstName() + " " + user.getLastName() + Constants.REGISTRATION_MESSAGE
						+ Constants.VERIFICATION_LINK + jwt.generateToken(user.getUserId()));
				producer.sendToQueue(mail);
				consumer.receiveMail(mail);
			}
		} catch (UserException e) {
			throw new UserException(400, env.getProperty("102"));
		}
	}

	/**
	 * Update the user verification status if the received token is valid
	 * @return integer value that is number of record that had been updated
	 */
	@Override
	public int updateVerificationStatus(String token) {
		Long id = jwt.decodeToken(token);
		try {
			return repository.updateUserVerificationStatus(id, LocalDateTime.now());
		} catch (UserException e) {
			throw new UserException(400, env.getProperty("102"));
		}
	}

	/**
	 * Login to the application using login credential
	 * @return user details which is necessary
	 */
	@Override
	public LoginResponse loginByEmailOrMobile(LoginDTO login) {
		
		User user=null;
		boolean email = Pattern.compile("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)").matcher(login.getMailOrMobile()).matches();
		boolean mobile = Pattern.compile("^[0-9]{10}$").matcher(login.getMailOrMobile()).matches();
		Long mbl = mobile ? Long.parseLong(login.getMailOrMobile()) : 0; 
		user = email ? repository.findByEmailAddress(login.getMailOrMobile()).orElseThrow(() -> new UserException(404, env.getProperty("104"))) :
			   mobile ? repository.findByMobile(mbl).orElseThrow(() -> new UserException(404, env.getProperty("104"))) : null;

		if (user.isUserVerified() && user !=null) {
			if (encoder.matches(login.getPassword(), user.getPassword())) {
				user.setPassword(null);
				user.setNotes(null);
				user.setLabels(null);
				return new LoginResponse(HttpStatus.OK.value(), env.getProperty("202"), user,
						jwt.generateUserToken(user.getUserId()));
			}
			throw new UserException(401, env.getProperty("401"));
		}
		return null;
	}

	/**
	 * Send the jwt token to the user mail
	 */
	@Override
	public void sendTokentoMail(String emailAddress) {

		User user = repository.findByEmailAddress(emailAddress)
				.orElseThrow(() -> new UserException(404, env.getProperty("104")));
		Mail mail = new Mail();
		mail.setTo(emailAddress);
		mail.setSubject(Constants.RESET_MSG);
		mail.setContext(Constants.RESET_PASSWORD_LINK + jwt.generateToken(user.getUserId()));
		producer.sendToQueue(mail);
		consumer.receiveMail(mail);
	}

	/**
	 * Provides the link to reset the password to user mail address
	 */
	@Override
	public int resetPassword(String token, String newPassword) {
		Long id = jwt.decodeToken(token);
		return repository.updatePassword(id, encoder.encode(newPassword), LocalDateTime.now());
	}
	
	/**
	 * To get the profile details
	 */
	@Override
	public ProfileDTO getProfileDetails(String token) {
		Long uId = jwt.decodeToken(token);
		User user = repository.findById(uId).orElseThrow(() -> new UserException(404, env.getProperty("104")));
		ProfileDTO profile = new ProfileDTO();
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		profile.setEmailAddress(user.getEmailAddress());
		return profile;
	}

}
