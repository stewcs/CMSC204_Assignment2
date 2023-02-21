import java.util.ArrayList;

/**
 * This class implements a stack data structure.
 * @author Daniel Xu
 * @version 2/20/2023
 */

public class MyStack <T> implements StackInterface<T>{
	
	

	private int size;
	private static int defaultSize = 10;
	private int maxCap;
	ArrayList<T> stack;
	
	
	public MyStack(){
		this(defaultSize);
	}
	
	public MyStack(int size){
		this.size = 0;
		maxCap = size;
		stack = new ArrayList<T>(size);
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
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		T element = stack.remove(size-1);
		size--;
		return element;
	}

	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		T element = stack.get(size-1);
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean push(T e) throws StackOverflowException{
		if(isFull()) {
			throw new StackOverflowException();
		}
		stack.add(e);
		size++;
		return true;
	}

	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < size; i++)
				s += stack.get(i);
		
		return s;
	}
	
	
	@Override
	public String toString(String delimiter) {
		String s = "";
		for(int i = 0; i < size; i++) {
			if(i == stack.size()-1)
				s += stack.get(i);
				else
			s += stack.get(i) + delimiter;			
		}
		return s;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> newStack = new ArrayList<T>(stack);
		for (int i = 0; i < list.size(); i++) {
			newStack.add(list.get(i));
			size++;
		}
		stack = newStack;
	}


}
