import { Component, OnInit }   from '@angular/core';
import { CourseService }       from '../../../services/course.service';
import { Router }              from '@angular/router';
import { UserService }         from '../../../services/user.service';
import { Course }              from '../../../model/course';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { User }                from '../../../model/user';

@Component({
  selector: 'app-users-for-admin',
  templateUrl: './users-for-admin.component.html',
  styles: []
})
export class UsersForAdminComponent implements OnInit {

  surname: string;
  users: User[];
  currentCourse: Course;
  info: any;

  constructor(private userService: UserService,
              private courseService: CourseService,
              private router: Router,
              private token: TokenStorageService) { }

  ngOnInit() {
    this.surname = null;

    this.info = {
      token: this.token.getToken(),
      authorities: this.token.getAuthorities(),
      login: this.token.getLogin()
    }
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

  onSubmit()
  {
    this.searchUser();
  }

}
