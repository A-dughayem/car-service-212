
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	
	BNode<K,T> root, current, addhere;
	

	
	public BST() {
		root = current = addhere=null;
	}

	
	public boolean empty() {
		
		return root == null;
	}
	

	
	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) { //maybe change counter
	//	System.out.println("find " + key );
		BNode<K,T> oldCurrent = current ;
		current = root;
		if(empty())
			return new Pair<Boolean, Integer>(false, 0);
		//BSTNode<T> oldCurrent = current;
		//current = root;
		
		int counter = 0;
		
		while(current != null) {
		if ((current.key.compareTo(key))==0) {
		//	System.out.println("found");
			addhere = null;
			counter++;
			
			return new Pair<Boolean, Integer>(true, counter);
		}
			if ((current.key.compareTo( key)) > 0) {
			//System.out.println(p.key);
			//System.out.print("L");
			addhere= current;
			current = current.left;
			counter++;
		}
		else if ((current.key.compareTo( key)) < 0) {
			//System.out.println(p.key);
			//System.out.print("R");
			addhere = current;
			current = current.right;
			counter++;
		}
			
		}
		
		//System.out.println("not found");
			current = oldCurrent;
		
			return new Pair<Boolean, Integer>(false, counter);
		
		
		
		
		
		
		
		
		//Pair<Boolean, Integer> output = new Pair<Boolean, Integer>();
	
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		//System.out.println("inserting "+ key);
	
		
		Pair<Boolean, Integer> found = find(key);
		
		if (found.first) {
		
			return new Pair<Boolean, Integer>(false, found.second);
		}

		BNode<K,T>  p = new BNode<K,T>(key, data);
		if (empty()) {
			root = current = p;
			//System.out.println( key+ " inserted Root");
			return  new Pair<Boolean, Integer>(true, found.second);
		}
		else {
			// ADDHERE  is pointing to parent of the new key
			if (p.key.compareTo(addhere.key) < 0) {
					addhere.left = p;//System.out.println(key + " inserted left of " + addhere.key);
					current = addhere.left;
					
					return new Pair<Boolean, Integer>(true, found.second + 1);
			}
			
			else  {
					addhere.right = p;//System.out.println(key + " inserted right of " + addhere.key);
					current = addhere.right;
					
					return new Pair<Boolean, Integer>(true, found.second + 1);
			}
			
			
			
			 //  (done) adding +1 to for the last compaision ** maybe need to add depending on right or left
		}
		

		
	
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		Pair<Boolean, Integer> found = find(key);
		
		if(!found.first) {
			return new Pair<Boolean, Integer>(false, found.second);
		}
		else {// current is pointing to the key
			int count = removeKey(key);
			return new Pair<Boolean, Integer>(true, count);
			
		}
	}

	private int removeKey(K k) {
		int count = 0;
	      K k1 = k;     BNode<K,T> p = root;      BNode<K,T> q = null; 
	      //System.out.println("root is " +p.key);
	      while (p != null) {
	    	// System.out.println("compairing "+ k1 + " TO " + p.key);
	    	  if(p.key.compareTo( k1) > 0) {
	    		  // System.out.println("compared sma");
	    		  count++;
	    		  q=p;
	    		  p=p.left;
	    		  continue;
	    	  }
	    	  else if(p.key.compareTo( k1) < 0) {
	    		  //System.out.println("compaired big");
	    		  count++;
	    		  q=p;
	    		  p=p.right;
	    		continue;
	    	  }
	    	  else { // found the key // might need to change the scope of while loop
	              if ((p.left != null) && (p.right != null)) {
	            	//  System.out.println("case 3");
	            	  BNode<K,T> min =p.right;
	            	  while (min.left != null) {               q = min;                  min = min.left;           }
	            	 // System.out.println("min is " + min.key);
	            	  p.key = min.key;
	            	  p.data=min.data;
	            	  k1= min.key; 
	            	  p=min;
	            	  
	              }
	              if(p.left != null)
	            	  p= p.left;
	              else 
	            	  p=p.right;
	              if(q==null)
	            	  root=p;
	              else {
	            	  
	            	  if(k1.compareTo(((K) q.key)) < 0)
	            		  q.left=p;
	            	  else 
	            		  q.right=p;
	            	  count++;
	            	//  System.out.println("linlked " + q.key + " to "+ p.key);
	            	  break;
	              }
	    	  }
	      }
	    	  
		
		return count;
	}


	@Override
	public List<K> getAll() {
		LinkedList<K> ALL = new LinkedList<K>();
//		BSTNode<T> p = root, q;
//		
//		
//		while(p.left != null) {
//			
//			p=p.left;
//		}
		
		ALL = traverseIncreasing(root,  ALL);
		
		
		
		
		return (List<K>) ALL;
	}

	
	
	
	
	private LinkedList<K> traverseIncreasing(BNode<K,T> node, LinkedList<K> list)
    {
        if (node == null)
            return null;
 
        /* first recur on left child */
       traverseIncreasing(node.left, list);
       
 
 
        /* then print the data of node */
          list.insert( node.key);   ;
 
        /* now recur on right child */
             traverseIncreasing(node.right, list);
             
             return list;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
