package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Creates a Stack using a SinglyLinkedList
 * 
 * @author Bilal Mohamad
 *
 * @param <E> The object type of the list.
 */
public class LinkedStack<E> extends AbstractStack<E> {

	/** The list containing the stack */
	private SinglyLinkedList<E> list;
	
	
	/**
	 * The constructor used for creating LinkedStack objects
	 */
	public LinkedStack() {
		list = new SinglyLinkedList<E>();
	}
	
	
	@Override
	public void push(E value) {
		list.add(0, value);
		
	}
	
	
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		
		E answer = list.remove(0);
		return answer;
	}


	@Override
	public E top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		
		return list.first();
	}


	@Override
	public int size() {
		return list.size();
	}
}
