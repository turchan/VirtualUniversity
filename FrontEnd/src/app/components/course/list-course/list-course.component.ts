import { Component, OnInit }   from '@angular/core';
import { Course }              from '../../../model/course';
import { Router }              from '@angular/router';
import { CourseService }       from '../../../services/course.service';

@Component({
  selector: 'app-list-course',
  templateUrl: './list-course.component.html',
  styles: []
})
export class ListCourseComponent implements OnInit {

  courses: Course[];

  constructor(private router: Router,
              private service: CourseService) { }

  ngOnInit() {
    this.service.getCourses().subscribe(data => (this.courses = data))
  }

  showCourse(course: Course): void
  {
    localStorage.removeItem('showCourseId');
    localStorage.setItem('showCourseId', course.course_id.toString());
    this.router.navigate(["show-course"]);
  }


}
