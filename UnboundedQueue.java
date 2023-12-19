import java.util.Vector;

public class UnboundedQueue<T> implements HospitalQueue<T>{
	public Vector<T> buffer; // buffer to hold queue entries

	public UnboundedQueue() {
		buffer = new Vector<T>();
	}

	public synchronized void insert(T paiet) { // insert to tail of queue
		buffer.add(paiet);
		this.notifyAll();
	}

	public synchronized T extract() { // return element from head of queue
		while (buffer.isEmpty()) // Queue is empty
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		T t = buffer.elementAt(0);
		buffer.remove(t);
		return t;
	}

}
