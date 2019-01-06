import { Component, OnInit }   from '@angular/core';
import { Course }              from '../../../model/course';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { UserService }         from '../../../services/user.service';
import { Router }              from '@angular/router';
import { CourseService }       from '../../../services/course.service';
import { User }                from '../../../model/user';

@Component({
  selector: 'app-show-users-courses',
  templateUrl: './show-users-courses.component.html',
  styles: []
})
export class ShowUsersCoursesComponent implements OnInit {

  currentUser: User;
  courses: Course[];
  info: any;

  constructor(private router: Router,
              private service: UserService,
              private courseService: CourseService,
              private token: TokenStorageService)
  { this.courseService.getCourses().subscribe(data => (this.courses = data))}

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    };

    const userShowId = localStorage.getItem("userId");

    if (!userShowId)
    {
      alert("Accept invalid");
      this.router.navigate(['main']);
      return;
    }

    this.service.getUser(+userShowId).subscribe(data => (this.currentUser = data));
  }

  showCourse(course: Course): void
  {
    localStorage.removeItem('courseId');
    localStorage.setItem('courseId', course.course_id.toString());
    this.router.navigate(['show-course'])
  }
}
