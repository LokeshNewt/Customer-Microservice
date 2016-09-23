package com.newt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.newt.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>{

 
    public Customer findCustomerBycustomerId(int customerId);
    public Customer findCustomerByfirstName(String firstName);
    
	 

}
