import { Component, OnInit }                  from '@angular/core';
import { CreateUserInfo }                     from '../../../auth/createUser-info';
import { AuthService }                        from '../../../auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styles: []
})
export class CreateUserComponent implements OnInit {

  createForm: FormGroup;
  form: any = {};
  createUserInfo: CreateUserInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.createForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      country: [],
      email: ['', Validators.required],
      city: []
    });
  }

  onSubmit() {
    console.log(this.form);

    this.createUserInfo = new CreateUserInfo(this.form.login, this.form.password, this.form.name, this.form.surname, this.form.country, this.form.email, this.form.city);

    this.authService.createUser(this.createUserInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
