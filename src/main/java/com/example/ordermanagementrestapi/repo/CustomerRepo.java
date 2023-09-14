package com.example.ordermanagementrestapi.repo;

import com.example.ordermanagementrestapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findById(int customerId);

    Customer findByCustomerName(String customerName);

}
