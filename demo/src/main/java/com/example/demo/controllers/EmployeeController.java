package com.example.demo.controllers;



import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee (@RequestBody Employee employee){
        Employee createEmployee=employeeService.saveEmployee(employee);
        return new  ResponseEntity<>(createEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>>  getAllEmployees(){
        List<Employee> getEmployee=employeeService.getEmployee();
        return  new ResponseEntity<>(getEmployee,HttpStatus.OK);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteEmployees(@RequestBody List<Integer> ids){
        employeeService.deleteEmployees(ids);
        return   ResponseEntity.noContent().build();
    }


    @PutMapping("/update")
    public ResponseEntity<List<Employee>> updateEmployees (@RequestBody List<Employee> employees){
        employeeService.updateEmployees(employees);
        return  ResponseEntity.noContent().build();
    }


    @GetMapping("/getEmployee")
    public Employee getEmployee(@RequestParam int id){
        Employee employee=employeeService.getEmployeeById(id);
        return employee;
    }

    @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam int id){
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployeeById(employee);
    }



}
