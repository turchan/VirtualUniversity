import { Component, OnInit }   from '@angular/core';
import { User }                from '../../model/user';
import { Router }              from '@angular/router';
import { UserService }         from '../../services/user.service';
import { TokenStorageService } from '../../auth/token-storage.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styles: []
})
export class ListUserComponent implements OnInit {
  users: User[];
  info: any;

  constructor(private router: Router,
              private token: TokenStorageService,
              private service: UserService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      authorities: this.token.getAuthorities()
    };

    this.service.getUsers().subscribe(data => (this.users = data));
  }

  deleteUser(user: User): void
  {
    this.service.deleteUser(user.user_id).subscribe(data => {
      this.users = this.users.filter(c => c !== user);
    })
  }

  editUser(user: User): void
  {
    localStorage.removeItem("editUserId");
    localStorage.setItem("editUserId", user.user_id.toString());
    this.router.navigate(['edit-user'])
  }

  addUser(): void
  {
    this.router.navigate([`add-user`]);
  }
}
