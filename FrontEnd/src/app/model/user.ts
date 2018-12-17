import { Role } from './role';
import { Course } from './course';

export class User {
  user_id: number;
  login: string;
  password: string;
  name: string;
  surname: string;
  country: string;
  email: string;
  city: string;
  role_id: Role;
  coursesList: Array<Course>;
}
