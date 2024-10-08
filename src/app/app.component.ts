import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { CustomerService } from './customer.service'; // Adjust the path if necessary
import { ToastrService } from 'ngx-toastr'; // Import ToastrService
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'sampleprojectangular';
  customerForm: FormGroup;
  isSubmitting = false; // Flag to prevent multiple submissions

  currentComponent: string = 'home'; // Default component

  showComponent(componentName: string): void {
    this.currentComponent = componentName;
  }


  // isLoggedIn(): boolean {
  //   return !!localStorage.getItem('jwt');
  // }

  // logout(): void {
  //   localStorage.removeItem('jwt');
  //   this.router.navigate(['/login']);
  // }




  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router:Router,
    private toastr: ToastrService // Inject ToastrService
  ) {
    this.customerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      jobTitle: ['', Validators.required],
      comments: ['']
    });
  }

  getControl(controlName: string): AbstractControl | null {
    return this.customerForm.get(controlName);
  }

  onSubmit(): void {
    if (this.customerForm.invalid) {
      this.toastr.warning('Please fill out the form correctly.', 'Warning');
      return;
    }

    if (this.isSubmitting) {
      return; // Prevent multiple submissions
    }

    this.isSubmitting = true; // Set flag to true while submitting

    const customerData = this.customerForm.value;
    this.customerService.submitCustomerData(customerData).subscribe(
      response => {
        console.log('Form submitted successfully', response);
        this.toastr.success('Form submitted successfully!', 'Success');
        this.customerForm.reset(); // Reset the form after successful submission
        this.isSubmitting = false; // Reset flag after submission
      },
      error => {
        console.error('Error submitting form', error);
        this.toastr.error('Error submitting form', 'Error');
        this.isSubmitting = false; // Reset flag after error
      }
    );
  }
}
