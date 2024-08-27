package com.example.demo.services;


import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;
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
        if(employeeRepository.findAll().isEmpty()){
            throw new RuntimeException("employees not found");
        }
        else{
            return employeeRepository.findAll();
        }

    }

    public Employee getEmployeeById(int id){
        return  employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public void deleteEmployeeById(int id){
        Employee employee1= employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.deleteById(id);
    }

    public void updateEmployeeById(Employee employee){
      int id =employee.getEmployeeId();
      Employee employee1= employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);

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
        if(employeeRepository.findAll().isEmpty()){
            throw  new RuntimeException("employees not found");
        }
        else{
            List<Integer> ids=updatedEmployees.stream().map(Employee::getEmployeeId).toList();

            List<Employee>  existingEmployees=employeeRepository.findAllById(ids);

            for(Employee existingEmployee:existingEmployees){
                Optional<Employee> updatedEmployeeOpt=updatedEmployees.stream().filter(c->c.getEmployeeId()==existingEmployee.getEmployeeId()).findFirst();
                if (updatedEmployeeOpt.isPresent()){

                    Employee updatedCustomer=updatedEmployeeOpt.get();

                    existingEmployee.setEmployeeName(updatedCustomer.getEmployeeName());
                    existingEmployee.setPosition(updatedCustomer.getPosition());
                    existingEmployee.setEmail(updatedCustomer.getEmail());
                    existingEmployee.setPhoneNo(updatedCustomer.getPhoneNo());
                    existingEmployee.setMimeType(updatedCustomer.getMimeType());
                    existingEmployee.setContent(updatedCustomer.getContent());

                    existingEmployee.setRegistrationDate(updatedCustomer.getRegistrationDate());

                }

            }
            employeeRepository.saveAll(existingEmployees);

        }
        }


}
