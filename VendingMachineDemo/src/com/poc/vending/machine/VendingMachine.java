/**
 * 
 */
package com.poc.vending.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.poc.vending.machine.exception.OutOfStockException;
import com.poc.vending.machine.exception.UserExitSelection;
import com.poc.vending.machine.state.ActiveState;
import com.poc.vending.machine.state.InactiveState;
import com.poc.vending.machine.state.State;
import com.poc.vending.machine.util.DrinkChamber;
import com.poc.vending.machine.util.VendingMachineUtil;
import com.poc.vending.machine.util.VendingMachineUtil.Coin;
import com.poc.vending.machine.visitor.Coffee;
import com.poc.vending.machine.visitor.Cola;
import com.poc.vending.machine.visitor.IVendingMachineVisitor;
import com.poc.vending.machine.visitor.OrangeJuice;
import com.poc.vending.machine.visitor.VendingMachineVisitorImpl;

/**
 * This is the implementation class for Vending Machine
 * @author achak7
 * 
 */
public class VendingMachine extends AbstractVendingMachine implements State {

	private State state;
	private DrinkChamber drinkChamber;
	private double amountPaid;
	private final String ENGAGING = "Still engaging";
	private final String EXIT = "Exit";
	private IVendingMachineVisitor visitor;

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	public VendingMachine() {
		state = new InactiveState();
	}

	/**
	 * This is the method which make Vending Machine to Active state. 
	 */
	@Override
	public void enterChoiceAndAmount(String product, String amount) {
		state.enterChoiceAndAmount(product, amount);
		State activeState = new ActiveState();
		if (state instanceof InactiveState) {
			setState(activeState);
			System.out.println("Vending Machine state has been moved to "
					+ state.getClass().getName());
		}

	}

	/**
	 * This is the method which make Vending Machine to Inactive state. 
	 */
	@Override
	public void dispenseProduct() {
		State inactiveState = new InactiveState();
		state.dispenseProduct();
		if (state instanceof ActiveState) {
			setState(inactiveState);
			System.out.println("Vending Machine state has been moved to "
					+ state.getClass().getName());
		}

	}

	/**
	 * This method loads all the product available in Vending Machine
	 */
	@Override
	public void loadInventory() {
		drinkChamber = new DrinkChamber();
		drinkChamber.loadInventory();

	}

	/**
	 * This method prints all the menu items to the console
	 */
	@Override
	public void displayMenu() {
		System.out.println("Please select your drink from the menu:\n");
		System.out.println("\t" + VendingMachineUtil.SelectionMenu.COLA
				+ "\t\tprice: [" + drinkChamber.cola.price + "]cents"
				+ "\tstill have: [" + drinkChamber.getColaCount().toString()
				+ "]can\n" + "\t" + VendingMachineUtil.SelectionMenu.COFFEE
				+ "\t\tprice: [" + drinkChamber.coffee.price + "]cents"
				+ "\tstill have: [" + drinkChamber.getCoffeeCount().toString()
				+ "]can\n" + "\t"
				+ VendingMachineUtil.SelectionMenu.ORANGE_JUICE + "\tprice: ["
				+ drinkChamber.oj.price + "]cents" + "\tstill have: ["
				+ drinkChamber.getOJCount().toString() + "]can\n" + "\t"
				+ "QUIT\n\n" + "Enter:");

	}

	/**
	 * This method is the entry point for all the business logic while dispensing any product
	 */
	@Override
	public void executeTask() {
		while (true) {
			if (captureInputAndRespond().equals("Exit"))
				break;
		}
		

	}
	
	/**
	 * This method reads the Item user has selected to purchase
	 * @return
	 */
	private String captureInputAndRespond() {
		String selection = null;

		BufferedReader choosen = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			selection = choosen.readLine();
		} catch (IOException e) {
			System.out.println("Error in reading input.");
			System.exit(1);
		}

