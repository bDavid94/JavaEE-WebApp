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
		
//		Product prod1 = new Product();
//		prod1.setName("Tuns");
//		prod1.setDuration(60);
//		prod1.setPrice(35);
//		productService.addProduct(prod1);
//		
//		Product prod2 = new Product();
//		prod2.setName("Spalat");
//		prod2.setDuration(10);
//		prod2.setPrice(5);
//		productService.addProduct(prod2);
//		
//		Product prod3 = new Product();
//		prod3.setName("Freza");
//		prod3.setDuration(15);
//		prod3.setPrice(10);
//		productService.addProduct(prod3);
//		
//		Product prod4 = new Product();
//		prod4.setName("Full Service");
//		prod4.setDuration(80);
//		prod4.setPrice(45);
//		productService.addProduct(prod4);
		
//		Customer customer1 = new Customer();
//		customer1.setEmail("customer1");
//		customer1.setFirstName("Customer 1");
//		customer1.setLastName("Customer 1");
//		
//		User u9 = new User();
//		u9.setEmail(customer1.getEmail());
//		u9.setPassword("customer1");
//		u9.setRole(Constants.ROLE_CUSTOMER);
////		userService.addUser(u9);
//		
//		customerService.addCustomer(customer1);
//		
//		Customer customer2 = new Customer();
//		customer2.setEmail("customer2");
//		customer2.setFirstName("Customer 2");
//		customer2.setLastName("Customer 2");
//		
//		User u8 = new User();
//		u8.setEmail(customer2.getEmail());
//		u8.setPassword("customer2");
//		u8.setRole(Constants.ROLE_CUSTOMER);
////		userService.addUser(u8);
//		
//		customerService.addCustomer(customer2);
//		
//		Customer customer3 = new Customer();
//		customer3.setEmail("customer3");
//		customer3.setFirstName("Customer 3");
//		customer3.setLastName("Customer 3");
//		
//		User u7 = new User();
//		u7.setEmail(customer3.getEmail());
//		u7.setPassword("customer3");
//		u7.setRole(Constants.ROLE_CUSTOMER);
////		userService.addUser(u7);
//		
//		customerService.addCustomer(customer3);
//		
//		Customer customer5 = new Customer();
//		customer5.setEmail("customer5");
//		customer5.setFirstName("Customer 5");
//		customer5.setLastName("Customer 5");
//		User u6 = new User();
//		u6.setEmail(customer5.getEmail());
//		u6.setPassword("customer5");
//		u6.setRole(Constants.ROLE_CUSTOMER);
////		userService.addUser(u6);
//		
//		customerService.addCustomer(customer5);
//		
//		Customer customer6 = new Customer();
//		customer6.setEmail("customer6");
//		customer6.setFirstName("Customer 6");
//		customer6.setLastName("CUSTOMER6");
//		User u4 = new User();
//		u4.setEmail(customer6.getEmail());
//		u4.setPassword("customer6");
//		u4.setRole(Constants.ROLE_CUSTOMER);
////		userService.addUser(u4);
//		
//		customerService.addCustomer(customer6);
//		
//		Employee employee1 = new Employee();
//		employee1.setEmail("employee1");
//		employee1.setFirstName("Employee 1");
//		employee1.setLastName("Employee 1");
//		employee1.setPhoneNumber("1234");
//		employeeService.addEmployee(employee1);
//		
//		User uu1 = new User();
//		uu1.setEmail(employee1.getEmail());
//		uu1.setPassword("employee1");
//		uu1.setRole(Constants.ROLE_EMPLOYEE);
////		userService.addUser(uu1);
//		
//		Employee employee5 = new Employee();
//		employee5.setEmail("employee2");
//		employee5.setFirstName("Employee2");
//		employee5.setLastName("Employee2");
//		employee5.setPhoneNumber("12345");
//		employeeService.addEmployee(employee5);
//		User uu2 = new User();
//		uu2.setEmail(employee5.getEmail());
//		uu2.setPassword("employee2");
//		uu2.setRole(Constants.ROLE_EMPLOYEE);
////		userService.addUser(uu2);
//		
//		Employee employee2 = new Employee();
//		employee2.setEmail("employee3");
//		employee2.setFirstName("Employee 3");
//		employee2.setLastName("Employee 3");
//		employee2.setPhoneNumber("1234");
//		employeeService.addEmployee(employee2);
//		
//		User uu3 = new User();
//		uu3.setEmail(employee2.getEmail());
//		uu3.setPassword("employee3");
//		uu3.setRole(Constants.ROLE_EMPLOYEE);
////		userService.addUser(uu3);
//		
//		Appointment appointment1 = new Appointment();
//		appointment1.setCustomer(customer1);
//		appointment1.setDate(new Date());
//		appointment1.setEmployee(employee2);
//		appointment1.setProduct(prod1);
//		appointment1.setStatus("Confirmed");
//		
//		appointmentService.addAppointment(appointment1);
//		
//		Appointment appointment5 = new Appointment();
//		appointment5.setCustomer(customer5);
//		appointment5.setDate(new Date());
//		appointment5.setEmployee(employee5);
//		appointment5.setProduct(prod1);
//		appointment5.setStatus("Pending");
//		
//		appointmentService.addAppointment(appointment5);
//		
//		Appointment appointment6 = new Appointment();
//		appointment6.setCustomer(customer6);
//		appointment6.setDate(new Date());
//		appointment6.setEmployee(employee5);
//		appointment6.setProduct(prod1);
//		appointment6.setStatus("Confirmed");
//		
//		appointmentService.addAppointment(appointment6);
//		
//		Appointment appointment2 = new Appointment();
//		appointment2.setCustomer(customer3);
//		appointment2.setDate(new Date());
//		appointment2.setEmployee(employee2);
//		appointment2.setProduct(prod1);
//		appointment2.setStatus("Confirmed");
//		
//		appointmentService.addAppointment(appointment2);
//		
//		Appointment appointment3 = new Appointment();
//		appointment3.setCustomer(customer2);
//		appointment3.setDate(new Date());
//		appointment3.setEmployee(employee2);
//		appointment3.setProduct(prod2);
//		appointment3.setStatus("Canceled");
//		
//		appointmentService.addAppointment(appointment3);

	}

}
