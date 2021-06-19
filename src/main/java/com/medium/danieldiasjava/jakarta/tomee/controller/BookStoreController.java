/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.controller;

import com.medium.danieldiasjava.jakarta.tomee.dto.ResponseDTO;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.service.BookStoreService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author daniel
 */
@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookStoreController {

	@Inject
	private BookStoreService bookStoreService;
	
	@GET
	public Response getBooks() {
		return Response.ok().entity(new ResponseDTO(bookStoreService.getBooks())).build();
	}

	@POST
	public Response saveBooks(Book book) {
		this.bookStoreService.saveBook(book);
		return Response.status(Status.CREATED).entity(new ResponseDTO(book,"saved!")).build();
	}
}