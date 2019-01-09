import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService }                        from '../../../auth/auth.service';
import { CreateProfessorInfo }                from '../../../auth/createProfessor-info';
import { Router }                             from '@angular/router';

@Component({
  selector: 'app-create-professor',
  templateUrl: './create-professor.component.html',
  styles: []
})
export class CreateProfessorComponent implements OnInit {

  createForm: FormGroup;
  form: any = {};
  createProfessorInfo: CreateProfessorInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  submitted = false;

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router) {}

  ngOnInit() {
    this.createForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      country: [],
      email: ['', [Validators.required, Validators.email]],
      city: []
    });
  }

  get f() {
    return this.createForm.controls;
  }

  onSubmit()
  {
    this.submitted = true;

    if (this.createForm.invalid)
    {
      return;
    }

    console.log(this.form);

    this.createProfessorInfo = new CreateProfessorInfo(this.form.login, this.form.password, this.form.name, this.form.surname, this.form.country, this.form.email, this.form.city);

    this.authService.createUser(this.createProfessorInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.router.navigate(["list-user"]);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
