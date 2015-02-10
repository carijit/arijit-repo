/**
 * 
 */
package com.poc.vending.machine.state;

/**
 * This is the vending machine abstract state 
 * @author achak7
 *
 */
public interface State {

	public void enterChoiceAndAmount(String product, String amount);
	
	public void dispenseProduct();
}
