package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;


/**
 * This class compares Student objects based on their GPA
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * The constructor used for StudentGPAComparator objects.
	 * Necessary for access to the compare method.
	 */
	public StudentGPAComparator() {
		//DO NOTHING
	}
	
	
	/**
	 * Compares two students based on their GPA.
	 * 
	 * @param one	the first Student for comparison
	 * @param two	the second Student for comparison
	 * 
	 * @return	0	if the Students have the same GPA
	 * 			1	if the first Student has a greater GPA than the second Student
	 * 			-1	if the first Student has a GPA less than the second Student
	 */
    @Override
    public int compare(Student one, Student two) {
    	
    	if (one.getGpa() > two.getGpa()) {
    		return 1;
    	}
    	else if (one.getGpa() < two.getGpa()) {
    		return -1;
    	}
    	
    	return 0;
    }
}
