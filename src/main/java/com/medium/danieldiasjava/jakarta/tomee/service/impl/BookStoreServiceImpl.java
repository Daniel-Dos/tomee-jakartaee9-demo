/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.service.impl;

import java.util.List;

import com.medium.danieldiasjava.jakarta.tomee.annotations.Service;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.repository.BookStoreRepository;
import com.medium.danieldiasjava.jakarta.tomee.service.BookStoreService;

import jakarta.inject.Inject;

/**
 * @author daniel
 *
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

	@Inject
	private BookStoreRepository bookStoreService;
	
	@Override
	public List<Book> getBooks() {
		return this.bookStoreService.getBooks();
	}

	@Override
	public void saveBook(Book book) {
		this.bookStoreService.saveBook(book);
	}

}
