package edu.ncsu.csc316.dsa.data;


/**
 * This class contains the Student objects that will be used for the use of the sorting algorithms
 * based a student's information, such as first name, last name, id, credit hours, GPA, and Unity Id.
 * 
 * @author Bilal Mohamad
 * 
 */
public class Student implements Comparable<Student>, Identifiable {

	/** The first name of the Student */
	private String first;
	
	/** The last name of the Student */
	private String last;
	
	/** The id of the Student */
	private int id;
	
	/** The credit hours of the Student */
	private int creditHours;
	
	/** The GPA of the Student */
	private double gpa;
	
	/** The Unity Id of the Student */
	private String unityID;
	
	
	/**
	 * The constructor used for creating Student objects.
	 * 
	 * @param first	the first name of the Student
	 * @param last	the last name of the Student
	 * @param id	the id of the Student
	 */
	public Student (String first, String last, int id) {
		
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(0);
		setGpa(0.0);
		setUnityID(null);
	}


	/**
	 * Retrieves the Student's first name
	 * 
	 * @return the first name of the Student
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * Sets the Student's first name to the entered value
	 * 
	 * @param first value to set the first name to
	 */
	public void setFirst(String first) {
		this.first = first;
	}


	/**
	 * Retrieves the Student's last name
	 * 
	 * @return the last name of the Student
	 */
	public String getLast() {
		return last;
	}


	/**
	 * Sets the Student's last name to the entered value
	 * 
	 * @param last value to set the last name to
	 */
	public void setLast(String last) {
		this.last = last;
	}


	/**
	 * Retrieves the Student's id
	 * 
	 * @return the id of the Student
	 */
	public int getId() {
		return id;
	}


	/**
	 * Sets the Student's id to the entered value
	 * 
	 * @param id value to set the id to
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * Retrieves the Student's credit hours
	 * 
	 * @return the credit hours of the Student
	 */
	public int getCreditHours() {
		return creditHours;
	}


	/**
	 * Sets the Student's creditHours to the entered value
	 * 
	 * @param creditHours value to set the creditHours to
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}


	/**
	 * Retrieves the Student's gpa
	 * 
	 * @return the gpa of the Student
	 */
	public double getGpa() {
		return gpa;
	}


	/**
	 * Sets the Student's gpa to the entered value
	 * 
	 * @param gpa value to set the gpa to
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	/**
	 * Retrieves the Student's UnityId
	 * 
	 * @return the UnityId of the Student
	 */
	public String getUnityID() {
		return unityID;
	}


	/**
	 * Sets the Student's UnityId to the entered value
	 * 
	 * @param unityID value to set the UnityId to
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	
	/**
	 * Hashes the Student object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	/**
	 * Checks if the current student and the entered object are equal. Comparison is based on id.
	 * 
	 * @param obj	the object to use for comparison with the current object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}


	/**
	 * Compares this object with the specified object.
	 * 
	 * @param s	the Student object used for comparison
	 * 
	 * @return 	0 	if the objects are the same
	 * 			1	if this object is greater than the specified object
	 * 			-1	if this object is less than the specified object
	 */
	@Override
	public int compareTo(Student s) {
		if (this.getLast().compareTo(s.getLast()) > 0) {
			return 1;
		}
		
		else if (this.getLast().compareTo(s.getLast()) < 0) {
			return -1;
		}
		
		else {
			if (this.getFirst().compareTo(s.getFirst()) > 0) {
				return 1;
			}
			
			else if (this.getFirst().compareTo(s.getFirst()) < 0) {
				return -1;
			}
			
			else {
				if (this.getId() > s.getId()) {
					return 1;
				}
				
				else if (this.getId() < s.getId()) {
					return -1;
				}
			}
		}
		
		return 0;
	}


	/**
	 * Converts this Student object to a String
	 * 
	 * @return this Student object as a String
	 */
	@Override
	public String toString() {
		return first + "," + last + "," + unityID + "," + id + "," + gpa + "," + creditHours;
	}
	
	
	
}
