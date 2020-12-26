package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;


/**
 * This class sorts an array of objects by using the Merge Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** The comparator to be used */
    @SuppressWarnings("unused")
	private Comparator<E> comparator;
    
	
    /**
     * Constructs an MergeSorter object with the specified comparator
     * 
     * @param comparator	the specified Comparator to set the private comparator value to
     */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	/**
	 * Constructs an MergeSorter object to allow for the use of the Merge Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
	public MergeSorter() {
		super(null);
	}

	
	/**
	 * Sorts a set of data using the Merge Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
	@Override
	public void sort(E[] data) {
		
		if (data.length < 2) {
			return;
		}
		int mid = data.length / 2;
		
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}

	
	/**
	 * Helper method for sorting an array that has been split by the left range and the right range
	 * 
	 * @param left		the data from the 0 to the middle index - 1
	 * @param right		the data from the middle to the end of the list
	 * @param data		the data being observed
	 */
	private void merge(E[] left, E[] right, E[] data) {
		
		int leftIndex = 0;
		int rightIndex = 0;
		
		while ((leftIndex + rightIndex) < data.length) {
			//Could be > 0
			if (rightIndex == right.length || (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}
