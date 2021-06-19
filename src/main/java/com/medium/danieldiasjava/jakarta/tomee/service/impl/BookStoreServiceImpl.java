/*
 * The MIT License
 * Copyright © 2021 Daniel Dias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.medium.danieldiasjava.jakarta.tomee.service.impl;

import java.util.List;

import com.medium.danieldiasjava.jakarta.tomee.annotations.Service;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.repository.BookStoreRepository;
import com.medium.danieldiasjava.jakarta.tomee.service.BookStoreService;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

/**
 * @author Daniel Dias
 *
 */
@Service
public class BookStoreServiceImpl implements BookStoreService {

	@Inject
	private BookStoreRepository bookStoreRepository;
	
	@Inject
	private Event<Book> bookEvent;
	
	@Override
	public List<Book> getBooks() {
		return this.bookStoreRepository.getBooks();
	}

	@Override
	public void saveBook(Book book) {
		this.bookStoreRepository.saveBook(book);
		this.bookEvent.fire(book);
	}
}