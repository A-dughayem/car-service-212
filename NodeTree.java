
public class NodeTree<T>{
	int u , v;
	T key;
	NodeTree<T> c1,c2,c3,c4;
	
	public NodeTree(int u, int v, T key) {
		
		this.u = u;
		this.v = v;
		this.key = key;
		c1=c2=c3=c4=null;
	}
	public NodeTree(Location l, T key) {
		
		this.u = l.x;
		this.v = l.y;
		this.key = key;
		c1=c2=c3=c4=null;
	}

}
