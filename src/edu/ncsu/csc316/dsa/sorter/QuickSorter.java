package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * This class sorts an array of objects by using the Quick Sort algorithm.
 * Implements the Sorter interface.
 * 
 * @author Bilal Mohamad
 *
 * @param <E> object type of the data
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Constant used for using the First Element Selector Strategy */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    /** Constant used for using the Last Element Selector Strategy */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    /** Constant used for using the Middle Element Selector Strategy */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    /** Constant used for using the Random Element Selector Strategy */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /** The selector to be used */
    private PivotSelector selector;
    
    
    /**
     * The constructor used for creating a QuickSorter object using an entered comparator and selector
     * 
     * @param comparator	the comparator to be used
     * @param selector		the selector to be used
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
    
    /**
     * The constructor used for creating a QuickSorter object using an entered comparator
     * 
     * @param comparator	the comparator to be used
     */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}    
    
	
    /**
     * The constructor used for creating a QuickSorter object using an entered selector
     * 
     * @param selector		the selector to be used
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }
    
    
    /**
     * The default constructor used for creating a QuickSorter object with no parameters
     */
    public QuickSorter() {
        this(null, null);
    }
    
    
    /**
     * Sets this selector to the entered selector
     * @param selector	the selector to be set
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            selector = new RandomElementSelector();
        }
        this.selector = selector;
    }
    
    
    /**
     * Sorts the entered list
     */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
		
	}
	
	
	/**
	 * Calls onto other helper methods to recursively sort an array using the QuickSort algorithm
	 * 
	 * @param data	the array to sort
	 * @param low	the first point being observed in the list
	 * @param high	the ending point being observed in the list
	 */
	private void quickSort(E[] data, int low, int high) {
		
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	
	/**
	 * Breaks up the list and calls on the partitionHelper helper method to aid in sorting the broken list parts
	 * 
	 * @param data	the array to sort
	 * @param low	the first point being observed in the list
	 * @param high	the ending point being observed in the list
	 * 
	 * @return the index of the sorted partition
	 */
	private int partition(E[] data, int low, int high) {
		
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		
		return partitionHelper(data, low, high);
	}
	
	
	/**
	 * Breaks up the list to aid in sorting the broken list parts using the swap helper method
	 * 
	 * @param data	the array to sort
	 * @param low	the first point being observed in the list
	 * @param high	the ending point being observed in the list
	 * 
	 * @return the index of the sorted partition
	 */
	private int partitionHelper(E[] data, int low, int high) {
		
		E pivot = data[high];
		int index = low;
		
		for (int i = low; i <= high - 1; i++) {
			
			if (compare(data[i], pivot) <= 0) {
				swap(data, index, i);
				index++;
			}
		}
		
		swap(data, index, high);
		
		return index;
	}
	
	
	/**
	 * Swaps the elements at the entered low and high indexes
	 * 
	 * @param data	the array to sort
	 * @param low	the first point being observed in the list
	 * @param high	the ending point being observed in the list
	 */
	private void swap(E[] data, int low, int high) {
		
		E temp = data[low];
		data[low] = data[high];
		data[high] = temp;
	}
    
	
    /**
     * The interface to be implemented by each of the different Selectors
     * 
     * @author Bilal Mohamad
     *
     */
    public interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    
    
    /**
     * Makes the selector start at the lowest index
     * 
     * @author Bilal Mohamad
     *
     */
    public static class FirstElementSelector implements PivotSelector {
    	
    	/**
    	 * The constructor used for creating FirstElementSelector objects
    	 */
    	public FirstElementSelector() {
    		//Do Nothing
    	}
    	
    	
    	@Override
    	public int selectPivot(int low, int high) {
//    		setSelector(FIRST_ELEMENT_SELECTOR);
    		return Math.min(low, high);
    	}
    }
    
    
    
    /**
     * Makes the selector start at the highest index
     * 
     * @author Bilal Mohamad
     *
     */
    public static class LastElementSelector implements PivotSelector {
    	
    	/**
    	 * The constructor used for creating LastElementSelector objects
    	 */
    	public LastElementSelector() {
    		//Do Nothing
    	}
    	
    	
    	@Override
    	public int selectPivot(int low, int high) {
//    		setSelector(LAST_ELEMENT_SELECTOR);
    		return Math.max(low, high);
    	}
    }
    
    
    
    /**
     * Makes the selector start at the middle index
     * 
     * @author Bilal Mohamad
     *
     */
    public static class MiddleElementSelector implements PivotSelector {
    	
    	/**
    	 * The constructor used for creating MiddleElementSelector objects
    	 */
    	public MiddleElementSelector() {
    		//Do Nothing
    	}
    	
    	
    	@Override
    	public int selectPivot(int low, int high) {
//    		setSelector(MIDDLE_ELEMENT_SELECTOR);
    		return (high + low) / 2;
    	}
    }
    
    
    
    /**
     * Makes the selector start at a random index
     * 
     * @author Bilal Mohamad
     *
     */
    public static class RandomElementSelector implements PivotSelector {
    	
    	/**
    	 * The constructor used for creating RandomElementSelector objects
    	 */
    	public RandomElementSelector() {
    		//Do Nothing
    	}
    	
    	
    	@Override
    	public int selectPivot(int low, int high) {
//    		setSelector(RANDOM_ELEMENT_SELECTOR);
    		Random rand = new Random();
    		return rand.nextInt(high - low + 1) + low;
    	}
    }
}