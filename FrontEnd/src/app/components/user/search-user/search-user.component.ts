import { Component, Input, OnInit } from '@angular/core';
import { User }                     from '../../../model/user';
import { UserService }              from '../../../services/user.service'
import { Router }                   from '@angular/router';
import { Course }                   from '../../../model/course';
import { CourseService }            from '../../../services/course.service';

@Component({
  selector: 'search-user',
  templateUrl: './search-user.component.html',
  styles: []
})
export class SearchUserComponent implements OnInit {

  surname: string;
  users: User[];
  currentCourse: Course;
  info: any;

  constructor(private userService: UserService,
              private courseService: CourseService,
              private router: Router) { }

  ngOnInit() {
    this.surname = null;
  }

  private searchUser()
  {
    this.userService.searchUser(this.surname)
      .subscribe(users => (this.users = users));
  }

  showUser(user: User)
  {
    localStorage.removeItem('userId');
    localStorage.setItem('userId', user.user_id.toString());
    this.router.navigate(["show-user"])
  }

  addUser(user: User): void
  {
    const courseId = localStorage.getItem('courseId');

    this.courseService.addUser(+courseId, user.user_id).subscribe(data => (this.currentCourse = data));
  }

  onSubmit()
  {
      this.searchUser();
  }
}
