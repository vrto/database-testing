package com.vrto.databasetesting.springdata;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vrto.databasetesting.RepositoryTest;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.fest.assertions.Assertions.assertThat;

public class RepositoryWithNamedMethodsTest extends RepositoryTest {

    @Autowired
    RepositoryWithNamedMethods repository;

    @Test
    @DatabaseSetup({"/dbunit/customers.xml", "/dbunit/customer_social_media.xml"})
    public void shouldFindNorthAmericans_OnTwitter() {
        val youngNAs = repository.findYoungNorthAmericansOnTwitter();

        // 3 North Americans, one hasn't got Twitter, one is over 65, only one matches the criteria
        assertThat(youngNAs.size()).isEqualTo(1);
        assertThat(youngNAs.get(0).getId()).isEqualTo(2);
    }

    @Test
    @DatabaseSetup({"/dbunit/customers.xml", "/dbunit/customer_social_media.xml"})
    public void shouldFindSeniorEuropeans() {
        val seniorEUs = repository.findSeniorEuropeans();

        // 2 Europeans, one is below 65, one matches criteria
        assertThat(seniorEUs.size()).isEqualTo(1);
        assertThat(seniorEUs.get(0).getId()).isEqualTo(5);
    }
}