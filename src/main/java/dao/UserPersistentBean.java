package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.User;

@Stateless
@LocalBean
public class UserPersistentBean implements UserPersistentBeanLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
			entityManager.merge(user);
	}

	@Override
	public void deleteUser(String userEmail) {
		entityManager.remove(getUserByEmail(userEmail));
	}
	
	@Override
	public User getUserByEmail(String userEmail) {
		return entityManager.find(User.class, userEmail);
	}

	@Override
	public User authenticateUser(String userEmail, String password) {
		try {
		return (User) entityManager
						.createQuery("select u from USER u where (u.email = :email "
								+ "AND u.password = :password)")
						.setParameter("email", userEmail)
						.setParameter("password", password).getSingleResult();
		} catch (Exception e) {
			System.out.println("No users found");
			return null;
		}
	}

}
