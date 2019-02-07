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

import domain.Product;
import service.ProductService;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProductService productService;
       
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
		try {
			switch (command) {
			case Constants.COMMAND_LIST:
				listProducts(request, response);
				break;
			case Constants.COMMAND_LOAD:
				loadProduct(request, response);
				break;
			case Constants.COMMAND_DELETE:
				deleteProduct(request, response);
				break;
			default:
				listProducts(request, response);
				break;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
			switch (command) {
			case Constants.COMMAND_LIST:
				listProducts(request, response);
				break;
			case Constants.COMMAND_ADD:
				addProduct(request, response);
				break;
			case Constants.COMMAND_LOAD:
				loadProduct(request, response);
				break;
			case Constants.COMMAND_UPDATE:
				updateProduct(request, response);
				break;
			default:
				listProducts(request, response);
				break;
			}
	}
	
	private void listProducts(HttpServletRequest request, 
							  HttpServletResponse response) {
		
			productService.listProducts(request, response);	
	}
	
	private void addProduct(HttpServletRequest request, 
							HttpServletResponse response) {
		
		productService.addProduct(request, response);
		
		listProducts(request, response);
	}
	
	private void loadProduct(HttpServletRequest request, 
							 HttpServletResponse response) {
		productService.loadProduct(request, response);
		
	}

	private void updateProduct(HttpServletRequest request, 
							   HttpServletResponse response) {
		productService.updateProduct(request, response);
	}
	
	private void deleteProduct(HttpServletRequest request, 
							   HttpServletResponse response) {
		int productId = Integer.parseInt(request.getParameter("productId"));
		productService.deleteProduct(productId);
		
		listProducts(request, response);
		
	}
	
}
