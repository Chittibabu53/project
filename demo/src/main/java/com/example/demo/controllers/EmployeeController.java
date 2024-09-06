package com.example.demo.controllers;

import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.InvalidFileException;
import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.projections.EmployeeDepartmentDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestPart("employee") Employee employee,
                                                   @RequestPart("image") MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            try {
                employee.setContent(file.getBytes());
                employee.setMimeType(file.getContentType());
            } catch (IOException e) {
                throw new InvalidFileException("Failed to process file");
            }
        }

        Employee createdEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> getEmployee = employeeService.getEmployee();
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteEmployees(@RequestBody List<Integer> ids){
        employeeService.deleteEmployees(ids);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateEmployees(@RequestBody List<Employee> employees){
        employeeService.updateEmployees(employees);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Employee> getEmployee(@RequestParam int id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<Void> deleteEmployee(@RequestParam int id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployeeById(employee);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/setDepartment")
    public ResponseEntity<Employee> setDepartment(@RequestParam int eid,@RequestParam int did){
       Department department=departmentService.getDepartmentById(did);
       Employee employee=employeeService.getEmployeeById(eid);
       employee.setDepartment(department);
       employeeService.saveEmployee(employee);
       return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    @GetMapping("/getDepartmentName")
    public List<EmployeeDepartmentDTO> getDepartmentName(){
       try{
           return employeeRepository.getByDeptId();
       }
       catch (Exception e){
           throw new RuntimeException("error fetching departments");
       }

    }


    @GetMapping("/getEmployeesWithThreeDept")
    public List<EmployeeDepartmentDTO> getEmployeesWithThreeDept() {
        try {
            return employeeRepository.getEmployeesWithThreeDept();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching employees with three departments.", e);
        }
    }

    @GetMapping("/getExpensiveProject")
    public List<EmployeeDepartmentDTO> getProjects() {
        try{
            return employeeRepository.getExpensiveProject();
        }
        catch (Exception e){
            throw new RuntimeException("An error occurred while fetching expensive projects.",e);
        }
    }

    @GetMapping("/getCountOfProjects")
    public List<EmployeeDepartmentDTO> getCountOfProjects() {
        try{
            return employeeRepository.getCountOfProjects();
        }
        catch (Exception e){
            throw new RuntimeException("An error occurred while fetching count of projects.",e);
        }
    }

}
