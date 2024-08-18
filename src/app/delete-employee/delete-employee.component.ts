import { Component } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-delete-employee',
  templateUrl: './delete-employee.component.html',
  styleUrl: './delete-employee.component.css'
})
export class DeleteEmployeeComponent {


  constructor(private service:EmployeeService){
   

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

  toggleAll(event: any): void {
    const checked = event.target.checked;
    this.rows.forEach(row => row.selected = checked);
  }

  
  deleteSelected(): void {
    const selectedIds = this.rows
      .filter(row => row.selected)
      .map(row => row.employeeId);
      if (selectedIds.length > 0) {
        this.service.deleteEmployees(selectedIds).subscribe(
          () => {
            // Handle successful deletion
            console.log('Deleted successfully');
            this.getList(); // Refresh the list
          },
          error => {
            console.error('Error deleting rows', error);
          }
        );
      } else {
        console.warn('No rows selected for deletion');
      }
  }
   customerData:any;
  getList(): void {
    this.service.getEmployees().subscribe(
      response => {
        this.rows = response.map((item: any) => ({ ...item, selected: false }));
      },
      error => {
        console.error('Error fetching customer data', error);
      }
    );
  }
}


