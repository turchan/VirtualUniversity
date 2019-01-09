import { Component, OnInit }   from '@angular/core';
import { Course }              from '../../../model/course';
import { Router }              from '@angular/router';
import { CourseService }       from '../../../services/course.service';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { UserService }         from '../../../services/user.service';
import { User }                from '../../../model/user';

@Component({
  selector: 'app-show-course',
  templateUrl: './show-course.component.html',
  styles: []
})
export class ShowCourseComponent implements OnInit {

  users: User[];
  courses: Course[];
  currentCourse: Course;
  info: any;

  constructor(private router: Router,
              private courseService: CourseService,
              private userService: UserService,
              private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    };

    const courseId = localStorage.getItem('courseId');

    if (!courseId)
    {
      alert("Accept invalid");
      this.router.navigate(["main"]);
      return;
    }

    this.userService.getUsers().subscribe(data => {this.users = data});
    this.courseService.getCourse(+courseId).subscribe(data => (this.currentCourse = data));
  }

  editCourse(course: Course): void
  {
    localStorage.removeItem('editCourseId');
    localStorage.setItem('editCourseId', course.course_id.toString());
    this.router.navigate(["edit-course"]);
  }

  addMaterial(course: Course): void
  {
    localStorage.removeItem('courseId');
    localStorage.setItem('courseId', course.course_id.toString());
    this.router.navigate(["addMaterial-course"])
  }

  showUser(user: User): void
  {
    localStorage.removeItem("userId");
    localStorage.setItem("userId", user.user_id.toString());
  }

  addUser(user: User): void
  {
    const courseId = localStorage.getItem('courseId');

    this.courseService.addUser(+courseId, user.user_id).subscribe(data => (this.currentCourse = data));
  }
}
