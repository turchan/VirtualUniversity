import { User } from './user';
import { Course } from './course';

export class Mark {
  mark_id: number;
  title: string;
  mark: number;
  user: User;
  course: Course;
}
