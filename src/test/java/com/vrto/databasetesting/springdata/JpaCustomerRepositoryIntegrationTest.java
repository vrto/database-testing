package com.vrto.databasetesting.springdata;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.RepositoryTest;
import com.vrto.databasetesting.handwritten.JpaCustomerRepository;
import lombok.val;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class JpaCustomerRepositoryIntegrationTest extends RepositoryTest {

    @Autowired
    JpaCustomerRepository jpaCustomerRepository;

    @Test
    @DatabaseSetup(value = "/dbunit/customers.xml")
    public void shouldFindNothing_WhenCustomerDoesNotExist() {
        long nonExisting = -1;
        val found = jpaCustomerRepository.findOne(nonExisting);
        assertThat(found.isPresent()).isFalse();
    }

    @Test
    @DatabaseSetup("/dbunit/customers.xml")
    public void shouldFindCustomerById() {
        long existing = 1;
        val customer = jpaCustomerRepository.findOne(existing).get();

        assertThat(customer.getFirstName()).isEqualTo("The");
        assertThat(customer.getLastName()).isEqualTo("Dude");

        assertNumberOfSelects(1);
    }

    private void assertNumberOfSelects(int expected) {
        int selects = QueryCountHolder.getGrandTotal().getSelect();
        assertThat(selects).isEqualTo(expected);
    }

    @Test
    @DatabaseSetup({"/dbunit/customers.xml", "/dbunit/customer_social_media.xml"})
    public void socialMediaIsLazyLoaded() {
        val customer = jpaCustomerRepository.findOne(2L).get();
        assertNumberOfSelects(1);

        val socialMedia = customer.getSocialMedia().get(0);
        assertThat(socialMedia).isNotNull();
        assertNumberOfSelects(2);
    }

}
