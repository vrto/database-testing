package com.vrto.databasetesting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Customer {

    Long id;

    String firstName;

    String lastName;

    Integer age;

}
