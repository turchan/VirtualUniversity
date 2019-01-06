import { Injectable }                         from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable }                         from 'rxjs';
import { Course }                             from '../model/course';
import { Material }                           from '../model/material';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>>
  {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `/materials/post`, formData, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
