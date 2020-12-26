package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Tests the StudentGPAComparator class
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentGPAComparatorTest {

	/** Sample Student object used for testing */ 
	public static final Student STUDENT_1 = new Student("Bill", "Nye", 1998);
	/** Sample Student object used for testing */
	public static final Student STUDENT_2 = new Student("Captain", "Krunch", 123456);
	
	
	/**
	 * Tests the compare method
	 */
	@Test
	public void testCompare() {
		
		StudentGPAComparator sic = new StudentGPAComparator();
		
		STUDENT_1.setGpa(12);
		STUDENT_2.setGpa(15);
		
		assertEquals(-1, sic.compare(STUDENT_1, STUDENT_2));
		assertEquals(1, sic.compare(STUDENT_2, STUDENT_1));
		assertEquals(0, sic.compare(STUDENT_1, STUDENT_1));
	}
}
