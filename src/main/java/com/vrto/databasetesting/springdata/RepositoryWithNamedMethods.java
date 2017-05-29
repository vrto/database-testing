package com.vrto.databasetesting.springdata;

import com.vrto.databasetesting.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryWithNamedMethods extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c, CustomerSocialMedia sm " +
            "WHERE c.age < 65 " +
            "AND c.countryCode IN ('US', 'CA') " +
            "AND c.id = sm.customerId " +
            "AND sm.socialMedia = com.vrto.databasetesting.SocialMedia.TWITTER")
    List<Customer> findYoungNorthAmericansOnTwitter();

    @Query("SELECT c FROM Customer c WHERE c.age > 65 AND c.countryCode = 'EU'")
    List<Customer> findSeniorEuropeans();
}
