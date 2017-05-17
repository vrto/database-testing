package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ManualRepositoryIntegrationTest {

    @Autowired
    JpaCustomerRepository customerRepository;

    @Test
    public void repositoryCanBeAssembled() {
        assertThat(customerRepository).isNotNull();
    }
}
