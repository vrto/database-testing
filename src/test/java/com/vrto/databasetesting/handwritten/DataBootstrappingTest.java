package com.vrto.databasetesting.handwritten;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.Customer;
import com.vrto.databasetesting.CustomerSocialMedia;
import com.vrto.databasetesting.RepositoryTest;
import com.vrto.databasetesting.SocialMedia;
import com.vrto.databasetesting.springdata.SpringCustomerRepository;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.fest.assertions.Assertions.assertThat;

public class DataBootstrappingTest extends RepositoryTest {

    @Autowired
    SpringCustomerRepository springCustomerRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JpaTransactionManager tm;

    @Test
    public void shouldFindCustomer_UsingManualBootstrapping() {
        // given this setup
        new TransactionTemplate(tm).execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                val customerWithTwitter = new Customer();
                customerWithTwitter.setFirstName("Twitter");
                entityManager.persist(customerWithTwitter);
                val twitter = new CustomerSocialMedia(customerWithTwitter.getId(), SocialMedia.TWITTER);
                entityManager.persist(twitter);

                val customerWithFacebook = new Customer();
                customerWithFacebook.setFirstName("Facebook");
                entityManager.persist(customerWithFacebook);
                val facebook = new CustomerSocialMedia(customerWithFacebook.getId(), SocialMedia.FACEBOOK);
                entityManager.persist(facebook);
            }
        });

        // assert that the repository works properly
        val customers = springCustomerRepository.findCustomersWithTwitter();
        assertThat(customers.size()).isEqualTo(1);

        val customer = customers.get(0);
        assertThat(customer.getFirstName()).isEqualTo("Twitter");
    }

    @Test
    @DatabaseSetup({"/dbunit/customers.xml", "/dbunit/customer_social_media.xml"})
    public void shouldFindCustomer_UsingFixtureBootstrapping() {
        val customers = springCustomerRepository.findCustomersWithTwitter();
        assertThat(customers.size()).isEqualTo(2);

        val customer = customers.get(0);
        assertThat(customer.getId()).isEqualTo(2);
    }
}
