import { Component }                       from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable }                      from 'rxjs';
import { map }                             from 'rxjs/operators';
import { TokenStorageService }             from '../auth/token-storage.service';
import { Router }                          from '@angular/router';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private token: TokenStorageService,
              private router: Router) {}


  logout() {
    this.token.signOut();
  }
}
