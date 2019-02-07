package controller;

import java.io.IOException;
import java.util.List;
import utils.Constants;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Customer;
import domain.Product;
import domain.User;
import service.CustomerService;
import service.UserService;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private UserService userService;
       
    public CustomerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
			switch (command) {
			case Constants.COMMAND_DELETE:
				deleteCustomer(request, response);
				break;
			case Constants.COMMAND_ADD:
				addCustomer(request, response);
				break;
			case Constants.COMMAND_LIST:
				listCustomers(request, response);
				break;
			default:
				listCustomers(request, response);
				break;
			}
		}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		System.out.println("add useeer");
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
			switch (command) {
			case Constants.COMMAND_DELETE:
				deleteCustomer(request, response);
				break;
			case Constants.COMMAND_ADD:
				addCustomer(request, response);
				break;
			case Constants.COMMAND_LIST:
				listCustomers(request, response);
				break;
			default:
				listCustomers(request, response);
				break;
			}
	}
	
	private void deleteCustomer(HttpServletRequest request, 
								HttpServletResponse response) {
		String customerEmail = request.getParameter("customerEmail");
		userService.deleteUser(customerEmail);
		customerService.deleteCustomer(customerEmail);
		
		listCustomers(request, response);
		
	}
	
	private void listCustomers(HttpServletRequest request, 
							   HttpServletResponse response) {

			List<Customer> customers = customerService.getCustomers();
			request.setAttribute("customers", customers);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_LIST_CUSTOMERS);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void addCustomer(HttpServletRequest request,
							 HttpServletResponse response) {
		RequestDispatcher dispatcher = null;
		String email = request.getParameter("email");
		User user = userService.getUserByEmail(email);
		if (user != null) {
			dispatcher = request.getRequestDispatcher(Constants.JSP_ADD_CUSTOMER_FORM);
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
			User new_user = new User();
			new_user.setEmail(email);
			new_user.setPassword(password);
			new_user.setRole(Constants.ROLE_CUSTOMER);
			userService.addUser(new_user);
			
			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customerService.addCustomer(customer);
			
			dispatcher = request.getRequestDispatcher(Constants.JSP_LOGIN);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

}
