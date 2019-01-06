import { Component, OnInit }           from '@angular/core';
import { FormBuilder, FormGroup }      from '@angular/forms';
import { Course }                      from '../../../model/course';
import { Router }                      from '@angular/router';
import { CourseService }               from '../../../services/course.service';
import { MaterialService }             from '../../../services/material.service';
import { first }                       from 'rxjs/operators';
import { UploadFileService }           from '../../../services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-material',
  templateUrl: './add-material.component.html',
  styleUrls: ['./add-material.component.css']
})
export class AddMaterialComponent implements OnInit {

  addForm: FormGroup;
  currentCourse: Course;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: {percentage: number } = {percentage: 0};

  constructor(private router: Router,
              private courseService: CourseService,
              private materialService: MaterialService,
              private uploadService: UploadFileService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    const courseId = localStorage.getItem('courseId');

    if (!courseId)
    {
      alert("Accept denied");
      this.router.navigate(["show-course"]);
      return;
    }

    this.addForm = this.formBuilder.group({
      material_id: [],
      title: [''],
      description: [''],
      file: [this.currentFileUpload],
      course_id: [this.currentCourse],
    });

    this.courseService.getCourse(+courseId).subscribe(data => {this.currentCourse = data});
  }

  selectFile(event)
  {
    this.selectedFiles = event.target.files;
  }


  onSubmit()
  {
    console.log(this.addForm);

    this.progress.percentage = 0;

    this.materialService.createMaterial(this.addForm.value, this.currentCourse)
      .pipe(first())
      .subscribe( data => {
        this.router.navigate(["show-course"]);
      },
        error1 => alert(error1));
  }

  upload()
  {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress)
      {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      }
      else if (event instanceof  HttpResponse)
      {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }
}
