package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;

@Entity
public class Teacher extends BaseEntity{
	
	String matricule;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	
}
