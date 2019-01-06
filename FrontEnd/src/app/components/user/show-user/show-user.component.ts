import { Component, OnInit }                  from '@angular/core';
import { User }                               from '../../../model/user';
import { UserService }                        from '../../../services/user.service';
import { Router }                             from '@angular/router';
import { TokenStorageService }                from '../../../auth/token-storage.service';
import { Course }                             from '../../../model/course';
import { CourseService }                      from '../../../services/course.service';

@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styles: []
})
export class ShowUserComponent implements OnInit {

  currentUser: User;
  courses: Course[];
  info: any;

  constructor(private router: Router,
              private service: UserService,
              private courseService: CourseService,
              private token: TokenStorageService) {
    this.courseService.getCourses().subscribe(data => (this.courses = data));
  }

  ngOnInit()
  {
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
    console.log(this.currentUser.name);
  }

  editUser(user: User): void
  {
    localStorage.removeItem('editUserId');
    localStorage.setItem('editUserId', user.user_id.toString());
    this.router.navigate(['edit-user'])
  }
}
