package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.Data;

@Entity
public class Role<T> implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
//	private Collection<T> user = new ArrayList<T>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
