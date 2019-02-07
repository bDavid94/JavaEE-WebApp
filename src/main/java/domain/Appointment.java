package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity(name="APPOINTMENT")
@Table(name="APPOINTMENT")
public class Appointment implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@NotNull(message="Product cannot be null")
	private Product product;
	
	@ManyToOne
	@NotNull(message="Employee cannot be null")
	private Employee employee;
	
	@OneToOne
	@NotNull(message="Customer cannot be null")
	private Customer customer;
	
	@Override
	public String toString() {
		return super.toString();
	}

	@NotNull(message="Date cannot be null")
	private Date date;
	
	@NotNull(message="Status cannot be null")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
