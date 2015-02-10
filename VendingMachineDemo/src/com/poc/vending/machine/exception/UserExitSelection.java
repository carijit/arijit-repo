/**
 * 
 */
package com.poc.vending.machine.exception;

/**
 * @author achak7
 *
 */
public class UserExitSelection extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
            return "\nWe understand that you have decided to exit. Have a nice day, bye!";
    }
}

