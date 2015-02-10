/**
 * 
 */
package com.poc.vending.machine.state;

/**
 * This class stands for vending machine Active state
 * @author achak7
 *
 */
public class ActiveState implements State{

	@Override
	public void enterChoiceAndAmount(String product, String amount) {
		System.out.println("Choice and Money has been inserted. Please wait untill current dispensing process finished...");
		
	}

	@Override
	public void dispenseProduct() {
		System.out.println("The product/s has been dispensed....");
		
	}

}
