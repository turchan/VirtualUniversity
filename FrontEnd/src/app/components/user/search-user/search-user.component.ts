import { Component, Input, OnInit } from '@angular/core';
import { User }                     from '../../../model/user';
import { UserService }              from '../../../services/user.service';
import { Course }                   from '../../../model/course';
import { Router }                   from '@angular/router';

@Component({
  selector: 'search-user',
  templateUrl: './search-user.component.html',
  styles: []
})
export class SearchUserComponent implements OnInit {

  surname: string;
  users: User[];

  constructor(private userService: UserService,
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

  onSubmit()
  {
      this.searchUser();
  }
}
