import { Component, OnInit } from '@angular/core';
import { User }              from '../../model/user';
import { Router }            from '@angular/router';
import { UserService }       from '../../services/user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styles: []
})
export class ListUserComponent implements OnInit {
  users: User[];

  constructor(private route: Router, private service: UserService) { }

  ngOnInit() {
    this.service.getUsers().subscribe(data => (this.users = data));
  }
}
