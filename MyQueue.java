import java.util.ArrayList;

/**
 * This class implements a queue data structure.
 * @author Daniel Xu
 * @version 2/20/2023
 */

public class MyQueue <T> implements QueueInterface<T>{

	private int size;
	private static int defaultSize = 10;
	private int maxCap;
	ArrayList<T> queue;


	public MyQueue(){
		this(defaultSize);
	}

	public MyQueue(int size){
		this.size = 0;
		maxCap = size;
		queue = new ArrayList<T>(size);

	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == maxCap;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty())
			throw new QueueUnderflowException();
		T element = queue.remove(0);
		size--;
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException{
		if(isFull())
			throw new QueueOverflowException();
		queue.add(e);			
		size++;
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < size; i++)
				s += queue.get(i);
		
		return s;
	}

	@Override
	public String toString(String delimiter) {
		String s = "";
		for(int i = 0; i < size; i++) {
			if(i == queue.size()-1)
				s += queue.get(i);
			else
				s += queue.get(i) + delimiter;			
		}
		return s;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> newQueue = new ArrayList<T>(queue);
		for (int i = 0; i < list.size(); i--) {
			newQueue.add(0, list.get(i));
			size++;
		}
		queue = newQueue;
	}
	

}


