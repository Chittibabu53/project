package com.example.demo.services;


import com.example.demo.models.Customer;
import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id){
        return  employeeRepository.findById(id).orElse(null);
    }

    public void deleteEmployeeById(int id){
        employeeRepository.deleteById(id);
    }

    public void updateEmployeeById(Employee employee){
      int id =employee.getEmployeeId();
      Employee employee1= employeeRepository.findById(id).orElse(null);


        if (employee1 == null) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }

      employee1.setPhoneNo(employee.getPhoneNo());
      employee1.setEmployeeName(employee.getEmployeeName());
      employee1.setPosition(employee.getPosition());
      employee1.setEmail(employee.getEmail());

      employeeRepository.save(employee1);
    }


    public void deleteEmployees(List<Integer> ids) {
        employeeRepository.deleteAllById(ids);
    }

    public void updateEmployees(List<Employee> updatedEmployees) {

        List<Integer> ids=updatedEmployees.stream().map(Employee::getEmployeeId).toList();

        List<Employee>  existingEmployees=employeeRepository.findAllById(ids);

        for(Employee existingCustomer:existingEmployees){
            Optional<Employee> updatedEmployeeOpt=updatedEmployees.stream().filter(c->c.getEmployeeId()==existingCustomer.getEmployeeId()).findFirst();
            if (updatedEmployeeOpt.isPresent()){

                Employee updatedCustomer=updatedEmployeeOpt.get();

                existingCustomer.setEmployeeName(updatedCustomer.getEmployeeName());
                existingCustomer.setPosition(updatedCustomer.getPosition());
                existingCustomer.setEmail(updatedCustomer.getEmail());
                existingCustomer.setPhoneNo(updatedCustomer.getPhoneNo());
             existingCustomer.setRegistrationDate(updatedCustomer.getRegistrationDate());

            }

        }
        employeeRepository.saveAll(existingEmployees);

    }
}
