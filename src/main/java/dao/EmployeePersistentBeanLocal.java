package dao;

import java.util.List;

import javax.ejb.Local;

import domain.Customer;
import domain.Employee;

public interface EmployeePersistentBeanLocal {

	void addEmployee(Employee employee);
	
	List<Employee> getEmployees();
	
	Employee getEmployeeByEmail(String email);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployee(String employeeEmail);
}

