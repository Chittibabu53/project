import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { SampleService } from '../sample.service';
import { response } from 'express';
import { error } from 'console';

@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrl: './sample.component.css'
})
export class SampleComponent {


  orderForm: FormGroup;
  isSubmitting = false;

  constructor(private fb: FormBuilder,private toastr:ToastrService,private sampleService:SampleService) {
    this.orderForm = this.fb.group({
      customerName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      product: ['', Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
      specialInstructions: ['']
    });
  }

  onSubmit() {
    if (this.orderForm.invalid) {
      this.toastr.warning('Please fill out the form correctly.', 'Warning');
      return;
    }

    if (this.isSubmitting) {
      return; // Prevent multiple submissions
    }

    this.isSubmitting = true; 

    const orderData=this.orderForm.value;
    this.sampleService.submitOrderData(orderData).subscribe(response =>
    {
      this.toastr.success('Order Details Submitted Successfully','Success');
      this.orderForm.reset(); // Reset the form after successful submission
      this.isSubmitting = false;

    },
    error=>
    { 
      console.error('Error submitting form', error);
      this.orderForm.reset(); // Reset the form after successful submission
        this.isSubmitting = false; 
    }

    )

}
}
