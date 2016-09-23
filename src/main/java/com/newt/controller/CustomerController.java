package com.newt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.model.Customer;
import com.newt.repository.CustomerRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import javax.ws.rs.PathParam;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "post a customer")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Customer registerCustomer(@RequestBody Customer customer) {
        Customer customer1 = (Customer) customerRepository.findAll();
        if (customer1.getUsername().equalsIgnoreCase(customer.getUsername()) || customer1.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
            System.out.println("Uesr anme and email id is exit plz try another one");
        } else {
            customerRepository.save(customer);
        }

        return customer;
    }

    @ApiOperation(value = "get a customer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Customer findCustomerbyID(@PathVariable int id) {

        Customer customer = null;
        customer = customerRepository.findCustomerBycustomerId(id);
        try {
            if (customer.getCustomerId() == id) {
                customer = customerRepository.findOne(id);

            } else {
                Object obj = "Customer id Notfound";
                customer = (Customer) obj;
                System.out.println("Customer Id is not found");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @RequestMapping(value = "/allcustomers", method = RequestMethod.GET)
    @ApiOperation(value = "get all customers")
    public List<Customer> listCustomers() {
        System.out.println("Searching all customer details===============:\n");
        List<Customer> list = (List<Customer>) customerRepository.findAll();
        //testing.add(nameVal);
        return list;
    }

    @RequestMapping(value = "/byname/{firstName}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get customer names1")
    public Customer findCustomerbyFirstName(@PathVariable("firstName") String firstName) {

        Customer customer;
        System.out.println("Controller Searching for Employee Firstname ===============:" + firstName);
        customer = customerRepository.findCustomerByfirstName(firstName);
        try {
            if (customer.getFirstName().equalsIgnoreCase(firstName)) {

                System.out.println("Customer First Name isfound:");

            } else {
                Object obj = "Customer Name not found";
                customer = (Customer) obj;
                System.out.println("Customer FirstName is not found:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

}
