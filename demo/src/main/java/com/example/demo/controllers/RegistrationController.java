package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.Registration;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    @PostMapping
    public Registration createRegistration (@RequestBody Registration registration){
        Employee employee=employeeRepository.findById(registration.getEmployee().getEmployeeId()).orElse(null);
        registration.setEmployee(employee);
        return  registrationService.saveRegistration(registration);
    }

    @GetMapping
    public  Registration getRegistration(@RequestParam int id){
        return  registrationService.getRegistration(id);
    }

    @DeleteMapping
    public String deleteRegistration(@RequestParam int id){
        registrationRepository.deleteById(id);
        return "deleted successfully";
    }

    @PutMapping
    public Registration updateRegistration(@RequestBody Registration registration){
     Registration registration1 = registrationRepository.findById(registration.getRegId()).orElseThrow(()->new RuntimeException("reg not found"));
     Employee employee=employeeRepository.findById(registration.getEmployee().getEmployeeId()).orElse(null);
     registration1.setEmployee(employee);
     registration1.setRegistrationStatus(registration.getRegistrationStatus());
     registrationRepository.save(registration1);
     return registration1;
    }
    
}
