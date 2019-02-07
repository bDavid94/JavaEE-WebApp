package dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Role;

@Stateless
@LocalBean
public class RolePersistentBean implements RolePersistentBeanLocal {
	
	public RolePersistentBean() {
		System.out.println("Role Persistent Bean instantiated");
	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String test() {
		return "Hello from persistent bean";
		
	}

}
