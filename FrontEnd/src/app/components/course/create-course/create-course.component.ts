import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CourseService }                      from '../../../services/course.service';
import { Router }                             from '@angular/router';
import { TokenStorageService }                from '../../../auth/token-storage.service';
import { first }                              from 'rxjs/operators';

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styles: []
})
export class CreateCourseComponent implements OnInit {

  createForm: FormGroup;
  info: any;
  submitted = false;

  constructor(private service: CourseService,
              private router: Router,
              private formBuilder: FormBuilder,
              private token: TokenStorageService) { }

  ngOnInit() {

    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    };

    this.createForm = this.formBuilder.group({
        course_id: [],
        title: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        professor: [this.info.login, Validators.required],
        description: [''],
        materialList: [],
        creator: [this.info.login],
        markList: [],
        userList: []
      }
    );
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

    console.log(this.createForm);

    this.service.createCourse(this.createForm.value)
      .pipe(first())
      .subscribe(data => {
          this.router.navigate(['show-professors-courses'])
        },
        error1 => {
          alert(error1);
        })
  }
}
