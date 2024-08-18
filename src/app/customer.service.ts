import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/customers'; 

  constructor(private http: HttpClient) {}

  
  submitCustomerData(customerData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, customerData);
  }


  getCustomerData(customerData:any):Observable<any>{
    return this.http.get<any>(this.apiUrl,customerData);
  }


  deleteCustomers(ids: number[]): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/batch`, { body: ids });
  }

  

  updateCustomers(customers: Customer[]): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update`, customers);
  }
}
