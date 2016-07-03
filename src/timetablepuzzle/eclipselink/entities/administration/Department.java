package timetablepuzzle.eclipselink.entities.administration;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import timetablepuzzle.eclipselink.entities.E;

@Entity
@Table(name="departments")
public class Department extends E{
	/***********Regular properties*************/
	@Column(name="name")
	private String _name;
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=YearOfStudy.class)
	@JoinTable(name="department_yearsofstudy",
    joinColumns=
         @JoinColumn(name="department_external_id"),
    inverseJoinColumns=
         @JoinColumn(name="yearofstudy_external_id"))
	private List<YearOfStudy> _yearsOfStudy; 
	
	/**
	 * Default constructor
	 */
	public Department()
	{
		this(0, "NoName", new ArrayList<YearOfStudy>());
	}
	
	/**
	 * Parameterized constructor
	 * @param externalId
	 * @param name
	 * @param areas
	 */
	public Department(int externalId, String name, List<YearOfStudy> yearsOfStudy)
	{
		_externalId = externalId;
		set_name(name);
		_yearsOfStudy = yearsOfStudy;
	}
	
	/********************Getters and setters****************/
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}
	
	public List<YearOfStudy> get_yearsOfStudy()
	{
		return this._yearsOfStudy;
	}
	
	/*******************Methods that model the class behavior*******************/
}
