import { Component, OnInit }                  from '@angular/core';
import { Course }                             from '../../../model/course';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { CourseService }                      from '../../../services/course.service';
import { MarkService }                        from '../../../services/mark.service';
import { first }                              from 'rxjs/operators';
import { User }                               from '../../../model/user';
import { UserService }                        from '../../../services/user.service';

@Component({
  selector: 'app-add-marks-course',
  templateUrl: './add-marks-course.component.html',
  styles: []
})
export class AddMarksCourseComponent implements OnInit {

  addForm: FormGroup;
  currentCourse: Course;
  currentUser: User;
  marks: ['2', '2.5', '3', '3.5', '4', '4.5', '5'];
  submitted = false;

  constructor(private courseService: CourseService,
              private userService: UserService,
              private markService: MarkService,
              private formBuilder: FormBuilder,
              private router: Router) {}

  ngOnInit() {

    const courseId = localStorage.getItem('courseId');
    const userId = localStorage.getItem('userId');

    if (!courseId || !userId)
    {
      alert("Accept denied");
      this.router.navigate(["showMark-course"]);
      return;
    }

    this.addForm = this.formBuilder.group({
      mark_id: [],
      title: ['', Validators.required],
      mark: ['', [Validators.required, Validators.min(2), Validators.max(5)]],
      user_id: [this.currentUser],
      course_id: [this.currentCourse]
    });

    this.userService.getUser(+userId).subscribe(data => (this.currentUser = data));
    this.courseService.getCourse(+courseId).subscribe(data => (this.currentCourse = data));
  }

  get f() {
    return this.addForm.controls;
  }

  onSubmit()
  {
    this.submitted = true;

    if (this.addForm.invalid)
    {
      return;
    }

    console.log(this.addForm);

    this.markService.addMark(this.addForm.value, this.currentUser, this.currentCourse)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(["showMark-course"])
      },
        error1 => console.log(error1));
  }
}
