/**
 * 
 */
package com.poc.vending.machine.visitor;

/**
 * @author achak7
 *
 */
public class OrangeJuice extends Drink {
	public double price = 100;

	@Override
	public double accept(IVendingMachineVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	/**
	 * @return the price
	 */
	@Override
	public double getPrice() {
		return price;
	}
	
	
}
