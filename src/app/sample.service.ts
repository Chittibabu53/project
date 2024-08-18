import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SampleService {
  private apiUrl = 'http://localhost:8080/api/orders'; 

  constructor(private http:HttpClient) {

   }

   submitOrderData(OrderData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, OrderData);
  }


  getOrderData(OrderData:any):Observable<any>{
    return this.http.get<any>(this.apiUrl,OrderData);
  }
}
