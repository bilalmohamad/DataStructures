package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * This abstract class sorts an array of objects.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** The comparator to be used */
    private Comparator<E> comparator;
    
    
    /**
     * Constructs an AbstractComparisonSorter object with the specified comparator
     * 
     * @param comparator	the specified Comparator to set the private comparator value to
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    
	/**
	 * Sets the comparator the entered parameter
	 * 
	 * @param comparator	the specified Comparator to set the private comparator value to
	 */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
           comparator = new NaturalOrder();
        }
        this.comparator = comparator;
    }     
    
    
    /**
     * Used if the client attempts to sort without a Comparator
     * 
     * @author Bilal Mohamad
     *
     */
    private class NaturalOrder implements Comparator<E> {
    	
    	/**
    	 * Compares the first parameter to the second parameter if the client attempts to sort without a Comparator
    	 * 
    	 * @param first		the first object used for comparison
    	 * @param second	the second object used for comparison
    	 * 
    	 * @return	0	if the objects are the same
    	 * 			-1	if the first object is less than the second object
    	 * 			1	if the first object is greater than the second object
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    
    /**
     * Compares the first entered object to the second entered object using this comparator.
     * 
     * @param data1	the first object used for comparison
     * @param data2	the second object used for comparison
     * 
	 * @return the result of comparing two elements using the Comparator’s compare method
	 */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}
