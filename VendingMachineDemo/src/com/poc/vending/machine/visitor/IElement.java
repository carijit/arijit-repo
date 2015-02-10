/**
 * 
 */
package com.poc.vending.machine.visitor;

/**
 * @author achak7
 *
 */
public interface IElement {
	
	public double accept(IVendingMachineVisitor visitor);

}
