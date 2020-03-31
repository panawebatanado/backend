package com.ipnetinstitute.csc394.backend.entity;

import java.util.Date; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo; 

@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, 
include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Teacher.class, name = "teacher"),     
    @JsonSubTypes.Type(value = Student.class, name = "student"),
    @JsonSubTypes.Type(value = User.class, name = "user")
}) 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract  class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Date createDateTime;
	Date modDateTime;
	Integer modBy;
	
	@Transient
	String error;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getModDateTime() {
		return modDateTime;
	}
	public void setModDateTime(Date modDateTime) {
		this.modDateTime = modDateTime;
	}
	public Integer getModBy() {
		return modBy;
	}
	public void setModBy(Integer modBy) {
		this.modBy = modBy;
	}
	
	
}
