package dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domain.Role;

public interface RolePersistentBeanLocal {

	boolean addRole(Role role);
	
	void deleteRole(Role role);
	
	List<Role> getRoles();
	
	String test();
}
