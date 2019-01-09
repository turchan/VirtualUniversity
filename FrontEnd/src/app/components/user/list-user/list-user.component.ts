import { Component, OnInit }   from '@angular/core';
import { User }                from '../../../model/user';
import { Router }              from '@angular/router';
import { UserService }         from '../../../services/user.service';
import { TokenStorageService } from '../../../auth/token-storage.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styles: []
})
export class ListUserComponent implements OnInit {
  users: User[];
  info: any;

  constructor(private router: Router,
              private service: UserService,
              private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      authorities: this.token.getAuthorities(),
      login: this.token.getLogin()
    };

    this.service.getUsers().subscribe(data => (this.users = data));
  }

  createUser(): void
  {
    this.router.navigate([`create-user`]);
  }

  createAdmin(): void
  {
    this.router.navigate(["create-admin"])
  }

  createProfessor(): void
  {
    this.router.navigate(["create-professor"])
  }

  showUser(user: User): void
  {
    localStorage.removeItem("userId");
    localStorage.setItem("userId", user.user_id.toString());
    this.router.navigate(['show-user']);
  }

  deleteUsers(user: User): void
  {
    this.service.deleteUser(user.user_id).subscribe(data => {
      this.users = this.users.filter(value => (value !== user));
    })
  }
}
