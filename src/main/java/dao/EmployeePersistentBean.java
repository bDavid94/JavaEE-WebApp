package dao;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import domain.Appointment;
import domain.Employee;
import domain.Product;

@Stateless
@LocalBean
public class EmployeePersistentBean implements EmployeePersistentBeanLocal{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addEmployee(Employee employee) {
		System.out.println("***********************************************");
		System.out.println(employee.toString());
		entityManager.persist(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> resultList = (List<Employee>) entityManager.createQuery("select e from EMPLOYEE e", Employee.class).getResultList();
		return resultList;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return entityManager.find(Employee.class, email);
	}


	@Override
	public void updateEmployee(Employee employee) {
		try {
			entityManager.merge(employee);
		} catch (ConstraintViolationException e) {
		       
		       e.getConstraintViolations().forEach(err-> System.out.println(err));
		    }
		
	}

	@Override
	public void deleteEmployee(String employeeEmail) {
		List<Appointment> appointments = (List<Appointment>) entityManager
		.createQuery("select a from APPOINTMENT a where a.employee.email = :employeeEmail")
		.setParameter("employeeEmail", employeeEmail)
		.getResultList();
		
		for(Appointment app: appointments) {
			entityManager.remove(app);
		}
		entityManager.remove(getEmployeeByEmail(employeeEmail));
	}

}
