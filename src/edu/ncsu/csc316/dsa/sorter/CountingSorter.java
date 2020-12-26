package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;


/**
 * This class sorts an array of objects by using the Counting Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
    
	
	/**
	 * Constructs an CountingSorter object to allow for the use of the Counting Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
    public CountingSorter() {
		//DO NOTHING
	}

    
	/**
	 * Sorts a set of data using the Counting Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
	@SuppressWarnings("unchecked")
	public void sort(E[] data) {
		
		int min = data[0].getId();
		int max = data[0].getId();
		
		for (int i = 0; i <= data.length - 1; i++) {
			
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		
		int range = max - min + 1;
		int[] arrayB = new int[range];
		
		for (int i = 0; i <= data.length - 1; i++) {
			arrayB[data[i].getId() - min] = arrayB[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i <= range - 1; i++) {
			arrayB[i] = arrayB[i - 1] + arrayB[i];
		}
		
		Identifiable[] arrayF = new Identifiable[data.length];
		
		for (int i = 0; i <= data.length - 1; i++) {
			
			arrayF[arrayB[data[i].getId() - min] - 1] = data[i];
			arrayB[data[i].getId() - min] = arrayB[data[i].getId() - min] - 1;
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = (E) arrayF[i];
		}
		
    }
}
