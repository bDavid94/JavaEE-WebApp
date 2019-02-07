package dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domain.User;

public interface UserPersistentBeanLocal {
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(String userEmail);
	
	public User getUserByEmail(String userEmail);
	
	public User authenticateUser(String userEmail, String password);
}
