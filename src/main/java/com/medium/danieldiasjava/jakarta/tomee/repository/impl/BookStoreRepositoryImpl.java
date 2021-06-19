/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.repository.impl;

import java.util.List;

import com.medium.danieldiasjava.jakarta.tomee.annotations.Repository;
import com.medium.danieldiasjava.jakarta.tomee.model.Book;
import com.medium.danieldiasjava.jakarta.tomee.repository.BookStoreRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

/**
 * @author daniel
 *
 */
@Repository
public class BookStoreRepositoryImpl implements BookStoreRepository {

	@Inject
	private EntityManager em;
	
	@Override
	public List<Book> getBooks() {
		this.em.getTransaction().begin();
		return this.em.createQuery("SELECT e FROM Book e",Book.class).getResultList();
	}

	@Override
	public void saveBook(Book book) {
		this.em.getTransaction().begin();
		this.em.persist(book);
		this.em.getTransaction().commit();
		
	}
}