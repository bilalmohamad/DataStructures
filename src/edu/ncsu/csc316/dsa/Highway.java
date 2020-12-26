package edu.ncsu.csc316.dsa;

/**
 * This class is used for testing the shortest and minimal paths
 *
 * @author Bilal Mohamad (bmohama)
 *
 */
public class Highway implements Weighted {
	
	/** The name of the edge */
    private String name;
    
    /** The length of the edge */
    private int length;
    
    /** A flag to check if the edge has been found */
    private boolean found;
    
    /**
     * The constructor used for creating Highway objects
     * 
     * @param n the name of the edge
     * @param l the length of the edge
     */
    public Highway(String n, int l) {
        setName(n);
        setLength(l);
        setFound(false);
    }

    /**
     * Sets the name to the entered parameter
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * Returns the name
     * 
     * @return the name
     */
    public String getName() {
    	return name;
    }

    /**
     * Returns the length
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length to the entered parameter
     * 
     * @param length the new length
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * Sets found to the entered parameter
     * 
     * @param found the new found flag
     */
    public void setFound(boolean found) {
    	this.found = found;
    }

    @Override
    public int getWeight() {
        return getLength();
    }

    /**
     * Checks if the edge has been found
     * 
     * @return	true	if it has been found
     * 			false	otherwise
     */
    public boolean isFound() {
    	return found;
    }
}
