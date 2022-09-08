
public class NodeList<T> {
	public T data;
	public NodeList<T> next;

	public NodeList () {
		data = null;
		next = null;
	}

	public NodeList (T val) {
		data = val;
		next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public NodeList<T> getNext() {
		return next;
	}

	public void setNext(NodeList<T> next) {
		this.next = next;
	}
}
