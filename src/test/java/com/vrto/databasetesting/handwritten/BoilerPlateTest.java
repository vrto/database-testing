package com.vrto.databasetesting.handwritten;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.AppConfig;
import com.vrto.databasetesting.springdata.SpringCustomerRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class BoilerPlateTest {

    @Autowired
    JpaCustomerRepository boilerplateRepository;

    @Autowired
    SpringCustomerRepository boilerplateFreeRepository;

    @Test
    @DatabaseSetup("/dbunit/customers.xml")
    public void bothRepositoriesWorkSame() {
        expectedCustomers(51, "US", 0);
        expectedCustomers(100, "CA", 0);
        expectedCustomers(51, "CA", 1);
    }

    private void expectedCustomers(int age, String countryCode, int expectedCount) {
        val boilerplateResults = boilerplateRepository.findCustomersByAgeAndCountryCode(age, countryCode);
        assertThat(boilerplateResults.size()).isEqualTo(expectedCount);

        val boilerplateFreeResults = boilerplateFreeRepository.findCustomersByAgeAndCountryCode(age, countryCode);
        assertThat(boilerplateFreeResults.size()).isEqualTo(expectedCount);
    }
}
