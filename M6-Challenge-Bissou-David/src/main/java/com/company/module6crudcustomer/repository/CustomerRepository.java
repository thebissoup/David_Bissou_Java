package com.company.module6crudcustomer.repository;

import com.company.module6crudcustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomerByState(String state);
}