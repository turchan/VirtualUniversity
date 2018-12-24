import { Component, OnInit }                  from '@angular/core';
import { AuthLoginInfo }                      from '../../auth/login-info';
import { AuthService }                        from '../../auth/auth.service';
import { TokenStorageService }                from '../../auth/token-storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: []
})
export class LoginComponent implements OnInit
{
  authForm: FormGroup;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private logininfo: AuthLoginInfo;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit() {

    this.authForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
    });

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log(this.form);

    this.logininfo = new AuthLoginInfo(
      this.form.login,
      this.form.password);

    this.authService.attemptAuth(this.logininfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveLogin(data.login);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        window.location.reload();
        this.router.navigate([`main`]);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.errorMessage;
        this.isLoginFailed = true;
      }
    );
  }
}
