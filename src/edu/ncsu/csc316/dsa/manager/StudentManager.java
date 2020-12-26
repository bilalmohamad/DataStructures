package edu.ncsu.csc316.dsa.manager;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.io.StudentReader;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Manages a roster of Students
 *
 * @author Dr. King
 * @author Bilal Mohamad
 */
public class StudentManager {

    /** A roster of Students **/
    private Student[] roster;
    
     /** The sorter used when sorting Students **/
    private Sorter<Student> sorter;
    
    
    /**
     * Creates a new StudentManager given the path to an input file that contains Student information.
     * Uses the entered sorting algorithm
     * 
     * @param pathToFile	the filename to read input from
     * @param sorter		the sorting algorithm to use to sort the data
     */
    public StudentManager(String pathToFile, Sorter<Student> sorter) {
    	roster = StudentReader.readInput(pathToFile);
        this.sorter = sorter;
    }
    
    /**
     * Creates a new StudentManager given the path to an input file that contains Student information.
     * 
     * @param pathToFile	the filename to read input from
     */
    public StudentManager(String pathToFile) {
        this(pathToFile, new InsertionSorter<Student>());
    }
        
    /**
     * Returns a sorted array of Students
     *
     * @return the sorted array of Students
     */
    public Student[] sort()
    {
        sorter.sort(roster);
        return roster;
    }
  
}