package com.people10.springboot.rest.challenge.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.people10.springboot.rest.challenge.booking.entity.Customer;
import com.people10.springboot.rest.challenge.booking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService service;

    //Test case for successful creation
    @Test
    void addCustomerTestSuccess() throws Exception {
        Customer temp = new Customer(5,"Abhilash","Pradhan",null,"abc@gmail.com","abcdabcd");

        when(service.saveCustomer(Mockito.any(Customer.class)))
                .thenReturn(temp);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(temp))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(201))
                .andReturn();
    }
    //Test case for creation failure due to invalid password
    @Test
    void addCustomerTestFailPassword() throws Exception {
        Customer temp = new Customer(5,"Abhilash","Pradhan",null,"abc@gmail.com","abcdabcdabcd");

        when(service.saveCustomer(Mockito.any(Customer.class)))
                .thenReturn(temp);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(temp))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(400))
                .andReturn();
    }
    //Method to convert Customer entity into JSON
    private static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Test case for matching ID
    @Test
    void findCustomerByIdTestMatched() throws Exception{
        when(service.getCustomerByID(5))
                .thenReturn(new Customer(5,"Abhilash","Pradhan",null,"abc@gmail.com","abcdabcd"));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customer/5")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(200))
                .andReturn();

    }
    //Test case for no matching id
    @Test
    void findCustomerByIdTestUnmatched() throws Exception{
        when(service.getCustomerByID(5))
                .thenReturn(new Customer(5,"Abhilash","Pradhan",null,"abc@gmail.com","abcdabcd"));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customer/50")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(404))
                .andReturn();

    }
}