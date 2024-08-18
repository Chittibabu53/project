import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AuthInterceptor {

  constructor(private http:HttpClient){

  }
  private apiUrl = 'http://localhost:8080/login';

  login(username: string, password: string): Observable<string> {
    return this.http.post<string>(this.apiUrl, { username, password }, { responseType: 'text' as 'json' });
  }
}
