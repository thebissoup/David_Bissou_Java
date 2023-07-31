package com.company.customerdataservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.customerdataservice.controller.CustomerController;
import com.company.customerdataservice.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addCustomerTest() throws Exception {

        Customer newCustomer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");

        String newCustomerJson = mapper.writeValueAsString(newCustomer);


        mockMvc.perform(post("/customers")
                        .content(newCustomerJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomerTest() throws Exception {

        Customer newCustomer = new Customer(123, "John", "Doe", "johndoe@example.com",
                "ABC Inc.", "1234567890", "123 Main Street", "Apt 4B",
                "New York City", "NY", "10001", "United States");

        String newCustomerJson = mapper.writeValueAsString(newCustomer);


        mockMvc.perform(put("/customers")
                        .content(newCustomerJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCustomerTest() throws Exception {

        mockMvc.perform(delete("/customers/{id}", "11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCustomerByIdTest() throws Exception {


        mockMvc.perform(get("/customers/{id}", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void getCustomersByState() throws Exception {


        mockMvc.perform(get("/state/{state}", "CA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
