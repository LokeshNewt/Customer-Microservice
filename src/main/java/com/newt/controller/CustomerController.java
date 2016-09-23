package com.newt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 
import com.newt.model.Customer;
import com.newt.repository.CustomerRepository;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger logger = Logger.getLogger(CustomerController.class);
    @ApiOperation(value = "post a customer")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Customer registerCustomer(@RequestBody Customer customer) {
        Customer customer1 = (Customer) customerRepository.findAll();
        if (customer1.getUsername().equalsIgnoreCase(customer.getUsername()) || customer1.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
        	logger.info("Uesrname and email id is exit, plz try another one");
             
        } else {
            customerRepository.save(customer);
        }

        return customer;
    }

    @ApiOperation(value = "get a customer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Customer findCustomerbyID(@PathVariable int id) {

        Customer customer = null;
        
        try {
        	customer = customerRepository.findCustomerBycustomerId(id);
            if (customer.getCustomerId() == id) {
            	logger.info("Customer Id is found");
                customer = customerRepository.findOne(id);

            } else {
                Object obj = "Customer id Notfound";
                customer = (Customer) obj;
               logger.info("Customer Id is not found");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @RequestMapping(value = "/allcustomers", method = RequestMethod.GET)
    @ApiOperation(value = "get all customers")
    public List<Customer> listCustomers() {
    	 logger.info("Searching all customer details===============:\n");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        //testing.add(nameVal);
        return list;
    }

    @RequestMapping(value = "/byname/{firstName}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get customer names1")
    public Customer findCustomerbyFirstName(@PathVariable("firstName") String firstName) {

        Customer customer= null;
        logger.info("Controller Searching for Employee Firstname ===============:" + firstName);
       
        try {
        	customer = customerRepository.findCustomerByfirstName(firstName);
            if (customer.getFirstName().equalsIgnoreCase(firstName)) {

            	 logger.info("Customer First Name isfound:");

            } else {
                Object obj = "Customer Name not found";
                customer = (Customer) obj;
                logger.info("Customer FirstName is not found:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

}
