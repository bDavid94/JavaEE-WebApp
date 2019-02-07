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

import dao.ProductPersistentBean;
import domain.Product;
import utils.Constants;

@Stateless
@LocalBean
@Startup
public class ProductService {

	@EJB
	ProductPersistentBean productPersistentBean;
	
	public void addProduct(HttpServletRequest request, 
						   HttpServletResponse response) {
		String name = request.getParameter("name");
		Float price = Float.parseFloat(request.getParameter("price"));
		Float duration = Float.parseFloat(request.getParameter("duration"));
		
		Product product = new Product();
		product.setDuration(duration);
		product.setName(name);
		product.setPrice(price);
		
		productPersistentBean.addProduct(product);
	}
	
	// for testing
	public void addProduct(Product product) {
		productPersistentBean.addProduct(product);
	}
	
	public void updateProduct(HttpServletRequest request, 
			 				  HttpServletResponse response) {
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("name");
		Float price = Float.parseFloat(request.getParameter("price"));
		Float duration = Float.parseFloat(request.getParameter("duration"));
		Product product = getProduct(productId);
		product.setName(name);
		product.setDuration(duration);
		product.setPrice(price);
		
		productPersistentBean.updateProduct(product);
		
		listProducts(request, response);	
	}
	
	public void deleteProduct(int productId) {
		productPersistentBean.deleteProduct(productId);
	}
	
	public Product getProduct(int productId) {
		return productPersistentBean.getProduct(productId);
	}
	
	public List<Product> getProducts() {
		return productPersistentBean.getProducts();
	}
	
	public void listProducts(HttpServletRequest request, 
							 HttpServletResponse response) {
		List<Product> theProducts = getProducts();
		request.setAttribute("products", theProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_LIST_PRODUCTS);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadProduct(HttpServletRequest request, 
							HttpServletResponse response) {
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		Product product = getProduct(productId);
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.JSP_UPDATE_PRODUCT_FORM);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

}
	
}
