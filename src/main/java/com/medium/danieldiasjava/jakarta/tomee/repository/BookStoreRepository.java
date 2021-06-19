/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.repository;

import java.util.List;

import com.medium.danieldiasjava.jakarta.tomee.model.Book;

/**
 * @author daniel
 *
 */
public interface BookStoreRepository {

	public List<Book> getBooks();
	public void saveBook(Book book);
}
