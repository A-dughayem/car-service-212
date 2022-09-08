
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	BST<K, Location> map;
	TreeLocator<K> tree;
	
	
	public TreeLocatorMap() {
		map = new BST<K, Location> ();
		tree= new TreeLocator<K> ();
	}

	@Override
	
	
	public Map<K, Location> getMap() {
		
		
		
		return map;
	}

	@Override
	public Locator<K> getLocator() {
		// TODO Auto-generated method stub
		return tree;
	} 
	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		
		
		Pair<Boolean, Integer> out ; 
		 out = map.insert(k, loc);
		 if(out.first)
			 out.second += tree.add(k, loc);
		return out;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		 Pair<Boolean, Integer> out ; 
		out = map.find(k);
		
		if(out.first) {
			BNode<K,Location> oldVersion = map.current;
			out.second += (tree.remove(k, oldVersion.data).second);
			map.update(loc);
			out.second += tree.add(k, loc);
			
		}
		
		return out;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		 Pair<Location, Integer> out = new Pair<Location, Integer>(null, null) ; 
		 Pair<Boolean, Integer> found= map.find(k);
		// System.out.println(found.first);
		 if(found.first) {
			 out.first= map.retrieve();
		 }
		 out.second = found.second;
		
		return out;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		//System.out.println("tree remove");
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> out = new Pair<Boolean, Integer> (null, null);
		Pair<Boolean, Integer> found= map.find(k);
		out.first = found.first;
		out.second = found.second;
		//System.out.println("found");
		if(found.first) {
			out.second += tree.remove(k, map.current.data).second;
			out.second += map.remove(k).second;
		}
	
		
		
		return out;
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		return map.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
	//	System.out.println("treeMap getInRange");
		
		Pair<List<K>, Integer> out = new Pair<List<K>, Integer>(null, null) ;
		Pair<List<Pair<Location, List<K>>>, Integer> foundInRange = tree.inRange(lowerLeft, upperRight);
		Pair<Location, List<K>> ll  = foundInRange.first.retrieve();
		if(ll != null)
		out.first = foundInRange.first.retrieve().second; 
		out.second = foundInRange.second; 
		return out;
	} 

}




























