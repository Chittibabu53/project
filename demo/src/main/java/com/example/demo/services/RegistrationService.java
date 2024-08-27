package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.Registration;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;


    public Registration saveRegistration(Registration registration){
        return registrationRepository.save(registration);
    }

    public  Registration getRegistration(int id){
        return  registrationRepository.findById(id).orElse(null);
    }


}
