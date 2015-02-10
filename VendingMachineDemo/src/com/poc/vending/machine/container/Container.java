/**
 * 
 */
package com.poc.vending.machine.container;

import java.util.HashMap;
import java.util.Map;

import com.poc.vending.machine.visitor.Drink;

/**
 * This class acts as an inventory for Vending Machine
 * @author achak7
 *
 */
public class Container <T extends Drink, I> {
    
    private Map<T,I> items = new HashMap<T,I>();
   
    public void addItem(T item, I count) {
            items.put(item, count);
    }
   
    public I getItemCount(T item) {
            return items.get(item);
    }
}

