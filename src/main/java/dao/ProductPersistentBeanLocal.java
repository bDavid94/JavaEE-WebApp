package dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;

import domain.Product;

public interface ProductPersistentBeanLocal {

	void addProduct(Product product);
	
	List<Product> getProducts();
	
	Product getProduct(int productId);
	
	void deleteProduct(int productId);
	
	void updateProduct(Product product);
}
