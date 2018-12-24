import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { UserService }                        from '../../services/user.service';
import { Role }                               from '../../model/role';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styles: []
})
export class AddUserComponent implements OnInit {
  roles = Role;
  keys = [];
  addForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private service: UserService)
  {this.keys = Object.keys(this.roles);}

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      user_id: [],
      login: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      country: [''],
      email: ['', Validators.required],
      city: [''],
      role_id: [],
      coursesList: []
    });
  }

  onSubmit() {
    this.service.createUser(this.addForm.value)
      .subscribe(data => {
        this.router.navigate([`list-user`]);
      })
  }
}
