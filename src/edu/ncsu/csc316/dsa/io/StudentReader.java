package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Processes text files with Student information
 *
 * @author Dr. King
 * @author Bilal Mohamad
 */
public class StudentReader {

	/**
	 * Used to construct StudentReader objects.
	 */
	public StudentReader() {
		//DO NOTHING
	}
	
    /**
     * Returns an array of Student objects processed
     * from the given input file.
     *
     * @param filePath - the path to the input file
     * @return an array of Students
     */
    public static Student[] readInput(String filePath)
    {
        Student[] list = new Student[10];
        try(Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8"))
        {
            scan.nextLine(); // SKIP HEADER LINE
            int index = 0;
            while(scan.hasNextLine())
            {
                if(index >= list.length)
                {
                    list = Arrays.copyOf(list, list.length * 2 + 1);
                }
                list[index] = processLine(scan.nextLine());
                index++;
            }
            list = Arrays.copyOf(list, index);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + e.getMessage());
        }
        return list;
    }

    
    /**
     * Helper method used to process the individual line to create a Student object
     * 
     * @param line	the line being processed from the input file
     * 
     * @return the Student object created from the processed line
     */
    private static Student processLine(String line) {
    	
    	Student newStudent = null;
    	
        try (Scanner scan = new Scanner(line)) {
        	
        	scan.useDelimiter(",");
        	
        	String first = scan.next();
        	String last = scan.next();
        	String unityId = scan.next();
        	int id = scan.nextInt();
        	double gpa = scan.nextDouble();
        	int creditHours = scan.nextInt();
        	
        	newStudent = new Student(first, last, id);
        	newStudent.setUnityID(unityId);
        	newStudent.setGpa(gpa);
        	newStudent.setCreditHours(creditHours);
        }
        
        return newStudent;
    }
}