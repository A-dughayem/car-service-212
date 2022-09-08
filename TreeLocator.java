
public class TreeLocator<T> implements Locator<T> {
	NodeTree<T> root , current;
	
	
	
	public TreeLocator() {
		root = null;
		current = null;
	}
	

	@Override
	public int add(T e, Location loc) {
		
		
		int counter = 0;
		NodeTree<T>  p = new NodeTree<T>(loc,e);
		if(empty()) {
			root = current = p;
			return counter ;
		}
		 current = root;
		 NodeTree<T>  previous = null;
		int i = 0;
		while(current!= null) {
			
		if (p.u < current.u && p.v <= current.v) {
			previous = current;
			current = current.c1;
			counter += 1;
			i =1;
		}
		else if (p.u <= current.u && p.v > current.v) {
			previous = current;
			current = current.c2;
			counter += 2;
			i=2;
		}else if (p.u > current.u && p.v >= current.v) {
			previous = current;
			current = current.c3;
			counter += 3;
			i=3;
		}else if (p.u >= current.u && p.v < current.v) {
			previous = current;
			current = current.c4;
			counter += 4;
			i=4;
		}else if (p.u == current.u && p.v ==current.v) {
			previous = current;
			
			i=5;
			break;
		}
		}
		if (i==1)
			previous.c1 = p;
		else if (i==2)
		previous.c2 = p; 
		else if (i==3)
			previous.c3 = p;
		else if (i==4)
			previous.c4 = p;
		else if (i==5)
			previous.key = (T)((String)previous.key).concat(", " + (String)p.key);
		else
			System.out.println("error not inserted");
		
		//System.out.println("the current key is " + previous.key);
		return counter;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		Pair<List<T> , Integer> out ;
		
		Pair<NodeTree<T>, Integer> found = find(loc);
		
		
		LinkedList<T> list = new LinkedList<T>();
		if (found.first != null) {
		T keys = found.first.key;
		
		if (found.first != null) {
	
					
				
			list.insert(keys);
		
		}
		}
		
		int conter = found.second ;
		
		
		out = new Pair<List<T> , Integer> (list , conter);
		return out;
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		
		 Pair<Boolean, Integer> out ;
		// System.out.println("tree loc start");
		 Boolean done = false;
		 Integer count = 0;
		 Pair<NodeTree<T>, Integer> found = find(loc); // return makes the current in found tree
		 count = found.second; 
		 T keys = found.first.key;
		// System.out.println(" the keys are " + keys);			
	
		if(((String)keys).matches((String)e)) {
			
	
		current.key = (T) ((String)current.key).replace((CharSequence) e, "");
			
			
		//System.out.println("the keys now "+ current.key);
		
			done = true;
		}
	
		 
		 
		
		out = new  Pair<Boolean, Integer> (done , count);
		//System.out.println("tree loc over" + done + count);
		return out ;
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> out;
		out = new LinkedList<>();
		if (empty())
			return out;
		
		NodeTree<T> p = root;
		availabile(p, out);
		
		
		
		return out;
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
	//	System.out.println("tree inRange");
		Integer counter =0;
		List<Pair<Location, List<T>>> listAndLoc = new LinkedList<Pair<Location,List<T>>>(); 
		LinkedList <T> listOfString =  new LinkedList<T>();
		
		
		NodeTree<T> p = root;
		//System.out.println("start getsome");
		
		getsome(listAndLoc, p, lowerLeft, upperRight, counter, listOfString);
		p = root;
		counter = numberofrounds(p, lowerLeft, upperRight);
		
		//System.out.println("counter is " + counter );
		 Pair<List<Pair<Location, List<T>>>, Integer> out = new  Pair<List<Pair<Location, List<T>>>, Integer> (listAndLoc, counter);
		 if (!out.first.empty())
		 out.first.retrieve().second = listOfString; 
	//	System.out.println("out of tree inRAnge");
		return out;
	}
	private int numberofrounds (NodeTree<T> p, Location lowerLeft, Location upperRight) {
		if(p==null) return 0;
		int c1=0 ,c2=0,c3=0,c4=0;
		if(p.u > lowerLeft.x && p.v >= lowerLeft.y)
		c1 = numberofrounds(p.c1, lowerLeft, upperRight);
		if(p.u >= lowerLeft.x && p.v < upperRight.y)
		c2 = numberofrounds(p.c2, lowerLeft, upperRight);
		if(p.u < upperRight.x && p.v <= upperRight.y)
		c3 = numberofrounds(p.c3, lowerLeft, upperRight);
		if(p.u <= upperRight.x && p.v > lowerLeft.y)
		c4 = numberofrounds(p.c4, lowerLeft, upperRight);
		return 1 + c1 + c2 + c3 + c4 ;

		
	}
	
