/**
 * 
 */
package com.poc.vending.machine;

/**
 * This class is the Template for Vending Machine.
 * 
 * @author achak7
 *
 */
public abstract class AbstractVendingMachine {
	
	public abstract void loadInventory();
	public abstract void displayMenu();
	public abstract void executeTask();
	
	/**
	 * This method will get executed for vending machine demo
	 */
	public final void perform(){
		loadInventory();
		displayMenu();
		executeTask();
	}
}
