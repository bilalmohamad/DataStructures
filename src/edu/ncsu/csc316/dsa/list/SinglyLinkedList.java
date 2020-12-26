package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SinglyLinkedList List that contains values and sorts them in an linked list.
 * 
 * @author Marwah Mahate
 *
 * @param <E> Generic type E
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	private int size;
	private LinkedListNode front;
	private LinkedListNode tail;

	/**
	 * Constructor for SinglyLinkedList.
	 */
	public SinglyLinkedList() {
		// Let front be a dummy (sentinel) node
		front = new LinkedListNode(null);
		tail = null;
		size = 0;
	}

	/**
	 * Adds values to specified index in the linked list.
	 */
	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		if (value == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			LinkedListNode node = new LinkedListNode(value);
			front.next = node;
			tail = node;
			size = 1;
			return;
		} else {
			if (index == 0) {
				LinkedListNode node = new LinkedListNode(value, front.getNext());
				front.next = node;
				size++;
				return;
			} else if (index == size()) {
				LinkedListNode node = new LinkedListNode(value);
				tail.next = node;
				tail = node;
				size++;
				return;
			} else {
				LinkedListNode before = getNode(index - 1);
				LinkedListNode after = getNode(index);
				LinkedListNode node = new LinkedListNode(value, after);
				before.next = node;
				size++;
				return;
			}
		}
	}

	/**
	 * Gets the last value in the linked list.
	 */
	@Override
	public E last() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		return tail.getElement();
	}

	/**
	 * Adds to the last index of the linked list.
	 */
	@Override
	public void addLast(E value) {
		if (value == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			LinkedListNode node = new LinkedListNode(value);
			front.next = node;
			tail = node;
			size = 1;
			return;
		} else {
			LinkedListNode node = new LinkedListNode(value);
			tail.next = node;
			tail = node;
			size++;
			return;
		}
	}

	/**
	 * Gets value from the specified index in the linked list.
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return getNode(index).getElement();
	}

	/**
	 * Removes value at the specified index in the linked list.
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);

		if (size() == 1) {
			E value = front.getNext().getElement();
			front.next = null;
			tail = null;
			size = 0;
			return value;
		} else {
			if (index == 0) {
				E value = front.getNext().getElement();
				front.next = front.getNext().getNext();
				size--;
				return value;
			} else if (index == size() - 1) {
				LinkedListNode before = getNode(index - 1);
				E value = before.getNext().getElement();
				before.next = null;
				tail = before;
				size--;
				return value;
			} else {
				LinkedListNode before = getNode(index - 1);
				LinkedListNode current = before.getNext();
				LinkedListNode after = current.getNext();
				E value = current.getElement();
				before.next = after;
				size--;
				return value;
			}
		}

	}

	/**
	 * Sets and replaces value from a specified index in the linked list.
	 */
	@Override
	public E set(int index, E value) {
		checkIndex(index);
		if (value == null) {
			throw new NullPointerException();
		}
		LinkedListNode node = getNode(index);
		E old = node.getElement();
		node.data = value;
		return old;
	}

	/**
	 * Returns size of the linked list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Iterator creates element iterator for iterating over a collection.
	 */
	@Override
	public Iterator<E> iterator() {
		// We need to tell the iterator to skip the dummy/sentinel node
		return new ElementIterator(front.getNext());
	}

	/**
	 * Gets node from specified index.
	 * 
	 * @param index
	 * @return
	 */
	private LinkedListNode getNode(int index) {
		LinkedListNode current = front.getNext();
		int i = 0;
		while (i < index) {
			current = current.getNext();
			i++;
		}
		return current;
	}

	/**
	 * LinkedListNode Inner class for SinglyLinkedList Handles individual node.
	 * 
	 * @author Marwah Mahate
	 *
	 */
	private class LinkedListNode {

		private E data;
		private LinkedListNode next;

		private LinkedListNode(E value) {
			this(value, null);
		}

		/**
		 * Gets element.
		 * 
		 * @return data of the element
		 */
		public E getElement() {
			return data;
		}

		/**
		 * Gets next node.
		 * 
		 * @return next node.
		 */
		public SinglyLinkedList<E>.LinkedListNode getNext() {
			return next;
		}

		private LinkedListNode(E value, LinkedListNode nextPointer) {
			this.data = value;
			this.next = nextPointer;
		}
	}

	/**
	 * ElementIterator inner class for SinglyLinkedList Iterates over the linked
	 * list.
	 * 
	 * @author Marwah Mahate
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		// Keep track of the next node that will be processed
		private LinkedListNode current;
		// Keep track of the node that was processed on the last call to 'next'
		private LinkedListNode previous;
		// Keep track of the previous-previous node that was processed
		// so that we can update 'next' links when removing
		private LinkedListNode previousPrevious;
		private boolean removeOK;

		/**
		 * Constructor for ElementIterator
		 * 
		 * @param start Start
		 */
		public ElementIterator(LinkedListNode start) {
			current = start;
			previous = null;
			previousPrevious = null;
			removeOK = false;
			LinkedListNode temp = start;
			while (temp != null) {
				temp = temp.next;
			}
		}

		/**
		 * Checks if there is next.
		 */
		public boolean hasNext() {
			removeOK = false;
			return current != null;
		}

		/**
		 * Gets next.
		 */
		public E next() {
			if (current == null) {
				throw new NoSuchElementException();
			} else {
				E value = current.getElement();
				previousPrevious = previous;
				previous = current;
				current = current.getNext();
				removeOK = true;
				return value;
			}

		}

		/**
		 * Removes next value.
		 */
		public void remove() {
			if (removeOK) {
				if (previousPrevious == null && current == null) {
					// removing the only node
					front.next = null;
					tail = null;
					size = 0;
					removeOK = false;
					return;
				} else if (previousPrevious == null && current != null) {
					// remove the first node
					front.next = current;
					size--;
					removeOK = false;
					return;
				} else if (previousPrevious != null && current == null) {
					// remove the last node
					previousPrevious.next = null;
					tail = previousPrevious;
					size--;
					removeOK = false;
					return;
				} else if (previousPrevious != null && current != null){
					// remove from the middle
					previousPrevious.next = current;
					size--;
					removeOK = false;
					return;
				}
			} else {
				throw new IllegalStateException();
			}
		}
	}

}
