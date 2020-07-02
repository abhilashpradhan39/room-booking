package com.people10.springboot.rest.challenge.booking.repository;

import com.people10.springboot.rest.challenge.booking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    //Repository Interface for CRUD

}
