package com.bridgelabz.fundookeep.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.fundookeep.dto.Collaborator;
import com.bridgelabz.fundookeep.dto.RegistrationDTO;

@Entity
public class User {
	@Id
	@GenericGenerator(name = "idGen", strategy = "increment")
	@GeneratedValue(generator = "idGen")
	@Column(name = "UserID")
	private Long userId;

	@Column(name = "FirstName", nullable = false)
	private String firstName;

	@Column(name = "LastName", nullable = false)
	private String lastName;

	@Column(name = "Email", nullable = false, unique = true)
	private String emailAddress;

	@Column(name = "MobileNo", nullable = false, unique = true)
	private Long mobile;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "isUserVerified", nullable = false, columnDefinition = "bit(1) default 0")
	private boolean isUserVerified;

	@Column(name = "CreatedUserAt", nullable = false)
	private LocalDateTime createUser;

	@Column(name = "UserUpdatedAt", nullable = false)
	private LocalDateTime updateUser;

	@Column(name = "ProfilePic")
	private String profilePic;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId")
	private List<Note> notes;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId")
	private List<Label> labels;
	
	@ManyToMany
	private List<Collaborator> collaborators;

	public User() {
	}

	public User(RegistrationDTO register) {
		this.firstName = register.getFirstName();
		this.lastName = register.getLastName();
		this.emailAddress = register.getEmailAddress();
		this.mobile = register.getMobile();
		this.password = register.getPassword();
		this.createUser = LocalDateTime.now();
		this.updateUser = LocalDateTime.now();
		this.isUserVerified = false;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public LocalDateTime getCreateUser() {
		return createUser;
	}

	public void setCreateUser(LocalDateTime createUser) {
		this.createUser = createUser;
	}

	public LocalDateTime getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(LocalDateTime updateUser) {
		this.updateUser = updateUser;
	}

	public boolean isUserVerified() {
		return isUserVerified;
	}

	public void setUserVerified(boolean isUserVerified) {
		this.isUserVerified = isUserVerified;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public List<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}
	
	

}
