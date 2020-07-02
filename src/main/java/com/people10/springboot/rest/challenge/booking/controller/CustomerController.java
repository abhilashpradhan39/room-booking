package com.people10.springboot.rest.challenge.booking.controller;

import com.people10.springboot.rest.challenge.booking.exception.BadRequestException;
import com.people10.springboot.rest.challenge.booking.exception.NotFoundException;
import com.people10.springboot.rest.challenge.booking.entity.Customer;
import com.people10.springboot.rest.challenge.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("/api/customer")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        if(customer.getPassword().length()<8 || customer.getPassword().length()>10)
            throw new BadRequestException();
        return service.saveCustomer(customer);
    }
    @GetMapping("/api/customer/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        if(service.getCustomerByID(id)==null)
            throw new NotFoundException();
        return service.getCustomerByID(id);
    }
    @PutMapping("/api/update")
    public Customer updateCustomer(@RequestBody Customer customer){
        return service.updateCustomer(customer);
    }
    @DeleteMapping("/api/delete/{id}")
    public String deleteCustomer(@PathVariable int id){
        return service.deleteCustomer(id);
    }
}
