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
package com.medium.danieldiasjava.jakarta.tomee.repository.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.medium.danieldiasjava.jakarta.tomee.annotations.Repository;
import com.medium.danieldiasjava.jakarta.tomee.dto.BookDTO;
import com.medium.danieldiasjava.jakarta.tomee.dto.mapper.BookDTOMapper;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.repository.BookEmbeddedStorageRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

/**
 * @author Daniel Dias
 *
 */
@Repository
public class BookEmbeddedStorageRepositoryImpl implements BookEmbeddedStorageRepository {

	@Inject
	private BookDTOMapper bookDTOMapper;
	
	@Inject
	private EmbeddedStorageManager embeddedStorageManager;
	
	private List<Book> bookList = new ArrayList<>();

	@PostConstruct
	private void createDataBase() {

		if (this.embeddedStorageManager.root() == null) {
			this.embeddedStorageManager.setRoot(this.bookList);	
		} else {
			this.bookList = (List<Book>) this.embeddedStorageManager.root();
		}
	}
	
	private void storeAll() {
		this.embeddedStorageManager.store(this.bookList);
	}

	@Override
	public void saveBook(BookDTO book) {
		this.bookList.add(this.bookDTOMapper.bookDtoToBook(book));
		storeAll();
	}

	@Override
	public List<BookDTO> getBooks() {
		return (List<BookDTO>) this.bookDTOMapper.bookListToBookDtoList(this.bookList);
	}
}