		if (selection != null) {
			try {
				if (!selection.equals(VendingMachineUtil.SelectionMenu.QUIT))
					System.out.printf("You have selected " + selection + "\n");
				processSelection(selection, false);
			} catch (Exception e) {
				if (e instanceof java.lang.IllegalArgumentException) {
					System.err
							.println("Please select the choice listed in the menu."+"\n\n"+ "Enter : ");
					captureInputAndRespond();
				} else if (e instanceof OutOfStockException) {
					System.err.println(e.toString());
					System.err.println("We have returned your money just now.");
					displayReturningCoins(amountPaid);
				} else {
					System.err.println(e.toString());
				}
				return EXIT;
			}
			finally{
				dispenseProduct();
			}
		}
		return ENGAGING;
	}
	
	/**
	 * This method reads user choice of product and asks the user to insert money
	 * @param selection
	 * @param paymentOK
	 * @throws Exception
	 */
	private void processSelection(String selection, boolean paymentOK)
			throws Exception {
		visitor = new VendingMachineVisitorImpl();
		switch (VendingMachineUtil.SelectionMenu.valueOf(selection)) {
		case COLA:
			if (paymentOK) {
				drinkChamber.takeACola();
			} else {
				System.out.printf(
						"The price is %d cents, please put in the coin.\n\n"+"Enter : ",
						(int) drinkChamber.cola.price);
				captureMoney(selection, new Cola().accept(visitor));
			}
			break;
		case COFFEE:
			if (paymentOK) {
				drinkChamber.takeACoffee();
			} else {
				System.out.printf(
						"The price is %d cents, please put in the coin.\n",
						(int) drinkChamber.coffee.price);
				captureMoney(selection, new Coffee().accept(visitor));
			}
			break;
		case ORANGE_JUICE:
			if (paymentOK) {
				drinkChamber.takeAOJ();
			} else {
				System.out.printf(
						"The price is %d cents, please put in the coin.\n",
						(int) drinkChamber.oj.price);
				captureMoney(selection, new OrangeJuice().accept(visitor));
			}
			break;
		case QUIT:
			throw new UserExitSelection();
		}
	}
	
	/**
	 * This method reads the money amount user has inserted
	 * @param selection
	 * @param price
	 * @throws Exception
	 */
	private void captureMoney(String selection, double price) throws Exception {
		String amount = null;

		BufferedReader coins = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			amount = coins.readLine();
			enterChoiceAndAmount(selection, amount);
			if (amount != null) {
				double change = calculateChange(price, amount);
				if (change >= 0.0) {
					processSelection(selection, true);
					if (change > 0.0) {
						System.out.println("Your change is: " + change
								+ " cents");
						displayReturningCoins(change);
						System.out
								.println("Thank you for your business, you see again! Exiting....\n\n\n\n");
					}
				} else {
					System.out
							.println("You did not put enough money, please put in more coin.");
					captureMoney(selection, price);
				}
			}
		} catch (IOException e) {
			System.out.println("Error in reading input.");
			System.exit(1);
		}
	}
	
	/**
	 * This method calculates the change has to be returned back to the user
	 * @param price
	 * @param insertedCoins
	 * @return
	 */
	private double calculateChange(double price, String insertedCoins) {
		StringTokenizer st = new StringTokenizer(insertedCoins);
		while (st.hasMoreElements()) {
			String coin = st.nextToken();
			if (coin.equals("PENNY")) {
				amountPaid += Coin.PENNY.value;
			} else if (coin.equals("NICKEL")) {
				amountPaid += Coin.NICKEL.value;
			} else if (coin.equals("DIME")) {
				amountPaid += Coin.DIME.value;
			} else if (coin.equals("QUARTER")) {
				amountPaid += Coin.QUARTER.value;
			} else if (coin.equals("DOLLAR")) {
				amountPaid += Coin.DOLLAR.value;
			}
		}
		System.out.println("You have paid " + amountPaid + " cents");
		return amountPaid - price;
	}
	
	/**
	 * This method prints the coin denominations to be returned back to the user as a part of change
	 * @param change
	 */
	private void displayReturningCoins(double change) {
		System.out.println("Returning coin: ");
		if (change / VendingMachineUtil.Coin.DOLLAR.value >= 1) {
			int numDollar = (int) change
					/ (int) VendingMachineUtil.Coin.DOLLAR.value;
			change = change
					- (numDollar * VendingMachineUtil.Coin.DOLLAR.value);
			System.out.println(numDollar + " dollar ");
		}
		if (change / Coin.QUARTER.value >= 1) {
			int numQuarter = (int) change / (int) Coin.QUARTER.value;
			change = change - (numQuarter * Coin.QUARTER.value);
			System.out.println(numQuarter + " quarter ");
		}
		if (change / Coin.DIME.value >= 1) {
			int numDime = (int) change / (int) Coin.DIME.value;
			change = change - (numDime * Coin.DIME.value);
			System.out.println(numDime + " dime ");
		}
		if (change / Coin.NICKEL.value >= 1) {
			int numNickel = (int) change / (int) Coin.NICKEL.value;
			change = change - (numNickel * Coin.NICKEL.value);
			System.out.println(numNickel + " nickel ");
		}
		if (change / Coin.PENNY.value >= 1) {
			int numPenny = (int) change / (int) Coin.PENNY.value;
			change = change - (numPenny * Coin.PENNY.value);
			System.out.println(numPenny + " penny ");
		}
	}

}
