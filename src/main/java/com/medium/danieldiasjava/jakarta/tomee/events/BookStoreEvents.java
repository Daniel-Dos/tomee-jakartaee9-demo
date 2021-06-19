/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.events;

import com.medium.danieldiasjava.jakarta.tomee.model.Book;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;

/**
 * @author daniel
 *
 */
@Named
public class BookStoreEvents {

	public void observeEvent(@Observes Book book) {
		System.out.println("Your order has been sent for processing...");
	}
}
