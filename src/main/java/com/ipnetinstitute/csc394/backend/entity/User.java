package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="users")
public class User extends BaseEntity {
	
	public User() {
		
	}
	public User(String firstName, String lastName, String phone, String eMail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.eMail = eMail;
	}

	String firstName;
	String lastName;
	String phone;
	String eMail;
	String userName;
	
	@ColumnTransformer(read = "AES_DECRYPT(UNHEX(password), UNHEX(SHA2('Un truc secret', 512)))", write = "HEX(AES_ENCRYPT(?, UNHEX(SHA2('Un truc secret', 512))))")

	String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", eMail=" + eMail
				+ ", id=" + id + ", createDateTime=" + createDateTime + ", modDateTime=" + modDateTime + ", modBy="
				+ modBy + ", error=" + error + "]";
	}

}
