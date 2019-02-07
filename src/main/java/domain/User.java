package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="USER")
@Table(name="USER")
public class User implements Serializable{
	
	public User() {
		super();
	}
	
	@Id
	@NotNull(message="email cannot be null")
	private String email;
	
	@NotNull(message="Password cannot be null")
	private String password;
	
	@NotNull(message="Role cannot be null")
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
