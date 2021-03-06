import { Component, OnInit }   from '@angular/core';
import { Course }              from '../../../model/course';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { UserService }         from '../../../services/user.service';
import { Router }              from '@angular/router';
import { CourseService }       from '../../../services/course.service';
import { User }                from '../../../model/user';

@Component({
  selector: 'app-show-participants-course',
  templateUrl: './show-participants-course.component.html',
  styles: []
})
export class ShowParticipantsCourseComponent implements OnInit {

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

    this.courseService.getCourse(+courseId).subscribe(data => (this.currentCourse = data));
  }

  addUser(course: Course): void
  {
    localStorage.removeItem('courseId');
    localStorage.setItem('courseId', course.course_id.toString());
    this.router.navigate(['addUser-course']);
  }

  showMarks(course: Course, user: User): void
  {
    localStorage.removeItem('courseId');
    localStorage.removeItem('userId');
    localStorage.setItem('courseId', course.course_id.toString());
    localStorage.setItem('userId', user.user_id.toString());
    this.router.navigate(["showMark-course"]);
  }


  deleteUser(user: User): void
  {
    this.courseService.deleteUser(this.currentCourse.course_id, user.user_id).subscribe(data => {
      this.currentCourse.userList = this.currentCourse.userList.filter(value => value !== user);
    })
  }

  showUser(user: User): void
  {
    localStorage.removeItem("userId");
    localStorage.setItem("userId", user.user_id.toString());
    this.router.navigate(['show-user']);
  }
}
