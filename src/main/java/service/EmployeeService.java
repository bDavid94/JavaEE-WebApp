package service;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerPersistentBean;
import dao.EmployeePersistentBean;
import domain.Customer;
import domain.Employee;
import domain.User;
import utils.Constants;

@Stateless
@LocalBean
public class EmployeeService {
	
	@EJB
	private EmployeePersistentBean employeePersistentBean;
	
	@EJB
	private UserService userService;
	
	public EmployeeService() {
		super();
	}
	
	public List<Employee> getEmployees() {
		return employeePersistentBean.getEmployees();
	}
	
	public Employee getEmployeeByEmail(String email) {
		return employeePersistentBean.getEmployeeByEmail(email);
	}
	
	// for testing
	public void addEmployee(Employee employee) {
		employeePersistentBean.addEmployee(employee);
	}
	
//	public void addEmployee(HttpServletRequest request,
//							HttpServletResponse response) {
//
//		String email = request.getParameter("email");
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String phoneNumber = request.getParameter("phoneNumber");
//		
//		Employee employee = new Employee();
//		employee.setEmail(email);
//		employee.setFirstName(firstName);
//		employee.setLastName(lastName);
//		employee.setPhoneNumber(phoneNumber);
//		
//		System.out.println("service add employee");
//		
//		employeePersistentBean.addEmployee(employee);
//	}
	
	public void deleteEmployee(String employeeEmail) {
		employeePersistentBean.deleteEmployee(employeeEmail);
	}
	
	public void updateEmployee(HttpServletRequest request,
							   HttpServletResponse response) {
		
		String employeeEmail = request.getParameter("employeeEmail");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		Employee employee = getEmployeeByEmail(employeeEmail);
		employee.setEmail(employeeEmail);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setPhoneNumber(phoneNumber);
		
		User user = userService.getUserByEmail(employeeEmail);
		user.setPassword(password);
		userService.updateUser(user);
		
		System.out.println("Service update employee");

		employeePersistentBean.updateEmployee(employee);
	}
	
	public void listEmployees(HttpServletRequest request,
							  HttpServletResponse response) {
		List<Employee> theEmployees = getEmployees();
		request.setAttribute("employees", theEmployees);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_LIST_EMPLOYEES);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
