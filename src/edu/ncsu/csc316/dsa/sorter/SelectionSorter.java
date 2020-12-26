package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * This class sorts an array of objects by using the Selection Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** The comparator to be used */
    @SuppressWarnings("unused")
	private Comparator<E> comparator;
    
    
    /**
     * Constructs an SelectionSorter object with the specified comparator
     * 
     * @param comparator	the specified Comparator to set the private comparator value to
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    
	/**
	 * Constructs an SelectionSorter object to allow for the use of the Selection Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
	public SelectionSorter() {
		super(null);
	}    

    
	/**
	 * Sorts a set of data using the Selection Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
    public void sort(E[] data) {
        
    	for (int i = 0; i <= data.length - 1; i++) {
    		
    		int min = i;
    		
    		for (int j = i + 1; j <= data.length - 1; j++) {
    			
    			if ((compare(data[j], data[min]) < 0)) {
    				min = j;
    			}
    		}
    		
    		E x = data[i];
    		data[i] = data[min];
    		data[min] = x;
    	}
    }
}
