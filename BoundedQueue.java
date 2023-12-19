import java.util.Vector;

public class BoundedQueue<T> implements HospitalQueue<T> { // Synchronized by wait/notify

	private Vector<T> buffer;
	private int maxSize;

	public BoundedQueue(int maxSize)
	{
		buffer = new Vector<T>();
		this.maxSize=maxSize;
	}

	public synchronized void insert(T item) { // insert to tail of queue
		while(buffer.size()>=maxSize) // Queue is full. Wait until someone extracts from the queue
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		buffer.add(item);
		notifyAll();

	}

	public synchronized T extract() {
		while (buffer.isEmpty()) // Queue is empty
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		T item = buffer.elementAt(0);
		buffer.remove(item);
		notifyAll();
		return item;

	}

}

