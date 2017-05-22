package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;
import lombok.val;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Customer> findOne(long customerId) {
        val query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // these methods don't matter now

    @Override
    public List<Customer> findAll() { return Collections.emptyList(); }

    @Override
    public void save(Customer customer) {}

    @Override
    public void delete(long customerId) {}

    public List<Customer> findCustomersByAgeAndCountryCode(int age, String countryCode) {
        val queryString = "SELECT c FROM Customer c WHERE c.age = :age AND c.countryCode = :code";
        val query = entityManager.createQuery(queryString, Customer.class);
        query.setParameter("age", age);
        query.setParameter("code", countryCode);

        return query.getResultList();
    }
}
