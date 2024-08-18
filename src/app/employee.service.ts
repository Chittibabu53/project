import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee.model'; // Create this model
import { Customer } from './models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/employees'; // Adjust the URL as needed

  constructor(private http: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.apiUrl, employee);
  }

  deleteEmployees(ids: number[]): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/batch`, { body: ids });
  }

  updateEmployees(employees: Employee[]): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update`, employees);
  }
}
