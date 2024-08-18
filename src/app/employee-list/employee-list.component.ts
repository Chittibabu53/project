import { Component } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent {

  constructor(private employeeService:EmployeeService){

  }


  rows: any[] = [];
  columns = [
    { name: 'Employee ID', prop: 'employeeId' },
    {name:'Registration Date',prop:'registrationDate'},
    { name: 'EmployeeName', prop: 'employeeName' },
    {name:'JobTitle',prop:'position'},
    {name:'PhoneNumber',prop:'phoneNo'},
    { name: 'Email', prop: 'email' }
  ];

  
  ngOnInit(): void {
    this.getList();

  }

  data:any;


  getList(){
 
    this.employeeService.getEmployees().subscribe(data => {
      this.rows = data;
      console.log(data);
    });
  
    }

}
