/**
 * 
 */
package com.medium.danieldiasjava.jakarta.tomee.produces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

/**
 * @author daniel
 *
 */
@ApplicationScoped
public class BookStoreRepositoryProduce {

	@PersistenceUnit
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(@Disposes @Default EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
}