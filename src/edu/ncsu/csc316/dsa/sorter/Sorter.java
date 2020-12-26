package edu.ncsu.csc316.dsa.sorter;


/**
 * This interface will be implemented by the classes using different algorithms for sorting a set of data
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public interface Sorter<E> {

	/**
	 * The method for sorting each of the different algorithms for sorting
	 * 
	 * @param data	the set of data to be sorted
	 */
	void sort(E[] data);
	
}
