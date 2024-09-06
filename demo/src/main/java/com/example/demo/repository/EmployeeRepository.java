package com.example.demo.repository;


import com.example.demo.models.Employee;
import com.example.demo.projections.EmployeeDepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//    @Query(value = "select * from employee_table as emp join department as dept on emp.department_id=dept.department_id",nativeQuery = true)
//    List<EmployeeDepartmentDTO> getByDeptId();


    @Query(value = "select * from employee_table as emp join department as dept on emp.department_id=dept.department_id",nativeQuery = true)
    List<EmployeeDepartmentDTO> getByDeptId();

    @Query(value = "select email , phone_no from employee_table as emp join department as dept on emp.department_id=dept.department_id",nativeQuery = true)
    List<EmployeeDepartmentDTO> getSampleDetails();

    //finding departments having more than three employees

   @Query(value = "SELECT d.name, e.employee_name\n" +
           "FROM department d\n" +
           "JOIN employee_table e ON d.department_id = e.department_id\n" +
           "WHERE d.department_id IN (\n" +
           "    SELECT department_id\n" +
           "    FROM employee_table\n" +
           "    GROUP BY department_id\n" +
           "    HAVING COUNT(employee_id) > 3\n" +
           ")\n" +
           "ORDER BY d.name, e.employee_name",nativeQuery = true)
    List<EmployeeDepartmentDTO> getEmployeesWithThreeDept();

   //Finding the average budget of projects for each department and list the departments with more than one project
    @Query(value = """
            SELECT d.name , AVG(p.budget) as average
            FROM employee_table e
            JOIN department d ON e.department_id = d.department_id
            JOIN projects p ON e.project_id = p.projectid
            GROUP BY d.name
            HAVING COUNT(p.projectid) > 1""",nativeQuery = true)
    List<EmployeeDepartmentDTO> getProjects();

    //employees not assigned to any project
    @Query(value = """
            
            SELECT\s
                e.employee_name,
                e.position,
                d.name AS department_name
            FROM\s
                employee_table e
            JOIN\s
                department d ON e.department_id = d.department_id
            WHERE\s
                e.project_id IS NULL;
            """,nativeQuery = true)
    List<EmployeeDepartmentDTO> employeeNoProjects();


    //project name and employee count
    @Query(value = """
             SELECT\s
                p.project_name,
                COUNT(e.employee_id) AS employee_count
            FROM\s
                projects p
            LEFT JOIN\s
                employee_table e ON p.projectid = e.project_id
            GROUP BY\s
                p.project_name;
            """,nativeQuery = true)
    List<EmployeeDepartmentDTO> getCountOfProjects();



    @Query(value = """
            
            SELECT\s
                d.name AS department_name,
                p.project_name,
                p.budget
            FROM\s
                department d
            JOIN\s
                employee_table e ON d.department_id = e.department_id
            JOIN\s
                projects p ON e.project_id = p.projectid
            WHERE\s
                p.budget = (
                    SELECT MAX(p2.budget)
                    FROM projects p2
                    JOIN employee_table e2 ON p2.projectid = e2.project_id
                    WHERE e2.department_id = d.department_id
                );
            """,nativeQuery = true)
    List<EmployeeDepartmentDTO> getExpensiveProject();





}





