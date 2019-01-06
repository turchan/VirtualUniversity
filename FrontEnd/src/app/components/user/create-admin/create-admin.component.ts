import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService }                        from '../../../auth/auth.service';
import { CreateAdminInfo }                    from '../../../auth/createAdmin-info';

@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styles: []
})
export class CreateAdminComponent implements OnInit {

  createForm: FormGroup;
  form: any = {};
  createAdminInfo: CreateAdminInfo;
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

    this.createAdminInfo = new CreateAdminInfo(this.form.login, this.form.password, this.form.name, this.form.surname, this.form.country, this.form.email, this.form.city);

    this.authService.createUser(this.createAdminInfo).subscribe(
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
