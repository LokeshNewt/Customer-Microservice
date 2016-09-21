package com.newt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.newt.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>{

 
    public Customer findCustomerBycustomerId(Integer customerId);
    public Customer findCustomerByfirstName(String firstName);
   // public Customer findCustomerBylastName(String lastName);
	 
	 

}