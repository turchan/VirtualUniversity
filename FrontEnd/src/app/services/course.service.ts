import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs';
import { Course }                  from '../model/course';
import { map }                     from 'rxjs/operators';

@Injectable ({
  providedIn: 'root'
})
export class CourseService {

  private baseUrl = "http://localhost:8080/course";
  private httpHeaders = new HttpHeaders({"Content-Type": "application/json"});

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]>
  {
    return this.http.get(this.baseUrl).pipe(
      map(data => data as Course[])
    );
  }

  getCourse(id: number): Observable<Course>
  {
    return this.http.get<Course>(`${this.baseUrl}/${id}`);
  }

  createCourse(course: Course): Observable<Course>
  {
    return this.http.post<Course>(this.baseUrl + `/create`, course, {headers: this.httpHeaders});
  }

  updateCourse(course: Course): Observable<Course>
  {
    return this.http.put<Course>(this.baseUrl + "/update", course, {headers: this.httpHeaders});
  }

  deleteCourse(id: number): Observable<Course>
  {
    return this.http.delete<Course>(`${this.baseUrl}/${id}`, {headers: this.httpHeaders});
  }
}
