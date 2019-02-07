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
import javax.servlet.http.HttpSession;

import dao.AppointmentPersistentBean;
import domain.Appointment;
import domain.Employee;
import domain.Product;
import utils.Constants;

@Stateless
@LocalBean
public class AppointmentService {
	
	@EJB
	private AppointmentPersistentBean appointmentPeristentBean;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private EmployeeService employeeService;
	
	public AppointmentService() {
		super();
	}
	
	public void addAppointment(Appointment appointment) {
		appointmentPeristentBean.addAppointment(appointment);
	}
	
	public void updateAppointment(Appointment appointment) {
		appointmentPeristentBean.updateAppointment(appointment);
	}
	
	public void deleteAppointment(int appointmentId) {
		appointmentPeristentBean.deleteAppointment(appointmentId);
	}
	
	private List<Appointment> getAppointmentByEmployeeEmail(String employeeEmail) {
		System.out.println("*****************");
		return	appointmentPeristentBean.getAppointmentByEmployeeEmail(employeeEmail);
	}
	
	private Appointment getAppointmentByCustomerEmail(String customerEmail) {
		return appointmentPeristentBean.getAppointmentByCustomerEmail(customerEmail);
	}
	
	public Appointment getAppointmentById(int appointmentId) {
		return appointmentPeristentBean.getAppointmentById(appointmentId);
	}
	
	private List<Appointment> getAppointments() {
		return appointmentPeristentBean.getAppointments();
	}

	public void listAppointments(HttpServletRequest request,
								 HttpServletResponse response) {
		
		List<Appointment> appointments = getAppointments();
		
		request.setAttribute("appointments", appointments);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_ADDMIN_APPOINTMENTS);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listEmployeeAppointments(HttpServletRequest request,
										 HttpServletResponse response) {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		List<Appointment> appointments = getAppointmentByEmployeeEmail(user);
		System.out.println("The appointments*********:" + appointments);
		request.setAttribute("appointments", appointments);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_LIST_EMPLOYEE_APPOINTMENTS);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
	
	public void listCustomerAppointments(HttpServletRequest request,
			 HttpServletResponse response) {
		
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("user");
			Appointment appointment = getAppointmentByCustomerEmail(user);
			List<Product> products = productService.getProducts();
			List<Employee> employees = employeeService.getEmployees();
			request.setAttribute("products", products);
			request.setAttribute("employees", employees);
			if (appointment != null) {
				
				request.setAttribute("appointment", appointment);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_LIST_CUSTOMER_APPOINTMENTS);
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
}		
}
