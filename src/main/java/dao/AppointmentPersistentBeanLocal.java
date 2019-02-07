package dao;

import java.util.List;

import javax.ejb.Local;

import domain.Appointment;

public interface AppointmentPersistentBeanLocal {
	
	void addAppointment(Appointment appointment);
	
	List<Appointment> getAppointments();
	
	List<Appointment> getAppointmentByEmployeeEmail(String employeeEmail);
	
	Appointment getAppointmentByCustomerEmail(String customerEmail);
	
	Appointment getAppointmentById(int appointmentId);
	
	void deleteAppointment(int appointmentId);
	
	void updateAppointment(Appointment appointment);
}
