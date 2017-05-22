package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findOne(long customerId);

    List<Customer> findAll();

    void save(Customer customer);

    void delete(long customerId);

}
