// login.component.ts
import { Component } from '@angular/core';
import { AuthInterceptor } from '../auth-interceptor.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthInterceptor) { }

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe(
      token => {
        localStorage.setItem('jwt', token);
        // Navigate to a different page or show success
      },
      error => {
        console.error('Login failed', error);
      }
    );
  }
}
