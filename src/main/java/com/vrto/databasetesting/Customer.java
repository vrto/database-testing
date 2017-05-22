package com.vrto.databasetesting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    Integer age;

    @Column(name = "country_code")
    String countryCode;

}
