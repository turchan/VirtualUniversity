import { Component, OnInit }               from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable }                      from 'rxjs';
import { map }                             from 'rxjs/operators';
import { TokenStorageService }             from '../auth/token-storage.service';
import { Router }                          from '@angular/router';
import { User }                            from '../model/user';
import { UserService }                     from '../services/user.service'

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent implements OnInit {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  info: any;
  users: User[];
  currentUrl: string;

  constructor(private breakpointObserver: BreakpointObserver,
              private token: TokenStorageService,
              private router: Router,
              private service: UserService) {}

  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      role: this.token.getAuthorities(),
      login: this.token.getLogin()
    };

    this.service.getUsers().subscribe(data => (this.users = data));
  }

  showUsersCourses(user: User): void
  {
    localStorage.removeItem('userId');
    localStorage.setItem("userId", user.user_id.toString());
    this.router.navigate(['show-users-courses']);
  }

  showUser(user: User): void
  {
    localStorage.removeItem("userId");
    localStorage.setItem("userId", user.user_id.toString());
    this.router.navigate(['show-user']);
  }

  logout() {
    window.location.reload();
    this.token.signOut();
  }
}
