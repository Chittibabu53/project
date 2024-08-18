import { Component } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee.model';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrl: './employee-edit.component.css'
})
export class EmployeeEditComponent {

  isEditing: boolean = false;

  rows: any[] = [];
  columns = [
    { name: 'Employee ID', prop: 'employeeId' },
    {name:'Registration Date',prop:'registrationDate'},
    { name: 'EmployeeName', prop: 'employeeName' },
    {name:'JobTitle',prop:'position'},
    {name:'PhoneNumber',prop:'phoneNo'},
    { name: 'Email', prop: 'email' }
  ];

  constructor(private service:EmployeeService){

  }

  ngOnInit(): void {
    this.getList();
  }
  getList(): void {
    this.service.getEmployees().subscribe(
      (customerInfo: Employee[]) => {
        this.rows = customerInfo;
      },
      error => {
        console.error('Error fetching customer data', error);
      }
    );
  }

  toggleAll(event: any): void {
    const checked = event.target.checked;
    this.rows.forEach(row => row.selected = checked);
  }

  editSelected(): void {
    this.isEditing = !this.isEditing;

    if (!this.isEditing) {
      const updatedCustomers = this.rows.filter(row => row.selected);

      if (updatedCustomers.length > 0) {
        this.service.updateEmployees(updatedCustomers).subscribe(
          () => {
            console.log('Updated successfully');
            this.getList(); 
          },
          error => {
            console.error('Error updating', error);
          }
        );
      } else {
        console.warn('No rows selected for updating');
      }
    }
  }
}
