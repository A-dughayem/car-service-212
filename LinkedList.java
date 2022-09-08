
public class LinkedList<T> implements List<T> {

	private NodeList<T> head;
	private NodeList<T> current;

	public LinkedList() {
	
		head = null;
		current = null;
	}

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void findFirst() {
		current = head;

	}

	@Override
	public void findNext() {
		current = current.next;

	}

	@Override
	public boolean last() {
		return current.next == null;
	}

	@Override
	public T retrieve() {
		if (empty())
			return null;
		return current.data;

	}

	@Override
	public void update(T e) {
		current.data = e;

	}

	@Override
	public void insert(T e) {
		NodeList<T> tmp;
		if (empty()) {
			current = head = new NodeList<T> (e);
		}
		else {
			tmp = current.next;
			current.next = new NodeList<T> (e);
			current = current.next;
			current.next = tmp;
		}

	}
 
	@Override
	public void remove() {
		if (current == head) {
			head = head.next;
		}
		else {
			NodeList<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;


	}

}
