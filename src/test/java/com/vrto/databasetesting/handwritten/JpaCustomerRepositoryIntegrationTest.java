package com.vrto.databasetesting.handwritten;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.AppConfig;
import lombok.val;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Before;
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
public class JpaCustomerRepositoryIntegrationTest {

    @Autowired
    JpaCustomerRepository jpaCustomerRepository;

    @Before
    public void setUp() {
        QueryCountHolder.clear();
    }

    @Test
    @DatabaseSetup("/dbunit/customers.xml")
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

        assertThat(customer.getFirstName()).isEqualTo("Wayne");
        assertThat(customer.getLastName()).isEqualTo("Gretzky");

        assertNumberOfSelects(1);
    }

    private void assertNumberOfSelects(int expected) {
        int selects = QueryCountHolder.getGrandTotal().getSelect();
        assertThat(selects).isEqualTo(expected);
    }

}
