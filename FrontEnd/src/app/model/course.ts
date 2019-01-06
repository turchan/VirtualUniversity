import { Material } from './material';
import { Mark } from './mark';
import { User } from './user';


export class Course {
  course_id: number;
  title: string;
  password: string;
  professor: string;
  description: string;
  materialList: Array<Material>;
  creator: string;
  markList: Array<Mark>;
  userList: Array<User>;
}
