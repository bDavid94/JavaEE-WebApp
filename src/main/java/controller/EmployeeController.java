package controller;

import java.io.IOException;
import utils.Constants;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Appointment;
import domain.Customer;
import domain.Employee;
import domain.Product;
import domain.User;
import service.AppointmentService;
import service.EmployeeService;
import service.ProductService;
import service.UserService;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private AppointmentService appointmentService;
	
	@EJB
	private UserService userService;
       
    public EmployeeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("employeeEmail");
		String role = (String) session.getAttribute("role");
		if (role.equals(Constants.ROLE_ADMIN)) {
		String command = request.getParameter("command");
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
		try {
			switch (command) {
			case Constants.COMMAND_LIST:
				listEmployees(request, response);
				break;
			case Constants.COMMAND_LOAD:
				loadEmployee(request, response);
				break;
			case Constants.COMMAND_DELETE:
				deleteUser(email);
				deleteEmployee(request, response);
				listEmployees(request, response);
				break;
			default:
				listEmployees(request, response);
				break;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
		
}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		
		if (role.equals(Constants.ROLE_ADMIN)) {
			String command = request.getParameter("command");
			if (command == null) {
				command = Constants.COMMAND_LIST;
			}
			switch (command) {
				case Constants.COMMAND_LIST:
					listEmployees(request, response);
					break;
				case Constants.COMMAND_ADD:
					addEmployee(request, response);
					listEmployees(request, response);
					break;
				case Constants.COMMAND_LOAD:
					loadEmployee(request, response);
					break;
				case Constants.COMMAND_UPDATE:
					updateUser(request, response);
					updateEmployee(request, response);
					listEmployees(request, response);
					break;
				default:
					listEmployees(request, response);
					break;
				}
		}
	}
	
	private void listEmployees(HttpServletRequest request, 
							   HttpServletResponse response) {
		employeeService.listEmployees(request, response);
	}
	
	private void addEmployee(HttpServletRequest request, 
							 HttpServletResponse response) {
		
		RequestDispatcher dispatcher = null;
		String email = request.getParameter("email");
		User user = userService.getUserByEmail(email);
		if (user != null) {
			dispatcher = request.getRequestDispatcher(Constants.JSP_ADD_EMPLOYEE_FORM);
			request.setAttribute("email_exists", true);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phone = request.getParameter("phoneNumber");
			User new_user = new User();
			new_user.setEmail(email);
			new_user.setPassword(password);
			new_user.setRole(Constants.ROLE_EMPLOYEE);
			userService.addUser(new_user);
			
			Employee employee = new Employee();
			employee.setEmail(email);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setPhoneNumber(phone);
			employeeService.addEmployee(employee);
		}
	}
	
	
	
	private void loadEmployee(HttpServletRequest request, 
							  HttpServletResponse response) {
		String employeeEmail = request.getParameter("employeeEmail");
		HttpSession session = request.getSession();
		
		Employee employee = employeeService.getEmployeeByEmail(employeeEmail);
		session.setAttribute("lastLoadedEmployee", employee);
	
		request.setAttribute("employee", employee);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_UPDATE_EMPLOYEE_FORM);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void updateEmployee(HttpServletRequest request, 
								HttpServletResponse response) {
		
		employeeService.updateEmployee(request, response);
	}
	
	private void deleteEmployee(HttpServletRequest request, 
								HttpServletResponse response) {
		
		String employeeEmail = request.getParameter("employeeEmail");
		employeeService.deleteEmployee(employeeEmail);
		
	}
	
	private void deleteUser(String email) {
		userService.deleteUser(email);
	}
	
	private void addUser(HttpServletRequest request,
						 HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User exUser = userService.getUserByEmail(email);
		if (exUser == null) {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(Constants.ROLE_EMPLOYEE);
			userService.addUser(user);
		} else {
			request.setAttribute("user_exists", true);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(Constants.JSP_ADD_EMPLOYEE_FORM);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void updateUser(HttpServletRequest request,
							HttpServletResponse response) {
		HttpSession session = request.getSession();
		Employee employeeToUpdate = (Employee) session.getAttribute("lastLoadedEmployee");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("fisrtName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		User user = userService.getUserByEmail(employeeToUpdate.getEmail());
		user.setPassword(password);
		userService.updateUser(user);
	}
}
