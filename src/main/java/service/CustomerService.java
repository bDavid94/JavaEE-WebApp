package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import dao.CustomerPersistentBean;
import domain.Customer;

@Stateless
@LocalBean
public class CustomerService {
	
	@EJB
	private CustomerPersistentBean customerPersistentBean;
	
	public CustomerService() {}
	
	public List<Customer> getCustomers() {
		return customerPersistentBean.getCustomers();
	}
	
	public Customer getCustomerByEmail(String customerEmail) {
		return customerPersistentBean.getCustomerByEmail(customerEmail);
	}
	
	public void addCustomer(Customer customer) {
		customerPersistentBean.addCustomer(customer);
	}
	
	public void updateCustomer(Customer customer) {
		customerPersistentBean.updateCustomer(customer);
	}
	
	public void deleteCustomer(String customerEmail) {
		customerPersistentBean.deleteCustomer(customerEmail);
	}
	

}
