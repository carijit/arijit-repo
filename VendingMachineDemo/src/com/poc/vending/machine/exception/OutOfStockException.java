/**
 * 
 */
package com.poc.vending.machine.exception;

/**
 * @author achak7
 *
 */
public class OutOfStockException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selection;
   
    public OutOfStockException(String selection) {
            this.selection = selection;
    }
   
    public String toString() {
            return "\nSorry, we run out of stock on " + selection + ".";
    }
}
