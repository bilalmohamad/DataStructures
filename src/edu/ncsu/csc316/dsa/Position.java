package edu.ncsu.csc316.dsa;

/**
 * This interface is used for classes needed to obtain information of element at the current position
 * 
 * @author Bilal Mohamad 
 *
 * @param <E> The object type of the list.
 */
public interface Position<E> {
	
	/** 
	 * Retrieves the element at the current observed position
	 * 
	 * @return the element at the current position*/
    E getElement();
}
