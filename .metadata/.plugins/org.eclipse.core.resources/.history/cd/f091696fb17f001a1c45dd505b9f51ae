package com.bridgelabz.fundookeep.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDTO {

	@NotNull(message = "Frist Name field can't be empty!!!")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed Frist Name field")
	@Size(min = 3)
	private String firstName;

	@NotNull(message = "Last Name field can't be empty!!!")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Digits and Special characters are not allowed Last Name field")
	private String lastName;

	@NotNull(message = "Email address field can't be empty!!!")
	@Email(message = "Enter valid mail address!!!")
	private String emailAddress;

	@NotNull(message = "Moblie number field can't be empty!!!")
	// @Size(min = 10, max = 10, message = "Mobile number length should be 10
	// digits")
	// @Pattern(regexp = "^[7896][0-9]{9}",message = "Mobile number length should be
	// 10 digits")
	private Long mobile;

	@NotNull(message = "Password field can't be empty!!!")
	private String password;

	public RegistrationDTO() {
		// Auto-generated constructor stub
	}

	public RegistrationDTO(
			@NotNull(message = "Frist Name field can't be empty!!!") @Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed Frist Name field") @Size(min = 3) String firstName,
			@NotNull(message = "Last Name field can't be empty!!!") @Pattern(regexp = "^[a-zA-Z]+$", message = "Digits and Special characters are not allowed Last Name field") String lastName,
			@NotNull(message = "Email address field can't be empty!!!") @Email(message = "Enter valid mail address!!!") String emailAddress,
			@NotNull(message = "Moblie number field can't be empty!!!") Long mobile,
			@NotNull(message = "Password field can't be empty!!!") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobile = mobile;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
