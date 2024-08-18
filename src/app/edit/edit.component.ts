import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Customer } from '../models/customer.model';
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  rows: Customer[] = [];
  columns = [
    { name: 'ID', prop: 'id' },
    { name: 'FirstName', prop: 'firstName' },
    { name: 'LastName', prop: 'lastName' },
    { name: 'JobTitle', prop: 'jobTitle' },
    { name: 'PhoneNumber', prop: 'phone' },
    { name: 'Comments', prop: 'comments' },
    { name: 'Email', prop: 'email' }
  ];

  isEditing: boolean = false; // Track edit mode

  constructor(private service: CustomerService) {}

  ngOnInit(): void {
    this.getList();
  }
   customerData:any;
  getList(): void {
    this.service.getCustomerData(this.customerData).subscribe(
      (customerInfo: Customer[]) => {
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
        this.service.updateCustomers(updatedCustomers).subscribe(
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
