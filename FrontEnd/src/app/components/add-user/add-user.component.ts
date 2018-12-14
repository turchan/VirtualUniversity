import { Component, Input, OnInit }           from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { UserService }                        from '../../services/user.service';
import { RoleService }                        from '../../services/role.service';
import { Role }                               from '../../model/role';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styles: []
})
export class AddUserComponent implements OnInit {

  @Input() selectedRole: Role;
  roles = Role;
  keys = [];
  addForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private service: UserService,
              private roleService: RoleService)
  {this.keys = Object.keys(this.roles);}

  ngOnInit() {
    this.roleService.getRoles();

    this.addForm = this.formBuilder.group({
      id: [],
      login: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      country: ['', Validators.required],
      email: ['', Validators.required],
      city: ['', Validators.required]
    });
  }

  onSubmit() {
    this.service.createUser(this.addForm.value)
      .subscribe(data => {
        this.router.navigate([`list-user`]);
      })
  }

  listUser(): void
  {
    this.router.navigate([`list-user`]);
  }

}
