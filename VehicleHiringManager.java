public class VehicleHiringManager {
	TreeLocatorMap<String> tm;

	public VehicleHiringManager() {
		tm = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		
		return tm;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		tm= (TreeLocatorMap<String>) locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		
		
		return tm.add(id, loc).first ;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		return tm.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		return tm.remove(id).first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return tm.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		int x = loc.x;
		int y = loc.y;
		
		if (x<0)
			x=0;
		if(y<0)
			y=0;
		Location lowerlift = new Location(loc.x - r, loc.y - r);
		Location upperright = new Location(loc.x + r, loc.y + r);
		if (r > x)
		lowerlift.x = 0;
		if(r>y)
		lowerlift.y = 0;
		return tm.getInRange(lowerlift, upperright).first;
	}
}
