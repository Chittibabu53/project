export interface Customer {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    jobTitle: string;
    comments: string;
    selected?: boolean; // Optional property for selection
  }
  