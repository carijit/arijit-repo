/**
 * 
 */
package com.poc.vending.machine.visitor;

/**
 * @author achak7
 *
 */
public interface IVendingMachineVisitor {
	
	public double visit(Drink drink);
}
