package com.example.demo.services;


import com.example.demo.models.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
   private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
       return customerRepository.save(customer);
    }

    public List<Customer>  getCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomers(List<Integer> ids) {
        customerRepository.deleteAllById(ids);
    }

    public void updateCustomers(List<Customer> updatedCustomers) {

        List<Integer> ids=updatedCustomers.stream().map(Customer::getId).toList();

        List<Customer>  existingCustomers=customerRepository.findAllById(ids);

        for(Customer existingCustomer:existingCustomers){
            Optional<Customer>  updatedCustomerOpt=updatedCustomers.stream().filter(c->c.getId()==existingCustomer.getId()).findFirst();
            if (updatedCustomerOpt.isPresent()){

                Customer updatedCustomer=updatedCustomerOpt.get();

                existingCustomer.setFirstName(updatedCustomer.getFirstName());
                existingCustomer.setLastName(updatedCustomer.getLastName());
                existingCustomer.setEmail(updatedCustomer.getEmail());
                existingCustomer.setJobTitle(updatedCustomer.getJobTitle());
                existingCustomer.setComments(updatedCustomer.getComments());
                existingCustomer.setPhone(updatedCustomer.getPhone());

            }

        }
        customerRepository.saveAll(existingCustomers);

    }
}
