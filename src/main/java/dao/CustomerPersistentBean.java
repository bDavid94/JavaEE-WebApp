package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Appointment;
import domain.Customer;
import domain.Employee;

@Stateless
@LocalBean
public class CustomerPersistentBean implements CustomerPersistentBeanLocal {
	
	public CustomerPersistentBean() {}

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private AppointmentPersistentBean appointmentPersistentBean;
	
	@Override
	public void addCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> resultList = (List<Customer>) entityManager.createQuery("select c from CUSTOMER c", Customer.class).getResultList();
		return resultList;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return entityManager.find(Customer.class, email);
	}

	@Override
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
	}

	@Override
	public void deleteCustomer(String customerEmail) {
		Appointment appointment = 
				appointmentPersistentBean.getAppointmentByCustomerEmail(customerEmail);
		if (appointment != null) {
			appointmentPersistentBean.deleteAppointment(appointment.getId());
		}
		entityManager.remove(getCustomerByEmail(customerEmail));
		
	}

}
