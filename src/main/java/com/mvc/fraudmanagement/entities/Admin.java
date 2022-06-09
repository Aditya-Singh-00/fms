package com.mvc.fraudmanagement.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Admin {

	@Column(name = "first_name", nullable = false)
	@Size(min=1, message="Enter at least 5 Characters...")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@Size(min=1, message="Enter at least 5 Characters...")
	private String lastName;
	
	@Column(name = "dob", nullable = false)
	@Past(message="Date of Birth can not be a future date")
	private Date dob;
  
	@Column(name = "gender", nullable = false)
	@Size(min=1, message="Please select a gender type")
	private String gender;
  
	@Column(name="contact_No",unique = true)
	@Size(min=10, message="Enter a Valid Phone Number")
	@Size(max=10, message="Enter a Valid Phone Number")
	private String contact;
	
	@Column(name = "user_id", nullable = false, length = 20)
	@Size(min=5, message="Enter at least 5 Characters...")
    private String userId;
     
    @Column(name = "password", nullable = false, length = 20)
    @Size(min=8, message="Enter at least 8 Characters...")
    private String password;
    
    public Admin() {
    	super();
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	
}
