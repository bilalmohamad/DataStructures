package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the Student class
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentTest {

	/** Sample Student object used for testing */ 
	public static final Student STUDENT_1 = new Student("Bill", "Nye", 1998);
	/** Sample Student object used for testing */
	public static final Student STUDENT_2 = new Student("Captain", "Krunch", 123456);
	
	
	/**
	 * Tests the Student constructor as well as the getter and setter methods
	 */
	@Test
	public void testStudent() {
		
		assertEquals("Bill", STUDENT_1.getFirst());
		assertEquals("Nye", STUDENT_1.getLast());
		assertEquals(1998, STUDENT_1.getId());
		
		Student s = new Student ("J", "Cole", 1985);
		
		s.setCreditHours(15);
		s.setGpa(3.8);
		s.setUnityID("jcole");
		
		assertEquals(15, s.getCreditHours());
		assertEquals(3.8, s.getGpa(), 0.00001);
		assertEquals("jcole", s.getUnityID());
		
	}
	
	
	/**
	 * Tests the equals method of the Student class
	 */
	@Test
	public void testEquals() {
		
		assertTrue(STUDENT_1.equals(STUDENT_1));
		assertFalse(STUDENT_1.equals(STUDENT_2));
		
	}
	
	
	/**
	 * Tests the compareTo method of the Student class
	 */
	@Test
	public void testCompareTo() {
		
		assertEquals(0, STUDENT_1.compareTo(STUDENT_1));
		assertEquals(1, STUDENT_1.compareTo(STUDENT_2));
		assertEquals(-1, STUDENT_2.compareTo(STUDENT_1));
		
	}
	
	
	/**
	 * Tests the toString method of the Student class
	 */
	@Test
	public void testToString() {
		
		Student s = new Student ("J", "Cole", 1985);
		
		s.setCreditHours(15);
		s.setGpa(3.8);
		s.setUnityID("jcole");
		
		assertEquals("J,Cole,jcole,1985,3.8,15", s.toString());
	}
	
	
	/**
	 * Tests the hashCode method of the Student class
	 */
	@Test
	public void testHashCode() {
		
		Student newStudent = STUDENT_1;
		
		assertEquals(STUDENT_1.hashCode(), newStudent.hashCode());
	}
}
