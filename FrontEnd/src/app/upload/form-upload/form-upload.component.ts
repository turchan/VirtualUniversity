import { Component, OnInit }           from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFileService }           from '../../services/upload-file.service';
import { Course }                      from '../../model/course';
import { CourseService }               from '../../services/course.service';

@Component({
  selector: 'form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./form-upload.component.css']
})
export class FormUploadComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  currentCourse: Course;
  progress: {percentage: number } = {percentage: 0};

  constructor(private uploadService: UploadFileService,
              private courseService: CourseService) { }

  ngOnInit() {
    const courseId = localStorage.getItem('courseId');

    this.courseService.getCourse(+courseId).subscribe(data => {this.currentCourse = data});
  }

  selectFile(event)
  {
    this.selectedFiles = event.target.files;
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
