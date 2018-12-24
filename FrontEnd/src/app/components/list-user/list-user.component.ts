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

  constructor(private router: Router,
              private service: UserService) { }

  ngOnInit() {
    this.service.getUsers().subscribe(data => (this.users = data));
  }

  showUser(user: User): void
  {
    localStorage.removeItem("showUserId");
    localStorage.setItem("showUserId", user.user_id.toString());
    this.router.navigate(['show-user'])
  }

  addUser(): void
  {
    this.router.navigate([`add-user`]);
  }

  editUser(user: User): void
  {
    localStorage.removeItem("editUserId");
    localStorage.setItem("editUserId", user.user_id.toString());
    this.router.navigate(['edit-user'])
  }

  deleteUser(user: User): void
  {
    this.service.deleteUser(user.user_id).subscribe(data => {
      this.users = this.users.filter(c => c !== user);
    })
  }
}
