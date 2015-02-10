/**
 * 
 */
package com.poc.vending.machine.util;

import com.poc.vending.machine.container.Container;
import com.poc.vending.machine.exception.OutOfStockException;
import com.poc.vending.machine.visitor.Coffee;
import com.poc.vending.machine.visitor.Cola;
import com.poc.vending.machine.visitor.OrangeJuice;

/**
 * This class is used to maintain Item quantity during purchase.
 * @author achak7
 *
 */
public class DrinkChamber {
	Container<Cola, Integer> colaContainer = new Container<Cola, Integer>();
	Container<Coffee, Integer> coffeeContainer = new Container<Coffee, Integer>();
	Container<OrangeJuice, Integer> ojContainer = new Container<OrangeJuice, Integer>();

	public Cola cola = new Cola();
	public Coffee coffee = new Coffee();
	public OrangeJuice oj = new OrangeJuice();

	public void loadInventory() {
		colaContainer.addItem(cola, new Integer(10));
		coffeeContainer.addItem(coffee, new Integer(10));
		ojContainer.addItem(oj, new Integer(10));
	}

	public void takeACola() throws Exception {
		if (getColaCount().intValue() - 1 < 0)
			throw new OutOfStockException("cola");
		colaContainer.addItem(cola, getColaCount().intValue() - 1);
	}

	public void takeACoffee() throws Exception {
		if (getCoffeeCount().intValue() - 1 < 0)
			throw new OutOfStockException("coffee");
		coffeeContainer.addItem(coffee, getColaCount().intValue() - 1);
	}

	public void takeAOJ() throws Exception {
		if (getOJCount().intValue() - 1 < 0)
			throw new OutOfStockException("orange juice");
		ojContainer.addItem(oj, getColaCount().intValue() - 1);
	}

	public Integer getColaCount() {
		return colaContainer.getItemCount(cola);
	}

	public Integer getCoffeeCount() {
		return coffeeContainer.getItemCount(coffee);
	}

	public Integer getOJCount() {
		return ojContainer.getItemCount(oj);
	}
}
