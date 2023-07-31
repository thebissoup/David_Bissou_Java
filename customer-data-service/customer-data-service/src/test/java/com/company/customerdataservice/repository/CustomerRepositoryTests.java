package com.company.customerdataservice.repository;


import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;


    @BeforeEach
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer newCustomer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");


        //Act...
        newCustomer = customerRepo.save(newCustomer);

        Optional<Customer> recentlySavedCustomer = customerRepo.findById(newCustomer.getId());

        //Assert...
        assertEquals(recentlySavedCustomer.get(), newCustomer);
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");


        //Act...
        customer = customerRepo.save(customer);

        Optional<Customer> result = customerRepo.findById(customer.getId());

        Customer oldRecord = result.get();

        oldRecord.setEmail("davidebissou@gmail.com");

        Customer newRecord = customerRepo.save(oldRecord);

        //Assert...
        assertNotEquals(customer, newRecord);
    }

    @Test
    public void deleteCustomer() {
        //Arrange...
        Customer customer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");

        customer = customerRepo.save(customer);
        //Act...

        customerRepo.delete(customer);

        Customer finalCustomer = customer;
        assertThrows(NoSuchElementException.class, () -> customerRepo.findById(finalCustomer.getId()).get());
    }


    @Test
    public void findCustomerById() {
        //Arrange...
        Customer customer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");

        //Act...

        customer = customerRepo.save(customer);
        Optional<Customer> result = customerRepo.findById(customer.getId());



        //Assert..
        assertEquals(customer, result.get());
    }

    @Test
    public void findCustomerByState(){
        //Arrange...
        Customer customer1 = new Customer(101, "Alice", "Johnson", "alice@example.com",
                "XYZ Corp.", "9876543210", "789 Park Avenue", "Suite 202",
                "Los Angeles", "CA", "90001", "United States");

        Customer customer2 = new Customer(202, "Bob", "Smith", "bob@example.com",
                "ABC Corp.", "5551234567", "456 Elm Street", "Unit 10",
                "San Francisco", "CA", "94101", "United States");

        Customer customer3 = new Customer(303, "Charlie", "Williams", "charlie@example.com",
                "123 Company", "9998887777", "321 Oak Street", "Apt 5C",
                "San Diego", "CA", "92101", "United States");

        //Act

        customerRepo.save(customer1);
        customerRepo.save(customer2);
        customerRepo.save(customer3);

        List<Customer> results = customerRepo.findCustomerByState("CA");

        for ( Customer result : results){
            assertEquals(result.getState(), "CA");
        }


    }
}
