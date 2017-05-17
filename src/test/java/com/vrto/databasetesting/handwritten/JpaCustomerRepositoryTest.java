package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaCustomerRepositoryTest {

    @Mock
    EntityManager entityManager;

    @Mock
    TypedQuery<Customer> query;

    @InjectMocks
    JpaCustomerRepository customerRepository;

    @Test
    public void shouldFindOneCustomer() {
        when(entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :customerId", Customer.class)).thenReturn(query);
        when(query.setParameter("customerId", 1)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(new Customer());

        val customer = customerRepository.findOne(1);
        assertThat(customer).isNotNull();
    }
}