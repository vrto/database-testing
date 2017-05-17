package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;
import lombok.val;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Customer findOne(long customerId) {
        val query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        return query.getSingleResult();
    }

    @Override
    public Customer findAll() { return null; }

    @Override
    public void save(Customer customer) {}

    @Override
    public void delete(long customerId) {}
}
