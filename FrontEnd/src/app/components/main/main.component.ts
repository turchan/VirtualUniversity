import { Component, OnInit }   from '@angular/core';
import { TokenStorageService } from '../../auth/token-storage.service';
import { Router }              from '@angular/router';

@Component({
  selector: "app-main",
  templateUrl: "./main.component.html",
  styles: []
})
export class MainComponent implements OnInit {
  info: any;

  constructor(private token: TokenStorageService,
              private router: Router) {}

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    };
  }

  logout() {
    this.token.signOut();
    this.router.navigate([`app-login`])
  }
}
