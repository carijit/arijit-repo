/**
 * 
 */
package com.poc.vending.machine.util;


/**
 * This is an utility class holds all the coin denominations and menu items
 * @author achak7
 * 
 */
public class VendingMachineUtil {

	public static enum Coin {
		PENNY(1.0), NICKEL(5.0), DIME(10.0), QUARTER(25.0), DOLLAR(100.0);
		Coin(double value) {
			this.value = value;
		}

		public final double value;

		public double value() {
			return value;
		}
	}

	public static enum SelectionMenu {
		COLA, ORANGE_JUICE, COFFEE, QUIT
	}

}
