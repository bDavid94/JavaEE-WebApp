package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import utils.Constants;

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
import service.AppointmentService;
import service.CustomerService;
import service.EmployeeService;
import service.ProductService;

@WebServlet("/AppointmentController")
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AppointmentService appointmentService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private EmployeeService employeeService;
       
    public AppointmentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role.equals(Constants.ROLE_EMPLOYEE)) {
			
			String command = request.getParameter("command");
			
			if (command != null) {
				accept_declineAppointment(request, response, command);
				listEmployeeAppointments(request, response);
				return;
			}
			else {
				listEmployeeAppointments(request, response);
				return;
			}
				
				
		} else if (role.equals(Constants.ROLE_ADMIN)) {
			listAppointments(request, response);
			return;
		} else if (role.equals(Constants.ROLE_CUSTOMER)) {
			//listCustomerAppointments(request, response);
			
			String command = request.getParameter("command");
			
			if (command != null) {
				if(command.equals(Constants.COMMAND_DELETE)) {
					
					deleteAppointment(request, response);
//					setEmployeesAndProducts(request, response);
					listCustomerAppointments(request, response);
					System.out.println("List the apps here");
					return;
				}
			} else {
//				setEmployeesAndProducts(request, response);
				listCustomerAppointments(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		if (command != null) {
			if(command.equals(Constants.COMMAND_DELETE)) {
				deleteAppointment(request, response);
//				setEmployeesAndProducts(request, response);
				listCustomerAppointments(request, response);
				return;
			} else if (command.equals(Constants.COMMAND_ADD)) {
				int productId = Integer.parseInt(request.getParameter("product"));
				Product product = productService.getProduct(productId);
				System.out.println("Product:" + product.getName());
				String employeeEmail = request.getParameter("employee");
				Employee employee = employeeService.getEmployeeByEmail(employeeEmail);
				System.out.println("Employee:" + employee.getEmail());
				System.out.println("Date is: " + request.getParameter("appointmentDate"));
				Date date = new Date();
				System.out.println("Date:" + date);
				Customer customer = customerService.getCustomerByEmail(user);
				System.out.println("Customer:" + customer.getEmail());
				Appointment appointment = new Appointment();
				appointment.setEmployee(employee);
				appointment.setProduct(product);
				appointment.setDate(date);
				appointment.setCustomer(customer);
				appointment.setStatus(Constants.STATUS_PENDING);
				appointmentService.addAppointment(appointment);
				listCustomerAppointments(request, response);
				return;
			}
		}
	}
	
	private void listAppointments(HttpServletRequest request, 
								  HttpServletResponse response) {
		appointmentService.listAppointments(request, response);
	}
	private void listEmployeeAppointments(HttpServletRequest request, 
										  HttpServletResponse response) {
		appointmentService.listEmployeeAppointments(request, response);
		
	}
	
	private void listCustomerAppointments(HttpServletRequest request, 
			  							  HttpServletResponse response) {

		appointmentService.listCustomerAppointments(request, response);
}
	
	private void accept_declineAppointment(HttpServletRequest request, 
								   HttpServletResponse response,
								   String command) {
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		if (command.equals(Constants.COMMAND_ACCEPT)) {
			appointment.setStatus("Confirmed");
		} else if (command.equals(Constants.COMMAND_DECLINE)) {
			appointment.setStatus("Canceled");
		}
		appointmentService.updateAppointment(appointment);
	}
	
	private void deleteAppointment(HttpServletRequest request,
								   HttpServletResponse response) {
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		
		appointmentService.deleteAppointment(appointmentId);
		//listCustomerAppointments(request, response);
	}
	
	private void setEmployeesAndProducts(HttpServletRequest request,
										 HttpServletResponse response) {
		List<Product> products = productService.getProducts();
		List<Employee> employees = employeeService.getEmployees();
		request.setAttribute("products", products);
		request.setAttribute("employees", employees);
		
	}

}
