package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class sorts an array of objects by using the Insertion Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/** The comparator to be used */
    @SuppressWarnings("unused")
	private Comparator<E> comparator;
    
    
    /**
     * Constructs an InsertionSorter object with the specified comparator
     * 
     * @param comparator	the specified Comparator to set the private comparator value to
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
	
    
	/**
	 * Constructs an InsertionSorter object to allow for the use of the Insertion Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
	public InsertionSorter() {
		super(null);
	}
    
	
	/**
	 * Sorts a set of data using the Insertion Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
    public void sort(E[] data) {
        
    	for (int i = 1; i <= data.length - 1; i++) {
    		
    		E x = data[i];
    		int j = i - 1;
    		
    		while (j >= 0 && compare(data[j], x) > 0) {
    			
    			data[j + 1] = data[j];
    			j -= 1;
    		}
    		
    		data[j + 1] = x;
    	}
    }
}
