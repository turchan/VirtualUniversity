import { Component, OnInit }   from '@angular/core';
import { Course }              from '../../../model/course';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { UserService }         from '../../../services/user.service';
import { Router }              from '@angular/router';
import { CourseService }       from '../../../services/course.service';

@Component({
  selector: 'app-show-professors-courses',
  templateUrl: './show-professors-courses.component.html',
  styles: []
})
export class ShowProfessorsCoursesComponent implements OnInit {

  courses: Course[];
  info: any;

  constructor(private router: Router,
              private service: UserService,
              private courseService: CourseService,
              private token: TokenStorageService)
  {this.courseService.getCourses().subscribe(data => (this.courses = data))}

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    };
  }

  showCourse(course: Course): void
  {
    localStorage.removeItem('courseId');
    localStorage.setItem('courseId', course.course_id.toString());
    this.router.navigate(['show-course']);
  }

  deleteCourse(course: Course): void
  {
    this.courseService.deleteCourse(course.course_id).subscribe(data => {
      this.courses = this.courses.filter( c => c !== course);
    });
  }

}