	private void getsome(List<Pair<Location, List<T>>> list , NodeTree<T> p, Location lowerLeft, Location upperRight, Integer conter, List<T> keys) {
		if(p==null) return ;
		
		
	//	System.out.println("some"); 
		if (p.u >= lowerLeft.x && p.u <= upperRight.x && p.v >= lowerLeft.y && p.v <= upperRight.y && !((String)p.key).isEmpty()) {
			List <T> templist =  new LinkedList<>();
			keys.insert(p.key);
			templist.insert(p.key);
			Location l = new Location(0, 0);
			l.x = p.u;
			l.y= p.v;
			Pair<Location, List<T>> PLL = new Pair<Location, List<T>> (l,templist);
			list.insert(PLL);
			conter++;
			
		}
	
		
		
		if(p.u > lowerLeft.x && p.v >= lowerLeft.y)  {  // recursive to all possible outcomes
		//	System.out.println(p.key+">c1");
			conter++;
			getsome(list, p.c1, lowerLeft, upperRight, conter,keys);
		}
			if(p.u >= lowerLeft.x && p.v < upperRight.y) {
			//	System.out.println( p.key +">c2");
				conter++;
			getsome(list, p.c2, lowerLeft, upperRight, conter,keys);	
			}
		if(p.u < upperRight.x && p.v <= upperRight.y) {
			//System.out.println(p.key+">c3");
			conter++;
			getsome(list, p.c3, lowerLeft, upperRight, conter,keys);
		}if(p.u <= upperRight.x && p.v > lowerLeft.y) {
		//	System.out.println(p.key+">c4");
			conter++;
			getsome(list, p.c4, lowerLeft, upperRight, conter,keys);
		}
			
		
		
	}

	private void availabile(NodeTree<T> t,List<Pair<Location, List<T>>> o ){ // recursive that insert all ava child into out
		if(t == null) return ;
		o.insert(locList(t));
		if(t.c1 != null)
			 availabile(t.c1,o);
		else if(t.c2 !=null)
				availabile(t.c2,o);
		else if( t.c3 != null)
					availabile(t.c3,o);
		else if (t.c4!= null)
					availabile(t.c4,o);
		return ;
	}
	
	private Pair <Location,List<T>> locList(NodeTree<T> p){
		Pair<Location,List<T>> temp = null;
		Location loc = new Location(p.u,p.v);
			LinkedList<T> list = (LinkedList<T>) get(loc).first;
			temp = new Pair<Location,List<T>> (loc,list );
			return temp;
	}

	private boolean empty() {
		
		return root == null;
	}
	private Pair<NodeTree<T>, Integer> find(Location l){ // returns null if not found + counter 
		current = root;
		int counter = 0;
		while(current!= null) {
			counter++;
		if (l.x < current.u && l.y <= current.v) {
			current = current.c1;
		//	counter += 1;
			
		}
		else if (l.x <= current.u && l.y > current.v) {
			current = current.c2;
			//counter += 2;
			
		}else if (l.x > current.u && l.y >= current.v) {
			current = current.c3;
			//counter += 3;
		
		}else if (l.x >= current.u && l.y < current.v) {
			current = current.c4;
			//counter += 4;
		
		}else if (l.x== current.u && l.y ==current.v) {
			break;
		}
	}
		return new Pair<NodeTree<T>, Integer> (current, counter) ;
}

}







































