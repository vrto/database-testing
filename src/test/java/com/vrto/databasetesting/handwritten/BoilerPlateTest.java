package com.vrto.databasetesting.handwritten;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.RepositoryTest;
import com.vrto.databasetesting.springdata.SpringCustomerRepository;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class BoilerPlateTest extends RepositoryTest {

    @Autowired
    JpaCustomerRepository boilerplateRepository;

    @Autowired
    SpringCustomerRepository boilerplateFreeRepository;

    @Test
    @DatabaseSetup("/dbunit/customers.xml")
    public void bothRepositoriesWorkSame() {
        expectedCustomers(66, "US", 0);
        expectedCustomers(100, "CA", 0);
        expectedCustomers(66, "CA", 1);
    }

    private void expectedCustomers(int age, String countryCode, int expectedCount) {
        val boilerplateResults = boilerplateRepository.findCustomersByAgeAndCountryCode(age, countryCode);
        assertThat(boilerplateResults.size()).isEqualTo(expectedCount);

        val boilerplateFreeResults = boilerplateFreeRepository.findCustomersByAgeAndCountryCode(age, countryCode);
        assertThat(boilerplateFreeResults.size()).isEqualTo(expectedCount);
    }
}
