import { Component, OnInit }                  from '@angular/core';
import { User }                               from '../../../model/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { UserService }                        from '../../../services/user.service';
import { first }                              from 'rxjs/operators';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styles: []
})
export class EditUserComponent implements OnInit {

  user: User;
  editForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private service: UserService) { }

  ngOnInit()
  {
    const userId = localStorage.getItem("editUserId");

    if (!userId)
    {
      alert("Accept invalid");
      this.router.navigate(['list-user']);
      return;
    }

    this.editForm = this.formBuilder.group({
      user_id: [],
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      country: [],
      email: ['', [Validators.required, Validators.email]],
      city: [''],
      role_id: [],
      coursesList: []
    });

    this.service.getUser(+userId).subscribe(data => {
      this.editForm.setValue(data);
    });
  }

  get f() {
    return this.editForm.controls;
  }

  onSubmit()
  {
    this.submitted = true;

    if (this.editForm.invalid)
    {
      return;
    }

    this.service.updateUser(this.editForm.value)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(['show-user']);
        },
        error => {
          alert(error);
        });
  }
}
