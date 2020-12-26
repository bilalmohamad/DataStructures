package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * This class compares Student objects based on their ID
 * 
 * @author Bilal Mohamad
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * The constructor used for StudentIDComparator objects.
	 * Necessary for access to the compare method.
	 */
	public StudentIDComparator() {
		//DO NOTHING
	}
	
	
	/**
	 * Compares two students based on their ID.
	 * 
	 * @param one	the first Student for comparison
	 * @param two	the second Student for comparison
	 * 
	 * @return	0	if the Students have the same ID
	 * 			1	if the first Student has a greater ID than the second Student
	 * 			-1	if the first Student has a ID less than the second Student
	 */
    @Override
    public int compare(Student one, Student two) {
    	
    	if (one.getId() > two.getId()) {
    		return 1;
    	}
    	else if (one.getId() < two.getId()) {
    		return -1;
    	}
    	
    	return 0;
    }
}
