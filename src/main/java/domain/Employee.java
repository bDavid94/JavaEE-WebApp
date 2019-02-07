package domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
//import org.hibernate.annotations.CascadeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@javax.persistence.Entity(name="EMPLOYEE")
@Table(name="EMPLOYEE")
public class Employee implements Serializable{
	
	@Override
	public String toString() {
		return "Employee [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", appointments=" + appointments + "]";
	}

	@Id
	@NotNull
	private String email;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String phoneNumber;
	
	@OneToMany(cascade= { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, 
			   mappedBy="employee", fetch=FetchType.EAGER, orphanRemoval=true)
	@Cascade({
		org.hibernate.annotations.CascadeType.DELETE,
		org.hibernate.annotations.CascadeType.MERGE,
		org.hibernate.annotations.CascadeType.REFRESH,
		org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	})
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Appointment> appointments = new ArrayList<Appointment>();
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Collection<Appointment> getAppointment() {
		return appointments;
	}

	public void setAppointment(Collection<Appointment> appointments) {
		this.appointments = appointments;
	}

}

	
