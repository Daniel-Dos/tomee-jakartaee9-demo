/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.service;

import java.util.List;

import com.medium.danieldiasjava.jakarta.tomee.model.Book;

/**
 * @author daniel
 *
 */
public interface BookStoreService {

	public List<Book> getBooks();
	public void saveBook(Book book);
}
