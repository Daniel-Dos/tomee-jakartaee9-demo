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
package com.medium.danieldiasjava.jakarta.tomee.controller;

import com.medium.danieldiasjava.jakarta.tomee.dto.BookDTO;
import com.medium.danieldiasjava.jakarta.tomee.dto.ResponseDTO;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.service.BookStoreEmbeddedStorageService;
import com.medium.danieldiasjava.jakarta.tomee.service.BookStoreService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Daniel Dias
 *
 */
@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookStoreController {

	@Inject
	private BookStoreService bookStoreService;
	
	@Inject
	private BookStoreEmbeddedStorageService bookStoreEmbeddedStorageService;
	
	@GET
	@Path("/v1")
	public Response getBooks() {
		
		var books = bookStoreService.getBooks();
		if(books.isEmpty()) {
			return Response.status(Status.OK).entity(new ResponseDTO(books,Status.NO_CONTENT.getStatusCode())).build();	
		}
		return Response.status(Status.OK).entity(new ResponseDTO(books,Status.OK.getStatusCode())).build();
	}

	@POST
	@Path("/v1")
	public Response saveBooks(@Valid BookDTO bookDto) {
		this.bookStoreService.saveBook(bookDto);
		return Response.status(Status.CREATED).entity(new ResponseDTO(bookDto,"saved!",Status.CREATED.getStatusCode())).build();
	}
	
	@GET
	@Path("/v2")
	public Response getBooksEmbeddedStorage() {
		
		var books = bookStoreEmbeddedStorageService.getBooks();
		if(books.isEmpty()) {
			return Response.status(Status.OK).entity(new ResponseDTO(books,Status.NO_CONTENT.getStatusCode())).build();	
		}
		return Response.status(Status.OK).entity(new ResponseDTO(books,Status.OK.getStatusCode())).build();
	}

	@POST
	@Path("/v2")
	public Response saveBooksEmbeddedStorage(@Valid BookDTO bookDto) {
		this.bookStoreEmbeddedStorageService.saveBook(bookDto);
		return Response.status(Status.CREATED).entity(new ResponseDTO(bookDto,"saved!",Status.CREATED.getStatusCode())).build();
	}
}