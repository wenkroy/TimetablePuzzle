package timetablepuzzle.eclipselink.entities.administration;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="faculties")
public class Faculty {
	@Id
	@Column(name="external_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _externalId;
	
	@Column(name="name")
	private String _name;
	
	@OneToMany(targetEntity=Department.class)
	@JoinTable(name="faculty_departments",
    joinColumns=
         @JoinColumn(name="faculty_external_id"),
    inverseJoinColumns=
         @JoinColumn(name="department_external_id"))
	private List<Department> _departments;
	
	/**
	 * Default constructor
	 */
	public Faculty()
	{
		this(0,"NoName", new ArrayList<Department>());
	}
	
	/**
	 * Parameterized constructor
	 * @param externalId
	 * @param name
	 * @param department
	 */
	public Faculty(int externalId, String name, List<Department> departments)
	{
		_externalId = externalId;
		set_name(name);
		_departments = departments;
	}
	/********************Getters and setters****************/
	public int get_externalId() {
		return _externalId;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public List<Department> get_departments() {
		return _departments;
	}
	/*******************Methods that model the class behavior*******************/
}
