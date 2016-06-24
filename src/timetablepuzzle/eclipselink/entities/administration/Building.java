package timetablepuzzle.eclipselink.entities.administration;

public class Building {
	private int _externalId;
	private String _name;
	private String _abreviation;
	private Location _location;
	
	/**
	 * Create and initialize a building with given parameters
	 * @param externalID
	 * @param name
	 * @param abreviation
	 * @param location
	 */
	public Building(int externalID, String name, String abreviation, Location location)
	{
		_externalId = externalID;
		set_name(name);
		set_abreviation(abreviation);
		if(location != null)
		{
			set_location(location);
		}else{
			set_location(new Location());
		}
	}

	public int get_externalId() {
		return _externalId;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_abreviation() {
		return _abreviation;
	}

	public void set_abreviation(String _abreviation) {
		this._abreviation = _abreviation;
	}

	public Location get_location() {
		return _location;
	}

	public void set_location(Location _location) {
		this._location = _location;
	}
}