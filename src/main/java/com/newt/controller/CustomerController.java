package com.newt.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

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
  private  CustomerRepository customerRepository;
	
	
	/*@POST
	@RequestMapping("add/{firstName}/{lastName}/{age}/{gender}/{dob}/{address1}/{address2}/{city}/{state}/{country}/{postalCode}/{phone}/{status}/{emailId}/{username}/{password}") 
	@ApiOperation(value = "post a customer")
	public Integer registerCustomer(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName,@PathVariable("gender") String gender,@PathVariable("age") Integer age,@PathVariable("dob") Date dob,@PathVariable("address1") String address1,@PathVariable("address2") String address2,@PathVariable("city") String city,@PathVariable("state") String state,@PathVariable("country") String country,@PathVariable("postalCode") String postalCode,@PathVariable("phone") String phone,@PathVariable("status") String status,@PathVariable("emailId") String emailId,@PathVariable("username") String username,@PathVariable("password") String password)
	{
		//System.out.println("Adding Employee Details===============:"+name+" "+" "+dept+" "+sal+" "+yoexp);
		System.out.println("Adding customer details into DB....");
		 Customer customer = new Customer();
		 customer.setFirstName(firstName);
		 customer.setLasstName(lastName);
		 customer.setAge(age);
		 customer.setDob(dob);
		 customer.setAddress1(address1);
		 customer.setAddress2(address2);
		 customer.setCity(city);
		 customer.setState(state);
		 customer.setCountry(country);
		 customer.setPostalCode(postalCode);
		 customer.setPhone(phone);
		 customer.setStatus(status);
		 customer.setEmailId(emailId);
		 customer.setUsername(username);
		 customer.setPassword(password);
		 customerRepository.save(customer);
		 System.out.println("Successfully added...!");
	return customer.getCustomerId();
		
	}*/
	//creating customer services
	
	@ApiOperation(value = "post a customer")
    @RequestMapping(method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer) {
		Customer customer1 =  (Customer) customerRepository.findAll();
		if(customer1.getUsername().equals(customer.getUsername())||customer1.getEmailId().equals(customer.getEmailId())){
			System.out.println("Uesr anme and email id is exit plz try another one");
		}
		else{
			customerRepository.save(customer);
		}
		
		
        return  customer;
    }
	
	
	/*@RequestMapping(value="searching/{customerID}",method = RequestMethod.GET, produces = "application/json") 
	@ApiOperation(value = "get customer id")
	public Customer findCustomerbyID(@PathVariable("customerId") Integer customerId)
	{
		System.out.println("Controller Searching for Customer id is ===============:"+customerId);
		
		
		return customerRepository.findOne(customerId);    
		
		
 
	}*/
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer findCustomerbyID(@PathVariable Integer id) {
		Customer customer =
                     customerRepository.findCustomerBycustomerId(id);
        
	    if(customer.getCustomerId().equals(id)){
	    	customer= customerRepository.findOne(id); 
	    	
	    }else{
	    	System.out.println("Customer Id is not found");
	    	
    }
	    return customer;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping("searching/all customer") 
	@ApiOperation(value = "get all customers")
	public List<Customer> listCustomers()
	{
		System.out.println("Searching all customer details===============:\n");
		
		List<Customer> list=(List<Customer>) customerRepository.findAll();
		//testing.add(nameVal);
	return list;
	}
	
	@RequestMapping(value="searching/{firstName}",method = RequestMethod.GET, produces = "application/json") 
	@ApiOperation(value = "get customer names1")
	public Customer findCustomerbyFirstName(@PathVariable("firstName") String firstName)
	{
	   
		 Customer customer = new Customer();
		System.out.println("Controller Searching for Employee Firstname ===============:"+firstName );
		  customer = customerRepository.findCustomerByfirstName(firstName);
		
		  
	       
		    if(customer.getFirstName().equals(firstName)){
		    	 
		    	System.out.println("Customer First Name isfound:");
		    	
		    }else{
	           System.out.println("Customer FirstName is not found:");  
	    }
 
		return customer;	
	}
	
	/*@RequestMapping(value="searching/{lastName}",method = RequestMethod.GET, produces = "application/json") 
	@ApiOperation(value = "get customer names2")
	public Customer findCustomerbyLastName(@PathVariable("lastName") String lastName)
	{
		System.out.println("Controller Searching for Employee Lastname ===============:"+lastName );
		 Customer customer = customerRepository.findCustomerBylastName(lastName);
		
		  
	       List<Customer> list=null;
		    if(customer.getFirstName()!=lastName){
		    	System.out.println("Customer Last Name is not found");
		    	
		    }else{
	           list = (List<Customer>) customer;    
	    }
		    return (Customer) list;
	}*/
}
