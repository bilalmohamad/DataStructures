package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Creates a PriorityQueue using the Adaptable Heap strategy
 *
 * @author Bilal Mohamad (bmohama)
 *
 * @param <K> the key
 * @param <V> the value
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

	
    /**
     * The constructor used for creating HeapAdaptablePriorityQueue objects with a comparator
     * 
     * @param c the comparator to be used
     */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}

	
    /**
     * The constructor used for creating HeapPriorityQueue objects without a comparator
     */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}

	// Adaptable PQ Entries must be location-aware so that the 
	// performance of replaceKey, replaceValue, and remove are O(log n)
	/**
	 * Creates the individual entries used for Adaptable Priority Queues
	 *
	 * @author Bilal Mohamad (bmohama)
	 *
	 * @param <K> the key
	 * @param <V> the value
	 */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		/** The index of the Entry */
		private int index;

		
		/**
		 * The constructor for creating an AdaptablePQEntry
		 * 
		 * @param key	the key of the entry
		 * @param value the value of the entry
		 * @param index the index of the entry
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		
		/**
		 * Retrieves the index of the entry
		 * 
		 * @return the index of the entry
		 */
		public int getIndex() {
			return index;
		}
		

		/**
		 * Sets the current index to the entered index
		 * 
		 * @param index the index to set to
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}

	// Factory method for creating a new adaptable PQ entry
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		// A new adaptable PQ Entry added to the heap will be at index size()
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}

	/**
	 * Make sure the entry is a valid Adaptable PQ Entry and is located within the heap structure
	 * 
	 * @param entry the entry being checked]
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @return an AdapatablePQEntry made from the entered entry
	 */
	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if (!(entry instanceof AdaptablePQEntry)) {
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>) entry;
		if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}

	
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>) list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>) list.get(index2)).setIndex(index2);
	}

	
	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		
		int index = temp.getIndex();
		
		if (index == list.size() - 1) {
			list.remove(list.size() - 1);
		}
		else {
			swap(index, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(index);
		}
	}

	/**
	 * Adjusts the entry at the index up or down the heap as needed
	 * 
	 * @param index the index being observed
	 */
	private void bubble(int index) {
		if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} 
		else {
			downHeap(index);
		}
	}

	
	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		
		temp.setKey(key);
		bubble(temp.getIndex());
	}

	
	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		
		temp.setValue(value);
	}
}
