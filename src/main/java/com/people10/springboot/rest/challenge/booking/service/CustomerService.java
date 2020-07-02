package com.people10.springboot.rest.challenge.booking.service;

import com.people10.springboot.rest.challenge.booking.exception.BadRequestException;
import com.people10.springboot.rest.challenge.booking.exception.NotFoundException;
import com.people10.springboot.rest.challenge.booking.entity.Customer;
import com.people10.springboot.rest.challenge.booking.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepo repository;

    //Post method for customer
    public Customer saveCustomer(Customer customer){
        try{
            repository.save(customer);
        }
        catch (Exception e){
            throw new BadRequestException();
        }
        return repository.save(customer);
    }
    //Get method for customer by ID
    public Customer getCustomerByID(int id){
        return repository.findById(id).orElse(null);
    }
    //Delete method for customer
    public String deleteCustomer(int id){
        repository.deleteById(id);
        return "Customer deleted with ID "+id;
    }
    //Update method for customer
    public Customer updateCustomer(Customer customer){
        Customer temp = repository.findById(customer.getId()).orElse(null);
        if(temp!=null){
            temp.setFirstName(customer.getFirstName());
            temp.setLastName(customer.getLastName());
            temp.setDob(customer.getDob());
            temp.setEmail(customer.getEmail());
            temp.setPassword(customer.getPassword());
            return repository.save(temp);
        }else
            throw new NotFoundException();
    }

}
