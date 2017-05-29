package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.RepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class ManualRepositoryIntegrationTest extends RepositoryTest {

    @Autowired
    JpaCustomerRepository customerRepository;

    @Test
    public void repositoryCanBeAssembled() {
        assertThat(customerRepository).isNotNull();
    }
}
