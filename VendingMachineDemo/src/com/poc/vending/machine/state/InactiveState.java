/**
 * 
 */
package com.poc.vending.machine.state;

/**
 * This class stands for vending machine Inactive state
 * @author achak7
 *
 */
public class InactiveState implements State{

	@Override
	public void enterChoiceAndAmount(String product, String amount) {
		System.out.println(amount+" has been inserted and "+product+" has been selected.");
		
	}

	@Override
	public void dispenseProduct() {
		System.out.println(" Vending Machine can not dispense product because money is not inserted and product is not selected.");
		
	}

}
