/**
 * 
 */
package com.poc.vending.machine.visitor;

/**
 * @author achak7
 *
 */
public class VendingMachineVisitorImpl implements IVendingMachineVisitor {

	/* (non-Javadoc)
	 * @see com.poc.vending.machine.visitor.IVendingMachineVisitor#visit(com.poc.vending.machine.visitor.Drink)
	 */
	@Override
	public double visit(Drink drink) {
		return drink.getPrice();
	}

}
