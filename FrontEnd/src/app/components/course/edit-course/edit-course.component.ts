import { Component, OnInit }                  from '@angular/core';
import { Course }                             from '../../../model/course';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { CourseService }                      from '../../../services/course.service';
import { first }                              from 'rxjs/operators';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styles: []
})
export class EditCourseComponent implements OnInit {

  course: Course;
  editForm: FormGroup;

  constructor(private router: Router,
              private formBuilder: FormBuilder,
              private service: CourseService) { }

  ngOnInit() {
    const courseId = localStorage.getItem('editCourseId');

    if (!courseId)
    {
      alert("Accept invalid");
      this.router.navigate(["main"]);
      return;
    }

    this.editForm = this.formBuilder.group( {
      course_id: [],
      title: ['', Validators.required],
      password: ['', Validators.required],
      professor: ['', Validators.required],
      description: [''],
      creator: [],
      materialList: [],
      markList: []
    });

    this.service.getCourse(+courseId).subscribe(data => (this.editForm.setValue(data)));
  }

  onSubmit()
  {
    this.service.updateCourse(this.editForm.value)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(['list-course']);
      },
        error1 => {
        alert(error1)
      });
  }

}
