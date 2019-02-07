package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.UserPersistentBean;
import domain.User;

@Stateless
@LocalBean
public class UserService {
	
	@EJB
	private UserPersistentBean userPersistentBean;
	
	public void addUser(User user) {
		userPersistentBean.addUser(user);
	}

	public void updateUser(User user) {
		userPersistentBean.updateUser(user);
	}

	public void deleteUser(String userEmail) {
		userPersistentBean.deleteUser(userEmail);
	}
	
	public User getUserByEmail(String userEmail) {
		return userPersistentBean.getUserByEmail(userEmail);
	}
	
	public User authenticateUser(String userEmail, String password) {
		return userPersistentBean.authenticateUser(userEmail, password);
	}
}
