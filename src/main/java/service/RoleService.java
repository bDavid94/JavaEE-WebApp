package service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.RolePersistentBean;

@Stateless
@LocalBean
public class RoleService {

	@EJB
	private RolePersistentBean rolePersistentBean;
	
	public RoleService() {
		System.out.println("Role service instantiated");
	}
	
	public String test() {	
		return rolePersistentBean.test();
	}
}
