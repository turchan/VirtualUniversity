import { Component, OnInit } from '@angular/core';
import { User }              from '../../../model/user';
import { Course }            from '../../../model/course';
import { UserService }       from '../../../services/user.service';
import { Router }            from '@angular/router';
import { CourseService }     from '../../../services/course.service';

@Component({
  selector: 'app-show-users-marks',
  templateUrl: './show-users-marks.component.html',
  styles: []
})
export class ShowUsersMarksComponent implements OnInit {

  currentUser: User;
  currentCourse: Course;

  constructor(private router: Router,
              private userService: UserService,
              private courseService: CourseService) { }

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

}
