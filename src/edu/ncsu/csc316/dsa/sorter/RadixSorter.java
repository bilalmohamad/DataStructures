package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;


/**
 * This class sorts an array of objects by using the Radix Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
    
	
	/**
	 * Constructs an RadixSorter object to allow for the use of the Radix Sort algorithm.
	 * Does not complete any action aside for the use of the sort method.
	 */
    public RadixSorter() {
		//DO NOTHING
	}

    
	/**
	 * Sorts a set of data using the Radix Sort algorithm
	 * 
	 * @param data	the set of data to be sorted
	 */
	@SuppressWarnings("unchecked")
	public void sort(E[] data) {
		
		int k = 0;
		
		for (int i = 0; i <= data.length - 1; i++) {
			k = Math.max(k, data[i].getId());
		}
		
		double x = Math.ceil(Math.log(k + 1) / Math.log(10));
		int p = 1;
		
		for (int j = 1; j <= x; j++) {
			
			int[] arrayB = new int[10];
			
			for (int i = 0; i <= data.length - 1; i++) {
				arrayB[ (data[i].getId() / p) % 10] = arrayB[ (data[i].getId() / p) % 10] + 1;
			}
			
			for (int i = 1; i <= 9; i++) {
				arrayB[i] = arrayB[i - 1] + arrayB[i];
			}
			
			Identifiable[] arrayF = new Identifiable[data.length];
			
			for (int i = data.length - 1; i >= 0; i--) {
				arrayF[ arrayB[ (data[i].getId() / p) % 10] - 1] = data[i];
				arrayB[ (data[i].getId() / p) % 10] = arrayB[ (data[i].getId() / p) % 10] - 1;
			}
			
			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = (E) arrayF[i];
			}
			
			p *= 10;
		}
	}

}
