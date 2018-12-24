import { Component, OnInit }                  from '@angular/core';
import { User }                               from '../../model/user';
import { UserService }                        from '../../services/user.service';
import { Router }                             from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styles: []
})
export class ShowUserComponent implements OnInit {

  currentUser: User;

  constructor(private router: Router,
              private service: UserService) { }

  ngOnInit()
  {
    const userShowId = localStorage.getItem("showUserId");

    if (!userShowId)
    {
      alert("Accept invalid");
      this.router.navigate(['main']);
      return;
    }

    this.service.getUser(+userShowId).subscribe(data => (this.currentUser = data)
    );
  }

  editUser(user: User): void
  {
    localStorage.removeItem('editUserId');
    localStorage.setItem('editUserId', user.user_id.toString());
    this.router.navigate(['edit-user'])
  }
}
