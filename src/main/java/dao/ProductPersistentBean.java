package dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Appointment;
import domain.Product;

@Stateless
@LocalBean
public class ProductPersistentBean implements ProductPersistentBeanLocal {
	
	@PersistenceContext(unitName="JPADemo")
	private EntityManager entityManager;

	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public List<Product> getProducts() {
		List<Product> resultList = (List<Product>) entityManager.createQuery("select p from PRODUCT p", Product.class).getResultList();
		return resultList;
	}

	@Override
	public Product getProduct(int productId) {
		return entityManager.find(Product.class, productId);
	}

	@Override
	public void deleteProduct(int productId) {
		List<Appointment> appointments = (List<Appointment>) entityManager
		.createQuery("select a from APPOINTMENT a where a.product.id = :productId")
		.setParameter("productId", productId)
		.getResultList();
		for (Appointment app: appointments) {
			entityManager.remove(app);
		}
		entityManager.remove(getProduct(productId));

	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

}
