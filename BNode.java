
public class BNode <K,T>{
	public K key;
	public T data;
	public BNode<K,T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BNode(K k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BNode (K k, T val, BNode<K,T> l, BNode<K,T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}

	

}
