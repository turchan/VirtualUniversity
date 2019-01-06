import { Component, OnInit } from '@angular/core';
import { CourseService }     from '../../../services/course.service';
import { Course }            from '../../../model/course';
import { Router }            from '@angular/router';

@Component({
  selector: 'app-search-course',
  templateUrl: './search-course.component.html',
  styles: []
})
export class SearchCourseComponent implements OnInit {

  title: string;
  courses: Course[];

  constructor(private courseService: CourseService,
              private router: Router) { }

  ngOnInit() {
    this.title = null;
  }

  private searchCourse()
  {
    this.courseService.searchCourse(this.title)
      .subscribe(courses => (this.courses = courses));
  }

  showCourse(course: Course)
  {
    localStorage.removeItem('courseId');
    localStorage.setItem('courseId', course.course_id.toString());
    this.router.navigate(["show-course"])
  }

  onSubmit()
  {
    this.searchCourse();
  }
}
