package com.bank.retailbank.repository;

import com.bank.retailbank.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
