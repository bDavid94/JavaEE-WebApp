package com.ibm;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import domain.Role;
import domain.User;
import service.AppointmentService;
import service.CustomerService;
import service.EmployeeService;
import service.ProductService;
import service.RoleService;
import service.UserService;
import utils.Constants;

@WebServlet("/hello")
public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = -4751096228274971485L;
	
	@EJB
	private RoleService roleService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private AppointmentService appointmentService;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private UserService userService;
	
	public SimpleServlet() {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = null;
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.authenticateUser(email, password);
		String role;
		if (user != null) {
			role = user.getRole();
			System.out.println("the role is" + role);
			HttpSession session = request.getSession();
			session.setAttribute("role", role);
			session.setAttribute("user", email);
		} else {
			role = null;
			request.setAttribute("invalidCredentials", true);
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if (role.equals("admin")) {
			dispatcher = request.getRequestDispatcher(Constants.JSP_ADMIN);
			dispatcher.forward(request, response);
		} else if (role.equals("customer")) {
			dispatcher = request.getRequestDispatcher(Constants.JSP_CUSTOMERS);
			dispatcher.forward(request, response);
		} else if (role.equals("employee")) {
			request.setAttribute("user", email);
			dispatcher = request.getRequestDispatcher(Constants.JSP_CUSTOMERS);
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}
