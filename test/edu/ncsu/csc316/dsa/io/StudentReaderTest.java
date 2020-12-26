package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the StudentReader class
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentReaderTest {

	/** File path to receive input from */
	public static final String FILEPATH = "input/input_file.csv";
	
	/** Sample student used for testing */
	public static final Student STUDENT_1 = new Student("Leisha", "Yee", 2);
	/** Sample student used for testing */
	public static final Student STUDENT_2 = new Student("Avery", "Fisk", 4);
	/** Sample student used for testing */
	public static final Student STUDENT_3 = new Student("Isaac", "Avila", 5);
	/** Sample student used for testing */
	public static final Student STUDENT_4 = new Student("Dolores", "Conti", 8);
	
	/** Test array of sample students */
	private Student[] testArray = new Student[4];
	
	
	/**
	 * Tests the readInput method of the StudentReader class
	 */
	@Test
	public void testReadInput() {
		
		@SuppressWarnings("unused")
		StudentReader sr = new StudentReader();
		
		STUDENT_1.setUnityID("yeel");
		STUDENT_2.setUnityID("fiska");
		STUDENT_3.setUnityID("avilai");
		STUDENT_4.setUnityID("contid");
		
		STUDENT_1.setGpa(1.57);
		STUDENT_2.setGpa(0.78);
		STUDENT_3.setGpa(1.36);
		STUDENT_4.setGpa(3.14);
		
		STUDENT_1.setCreditHours(14);
		STUDENT_2.setCreditHours(17);
		STUDENT_3.setCreditHours(9);
		STUDENT_4.setCreditHours(10);
		
		testArray[0] = STUDENT_1;
		testArray[1] = STUDENT_2;
		testArray[2] = STUDENT_3;
		testArray[3] = STUDENT_4;
		
		Student[] studentArray = StudentReader.readInput(FILEPATH);
		
		for (int i = 0; i < testArray.length; i++) {
			
			assertEquals(testArray[i], studentArray[i]);
			assertEquals(testArray[i].getLast(), studentArray[i].getLast());
			assertEquals(testArray[i].getFirst(), studentArray[i].getFirst());
			assertEquals(testArray[i].getId(), studentArray[i].getId());
			assertEquals(testArray[i].getUnityID(), studentArray[i].getUnityID());
			assertEquals(testArray[i].getGpa(), studentArray[i].getGpa(), 0.0001);
			assertEquals(testArray[i].getCreditHours(), studentArray[i].getCreditHours());
		}
	}
}
