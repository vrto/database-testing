package com.vrto.databasetesting.springdata;

import com.vrto.databasetesting.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringCustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c, CustomerSocialMedia sm " +
            "WHERE c.id = sm.customerId " +
            "AND sm.socialMedia = com.vrto.databasetesting.SocialMedia.TWITTER")
    List<Customer> findCustomersWithTwitter();

    List<Customer> findCustomersByAgeAndCountryCode(int age, String countryCode);
}
