import { Component, OnInit } from '@angular/core';
import { User }              from '../../../model/user';
import { Course }            from '../../../model/course';
import { Router }            from '@angular/router';
import { UserService }       from '../../../services/user.service';
import { CourseService }     from '../../../services/course.service';
import { Mark }              from '../../../model/mark';
import { MarkService }       from '../../../services/mark.service';

@Component({
  selector: 'app-show-marks-course',
  templateUrl: './show-marks-course.component.html',
  styles: []
})
export class ShowMarksCourseComponent implements OnInit {

  currentUser: User;
  currentCourse: Course;

  constructor(private router: Router,
              private userService: UserService,
              private courseService: CourseService,
              private markService: MarkService) { }

  ngOnInit() {
    const userId = localStorage.getItem('userId');
    const courseId = localStorage.getItem('courseId');

    if (!courseId || !userId) {
      alert("Accept denied");
      this.router.navigate(['show-course']);
      return;
    }

    this.userService.getUser(+userId).subscribe(data => {
      this.currentUser = data
    });
    this.courseService.getCourse(+courseId).subscribe(data => {
      this.currentCourse = data
    });
  }

  addMark(user: User, course: Course): void
  {
    localStorage.removeItem('courseId');
    localStorage.removeItem('userId');
    localStorage.setItem('courseId', course.course_id.toString());
    localStorage.setItem('userId', user.user_id.toString());
    this.router.navigate(["addMark-course"]);
  }

  deleteMark(mark: Mark): void
  {
    this.markService.deleteMark(mark.mark_id).subscribe(data => {
      this.currentUser.markList = this.currentUser.markList.filter(value => (value !== mark));
    })
  }
}
