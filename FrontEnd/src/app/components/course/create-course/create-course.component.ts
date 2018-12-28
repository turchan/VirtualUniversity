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
        password: ['', Validators.required],
        professor: [this.info.login],
        description: [''],
        materialList: [],
        creator: [this.info.login],
        markList: [],
        userList: []
      }
    );
  }

  onSubmit()
  {
    console.log(this.createForm);

    this.service.createCourse(this.createForm.value)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(['show-user'])
      },
        error1 => {
        alert(error1);
        })
  }
}
