package com.example.demo.controllers;


import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer=customerService.saveCustomer(customer);
        return  new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer(){
         List<Customer> getAllCustomers=customerService.getCustomers();
        return  new ResponseEntity<>(getAllCustomers,HttpStatus.OK);

    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteCustomer(@RequestBody List<Integer> ids){
        customerService.deleteCustomers(ids);
        return   ResponseEntity.noContent().build();
    }


    @PutMapping("/update")
    public ResponseEntity<List<Customer>> updateCustomer (@RequestBody List<Customer> customer){
        customerService.updateCustomers(customer);
         return  ResponseEntity.noContent().build();

    }




}
