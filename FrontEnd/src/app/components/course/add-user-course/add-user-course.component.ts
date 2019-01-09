import { Component, OnInit }   from '@angular/core';
import { User }                from '../../../model/user';
import { Router }              from '@angular/router';
import { UserService }         from '../../../services/user.service';
import { CourseService }       from '../../../services/course.service';
import { Course }              from '../../../model/course';

@Component({
  selector: 'app-add-user-course',
  templateUrl: './add-user-course.component.html',
  styles: []
})
export class AddUserCourseComponent implements OnInit {

  users: User[];
  currentCourse: Course;
  info: any;

  constructor(private router: Router,
              private userService: UserService,
              private courseService: CourseService) {}

  ngOnInit() {
    this.userService.getUsers().subscribe(data => (this.users = data));
  }

  addUser(user: User): void
  {
    const courseId = localStorage.getItem('courseId');

    this.courseService.addUser(+courseId, user.user_id).subscribe(data => (this.currentCourse = data));
  }

  showUser(user: User): void
  {
    localStorage.removeItem("userId");
    localStorage.setItem("userId", user.user_id.toString());
    this.router.navigate(['show-user']);
  }
}
