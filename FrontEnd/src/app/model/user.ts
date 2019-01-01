import { Role }   from './role';
import { Course } from './course';
import { Mark }   from './mark';

export class User {
  user_id: number;
  login: string;
  password: string;
  name: string;
  surname: string;
  country: string;
  email: string;
  city: string;
  role_id: Array<Role>;
  coursesList: Array<Course>;
  markList: Array<Mark>;
}
