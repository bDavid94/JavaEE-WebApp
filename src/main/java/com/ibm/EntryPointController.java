package com.ibm;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Appointment;
import domain.Customer;
import domain.Employee;
import domain.Product;
import domain.User;
import service.AppointmentService;
import service.CustomerService;
import service.EmployeeService;
import service.ProductService;
import service.UserService;
import utils.Constants;

@WebServlet("/EntryPointController")
public class EntryPointController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EmployeeService employeeService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private AppointmentService appointmentService;
	
	@EJB
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryPointController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		initData();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		initData();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request,response);
	}
	
	private void initData() {
				
		User admin = new User();
		admin.setEmail("admin");
		admin.setPassword("admin");
		admin.setRole("admin");
		
		userService.addUser(admin);
	}
}
