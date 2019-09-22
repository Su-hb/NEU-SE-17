package Structure;

import java.lang.reflect.Array;

public class Stack<T> {
	public static int MAX_STACK_SIZE=9;

	private T[] mArray;
	private int size;

	public Stack(Class<T> type) {
		this(type, MAX_STACK_SIZE);
	}
	
	public Stack(Class<T> type, int size){
		mArray = (T[]) Array.newInstance(type, size);
		size = 0;
	}
	
	public void push(T val){
		mArray[size] = val;
		size++;
	}
	
	public void pop(){
		mArray[size-1] = null;
		size--;
	}
	
	public T peek(){
		if(size == 0){
			return null;
		}
		
		return mArray[size-1];
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0 ? true : false;
	}
	
	public boolean isFull(){
		return size==mArray.length ? true : false;
	}
	
	public T[] getAll(){
		return mArray;
	}
}
