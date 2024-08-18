import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  rows: any[] = [];
  columns = [
    { name: 'ID', prop: 'id' },
    { name: 'FirstName', prop: 'firstName' },
    { name: 'LastName', prop: 'lastName' },
    { name: 'JobTitle', prop: 'jobTitle' },
    { name: 'PhoneNumber', prop: 'phone' },
    { name: 'Comments', prop: 'comments' },
    { name: 'Email', prop: 'email' }
  ];

  constructor(private service: CustomerService) { }

  ngOnInit(): void {
    this.getList();
  }

  toggleAll(event: any): void {
    const checked = event.target.checked;
    this.rows.forEach(row => row.selected = checked);
  }
  disableButton=true;


  updateDisabledState(condition: boolean) {
    this.disableButton = condition;
  }


  deleteSelected(): void {
    const selectedIds = this.rows
      .filter(row => row.selected)
      .map(row => row.id);
      if (selectedIds.length > 0) {
        this.service.deleteCustomers(selectedIds).subscribe(
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
    this.service.getCustomerData(this.customerData).subscribe(
      response => {
        this.rows = response.map((item: any) => ({ ...item, selected: false })).reverse();
      },
      error => {
        console.error('Error fetching customer data', error);
      }
    );
  }
}
