import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-test-component',
  templateUrl: './test-component.component.html',
  styleUrl: './test-component.component.css'
})
export class TestComponentComponent {
  isNotificationShown:boolean= false;


  constructor(private toastr: ToastrService) {}

  showSuccess() {
    if (!this.isNotificationShown) {
      this.isNotificationShown = true;
      this.toastr.success('This is a success message!', 'Success');
      setTimeout(() => this.isNotificationShown = false, 1000); // Reset flag after a second
    }
  }


}
