import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-submit',
  templateUrl: './submit.component.html',
  styleUrl: './submit.component.css'
})
export class SubmitComponent {


  title = 'Person Details';
  customerForm: FormGroup;
  isSubmitting = false;


  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
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
