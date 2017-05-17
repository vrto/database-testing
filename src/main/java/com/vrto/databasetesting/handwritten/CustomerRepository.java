package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;

public interface CustomerRepository {

    Customer findOne(long customerId);

    Customer findAll();

    void save(Customer customer);

    void delete(long customerId);

}
