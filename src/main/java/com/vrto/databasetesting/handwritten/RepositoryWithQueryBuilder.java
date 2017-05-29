package com.vrto.databasetesting.handwritten;

import com.vrto.databasetesting.Customer;
import com.vrto.databasetesting.SocialMedia;
import lombok.val;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RepositoryWithQueryBuilder {

    @PersistenceContext
    EntityManager entityManager;

    List<Customer> findCustomers(boolean northAmericans, int minAge, SocialMedia socialMedia) {
        if (minAge <= 0) {
            throw new IllegalArgumentException("minAge must be a positive number");
        }

        String queryString = "SELECT c FROM Customer c, CustomerSocialMedia sm WHERE ";
        if (northAmericans) {
            queryString += "c.countryCode IN ('US', 'CA') ";
        } else {
            queryString = "c.countryCode NOT IN ('US', 'CA') ";
        }

        if (socialMedia != SocialMedia.TWITTER && socialMedia != SocialMedia.FACEBOOK) {
            throw new IllegalArgumentException("The system only supports Twitter and Facebook at the moment");
        } else if (minAge < 65) {
            queryString += "c.socialMedia = " + socialMedia;
        } // else any social media for seniors

        val query = entityManager.createQuery(queryString, Customer.class);
        return query.getResultList();
    }
}
