package dao;

import java.util.List;

import javax.ejb.Local;

import domain.Customer;

public interface CustomerPersistentBeanLocal {

	void addCustomer(Customer customer);
	
	List<Customer> getCustomers();
	
	Customer getCustomerByEmail(String email);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(String email);
}
