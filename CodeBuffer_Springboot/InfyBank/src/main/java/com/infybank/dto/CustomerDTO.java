package com.infybank.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

public class CustomerDTO {

	private Integer customerId;
	
	@NotEmpty(message="Please provide email id")
	@Pattern(regexp="[A-Za-z]+([A-Za-z]+)*",message="Name should only Conatin alphabets and space")
	private String name;
	@NotEmpty(message="Please provide email id")
	@Email(message="Please provide valid email address")
	private String emailId;
	
	@PastOrPresent(message="Date of Birth should be past or present")
	private LocalDate dateOfBirth;
	
	public CustomerDTO() {	}

	public CustomerDTO(Integer customerId, String name, String emailId, LocalDate dateOfBirth) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", emailId=" + emailId + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, dateOfBirth, emailId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(name, other.name);
	}

}
