export interface Employee {
    employeeId?: number; // Optional because it will be generated
    registrationDate?: string; // ISO 8601 format
    employeeName: string;
    email: string;
    position: string;
    phoneNo: string;
  }
  