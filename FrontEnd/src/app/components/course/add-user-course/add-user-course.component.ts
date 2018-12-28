import { Component, OnInit }   from '@angular/core';
import { User }                from '../../../model/user';
import { Router }              from '@angular/router';
import { UserService }         from '../../../services/user.service';
import { CourseService }       from '../../../services/course.service';
import { Course }              from '../../../model/course';
import { TokenStorageService } from '../../../auth/token-storage.service';

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
              private courseService: CourseService,
              private token: TokenStorageService) {}

  ngOnInit() {

    this.info = {
      token: this.token.getToken(),
      authorities: this.token.getAuthorities()
    };

    this.userService.getUsers().subscribe(data => (this.users = data));
  }

  addUser(user: User): void
  {
    const courseId = localStorage.getItem('courseId');

    this.courseService.addUser(+courseId, user.user_id).subscribe(data => (this.currentCourse = data));
  }
}
