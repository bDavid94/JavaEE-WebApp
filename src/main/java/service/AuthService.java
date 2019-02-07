package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import dao.UserPersistentBean;
import domain.User;

@Stateless
@LocalBean
public class AuthService {
	
	@EJB
	private UserPersistentBean userPersistentBean;
	
	public User authenticatUser(String email, String password) {
		return userPersistentBean.authenticateUser(email, password);
	}
	
	public void logOut() {
		
	}

}
