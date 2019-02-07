package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Entity(name="CUSTOMER")
@Table(name="CUSTOMER")
public class Customer implements Serializable{
	
	@Id
	@NotNull
	private String email;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@OneToOne(cascade= { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH },
			  mappedBy="customer",
			  orphanRemoval=true,
			  optional=true
			  )
	@Cascade({
		org.hibernate.annotations.CascadeType.DELETE,
		org.hibernate.annotations.CascadeType.MERGE,
		org.hibernate.annotations.CascadeType.REFRESH,
		org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	})
	@NotFound(action=NotFoundAction.IGNORE)
	private Appointment appointment;
	
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

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
