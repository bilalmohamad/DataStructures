package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.io.StudentReader;
import edu.ncsu.csc316.dsa.sorter.BubbleSorter;
import edu.ncsu.csc316.dsa.sorter.CountingSorter;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
import edu.ncsu.csc316.dsa.sorter.QuickSorter;
import edu.ncsu.csc316.dsa.sorter.RadixSorter;
import edu.ncsu.csc316.dsa.sorter.SelectionSorter;

/**
 * Tests the StudentManager class
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentManagerTest {

	/** File path to receive input from */
	public static final String FILEPATH = "input/input_file.csv";
	
	/** File path to receive sorted input from */
	public static final String SORTEDPATH = "input/input_sorted.csv";
	
	/** Array created from the input file */
	public static final Student[] INPUT_ARRAY = StudentReader.readInput(FILEPATH);
	
	/** Array created from the sorted input file */
	public static final Student[] ABSTRACT_SORTED_ARRAY = StudentReader.readInput(SORTEDPATH);
	
	
	/**
	 * Tests the sort method using the default sorter
	 */
	@Test
	public void testDefaultSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH);
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
	}
	
	
	
	/**
	 * Tests the sort method using the InsertionSorter algorithm
	 */
	@Test
	public void testInsertionSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH, new InsertionSorter<Student>(null));
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
	}


	/**
	 * Tests the sort method using the BubbleSorter algorithm
	 */
	@Test
	public void testBubbleSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH, new BubbleSorter<Student>(null));
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
		
		
		StudentManager sm2 = new StudentManager(FILEPATH, new BubbleSorter<Student>());
		
		Student[] actualArray2 = sm2.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray2[i]);
		}
	}
	
	
	/**
	 * Tests the sort method using the SelectionSorter algorithm
	 */
	@Test
	public void testSelectionSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH, new SelectionSorter<Student>(null));
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
		
		
		StudentManager sm2 = new StudentManager(FILEPATH, new SelectionSorter<Student>());
		
		Student[] actualArray2 = sm2.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray2[i]);
		}
	}
	
	
	/**
	 * Tests the sort method using the CountingSorter algorithm
	 */
	@Test
	public void testCountingSorter() {
		
		StudentManager sm = new StudentManager(SORTEDPATH, new CountingSorter<Student>());
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < INPUT_ARRAY.length; i++) {
			assertEquals(INPUT_ARRAY[i], actualArray[i]);
		}
	}
	
	
	/**
	 * Tests the sort method using the RadixSorter algorithm
	 */
	@Test
	public void testRadixSorter() {
		
		StudentManager sm = new StudentManager(SORTEDPATH, new RadixSorter<Student>());
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < INPUT_ARRAY.length; i++) {
			assertEquals(INPUT_ARRAY[i], actualArray[i]);
		}
	}
	
	
	/**
	 * Tests the sort method using the MergeSorter algorithm
	 */
	@Test
	public void testMergeSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH, new MergeSorter<Student>(null));
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
		
		
		StudentManager sm2 = new StudentManager(FILEPATH, new MergeSorter<Student>());
		
		Student[] actualArray2 = sm2.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray2[i]);
		}
	}
	
	
	/**
	 * Tests the sort method using the QuickSorter algorithm
	 */
	@Test
	public void testQuickSorter() {
		
		StudentManager sm = new StudentManager(FILEPATH, new QuickSorter<Student>());
		
		Student[] actualArray = sm.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray[i]);
		}
		
		StudentManager sm2 = new StudentManager(FILEPATH, new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR));
		
		Student[] actualArray2 = sm2.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray2[i]);
		}
		
		
		StudentManager sm3 = new StudentManager(FILEPATH, new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR));
		
		Student[] actualArray3 = sm3.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray3[i]);
		}
		
		
		StudentManager sm4 = new StudentManager(FILEPATH, new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR));
		
		Student[] actualArray4 = sm4.sort();
		
		for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray4[i]);
		}
		
		
		StudentManager sm5 = new StudentManager(FILEPATH, new QuickSorter<Student>(Comparator.naturalOrder()));
		
		Student[] actualArray5 = sm5.sort();
		
		assertEquals(ABSTRACT_SORTED_ARRAY[0], actualArray5[0]);
		
		/*for (int i = 0; i < ABSTRACT_SORTED_ARRAY.length - 1; i++) {
			assertEquals(ABSTRACT_SORTED_ARRAY[i], actualArray5[i + 1]);
		}*/
	}
	
}
