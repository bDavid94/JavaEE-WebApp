package dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Appointment;

@Stateless
@LocalBean
public class AppointmentPersistentBean implements AppointmentPersistentBeanLocal {
	
	public AppointmentPersistentBean() {}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addAppointment(Appointment appointment) {
		entityManager.persist(appointment);
	}

	@Override
	public List<Appointment> getAppointments() {
		return (List<Appointment>) entityManager
				.createQuery("select a from APPOINTMENT a")
				.getResultList();
	}

	@Override
	public List<Appointment> getAppointmentByEmployeeEmail(String employeeEmail) {
		return (List<Appointment>) entityManager
				.createQuery("select a from APPOINTMENT a where a.employee.email = :employeeEmail")
				.setParameter("employeeEmail", employeeEmail)
				.getResultList();
	}

	@Override
	public Appointment getAppointmentByCustomerEmail(String customerEmail) {
		try {return (Appointment) entityManager
				.createQuery("select a from APPOINTMENT a where a.customer.email = :customerEmail")
				.setParameter("customerEmail", customerEmail)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteAppointment(int appointmentId) {
		entityManager.remove(getAppointmentById(appointmentId));
	}
	
	@Override
	public void updateAppointment(Appointment appointment) {
		entityManager.merge(appointment);
	}
	
	@Override
	public Appointment getAppointmentById(int appointmentId) {
		return entityManager.find(Appointment.class, appointmentId);	
	}

}
