package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class sorts an array of objects by using the Bubble Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/** The comparator to be used */
    @SuppressWarnings("unused")
	private Comparator<E> comparator;
    
    
    /**
     * Constructs an BubbleSorter object with the specified comparator
     * 
     * @param comparator	the specified Comparator to set the private comparator value to
     */
    public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }
	
    
	/**
	 * Constructs an BubbleSorter object to allow for the use of the Bubble Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
	public BubbleSorter() {
		super(null);
	}
    
	
	/**
	 * Sorts a set of data using the Bubble Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
    public void sort(E[] data) {
        
    	boolean r = true;
    	
    	while (r) {
    		
    		r = false;
    		
    		for (int i = 1; i < data.length; i++) {
    			
    			if (compare(data[i], data[i - 1]) < 0) {
    				
    				E x = data[i - 1];
    				data[i - 1] = data[i];
    				data[i] = x;
    				r = true;
    			}
    		}
    	}
    }
}
