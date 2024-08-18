import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { TestComponentComponent } from './test-component/test-component.component';
import { ListComponent } from './list/list.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { SubmitComponent } from './submit/submit.component';
import { DeleteComponent } from './delete/delete.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptor } from './auth-interceptor.service';
import { EditComponent } from './edit/edit.component';
import { SampleComponent } from './sample/sample.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { DeleteEmployeeComponent } from './delete-employee/delete-employee.component';
import { EmployeeEditComponent } from './employee-edit/employee-edit.component';
@NgModule({
  declarations: [
    AppComponent,
    TestComponentComponent,
    ListComponent,
    SubmitComponent,
    DeleteComponent,
    SampleComponent,
    LoginComponent,
    EditComponent,
    EmployeeFormComponent,
    EmployeeListComponent,
    DeleteEmployeeComponent,
    EmployeeEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxDatatableModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 5000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      progressBar: true, 
      closeButton: true 
    }),
    
    
  ],
  providers: [
    provideClientHydration(),
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
