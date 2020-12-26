package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayBasedList List that contains values and sorts them in an array.
 * 
 * @author Marwah Mahate
 *
 * @param <E> Generic type E
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	private int size;
	private E[] data;

	/**
	 * Default array capacity.
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Constructor for ArrayBasedList.
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor for ArrayBasedList
	 * 
	 * @param initialCapacity Initial capacity of the array.
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int initialCapacity) {
		data = (E[]) (new Object[initialCapacity]);
		this.size = 0;
	}

	/**
	 * Adds values to specified index in the array.
	 * 
	 * @param index Index
	 * @param value Value
	 */
	public void add(int index, E value) {
		// System.out.println("abl.add " + index + " "  + value);
		checkIndexForAdd(index);
		if (value == null) {
			throw new NullPointerException();
		}

		// make sure there's space for the element we are adding
		ensureCapacity(size + 1);

		// shift values to right if the index is in between
		for (int i = size; i >= index + 1; i--) {
			data[i] = data[i - 1];
		}

		data[index] = value;
		size++;
	}

	/**
	 * Gets values from specified index in the array.
	 */
	@Override
	public E get(int index) {
		// System.out.println("abl.get " + index);
		checkIndex(index);
		return data[index];

	}

	/**
	 * Removes values from specified index in the array.
	 */
	@Override
	public E remove(int index) {
		// System.out.println("abl.remove " + index);
		checkIndex(index);

		E removed = data[index];

		// shift values to left if the index is in between
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}

		// ensure the last element is set to null
		data[size - 1] = null;
		size--;
		return removed;
	}

	/**
	 * Sets values and replaces them to the specified index in the array.
	 */
	@Override
	public E set(int index, E value) {
		// System.out.println("abl.set " + index + " "  + value);
		checkIndex(index);
		if (value == null) {
			throw new NullPointerException();
		}

		E replaced = this.data[index];
		this.data[index] = value;
		return replaced;

	}

	/**
	 * Returns the size of the array.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Element Iterator for ArrayBasedList.
	 */
	@Override
	public Iterator<E> iterator() {
		// System.out.println("abl.iterator");
		return new ElementIterator();
	}

	/**
	 * Checks capacity of the array and grows the array if necessary.
	 * 
	 * @param minCapacity Minimum capacity.
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			// minCapacity is usually close to size, so this is a win:
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * ElementIterator inner class for ArrayBasedList, the iterator for this class.
	 * 
	 * @author Marwah Mahate
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		private int position;
		private boolean removeOK;

		/**
		 * Constructor for ElementIterator.
		 */
		public ElementIterator() {
			// System.out.println("ElementIterator " + Arrays.toString(data));
			position = 0;
			removeOK = false;
		}

		/**
		 * Checks if there is a next element.
		 */
		public boolean hasNext() {
			removeOK = false;
			// System.out.println("It.hasNext " + position);
			return position < size;
		}

		/**
		 * Gets the next element.
		 */
		public E next() {
			try{
				E value = get(position);
				removeOK = true;
				position++;
				// System.out.println("It.next " + position + " value: " + value);
				return value;
			} catch (IndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
			

		}

		/**
		 * Removes element.
		 */
		public void remove() {
			// System.out.println("It.remove " + position);
			if (removeOK) {
				ArrayBasedList.this.remove(position - 1);
				position--;
				removeOK = false;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
