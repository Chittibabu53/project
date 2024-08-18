import { Component } from '@angular/core';
import { CustomerService } from '../customer.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { response } from 'express';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {


  

  constructor(private service:CustomerService, private fb: FormBuilder,){
   
    
  }
  rows: any[] = [];
  columns = [
    { name: 'ID', prop: 'id' },
    { name: 'FirstName', prop: 'firstName' },
    {name:'LastName',prop:'lastName'},
    {name:'JobTitle',prop:'jobTitle'},
    {name:'PhoneNumber',prop:'phone'},
    {name:'Comments',prop:'comments'},
    { name: 'Email', prop: 'email' }
  ];


  ngOnInit(): void {
    this.getList();

  }
  data:any;

  getList(){
 
  this.service.getCustomerData(this.data).subscribe(data => {
    this.rows = data.reverse();
    console.log(data);
  });

  }
   


}
