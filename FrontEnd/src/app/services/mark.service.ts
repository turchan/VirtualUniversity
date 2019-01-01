import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs/internal/Observable';
import { Mark }                    from '../model/mark';
import { map }                     from 'rxjs/operators';
import { User }                    from '../model/user';
import { Course }                  from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  private baseUrl = "http://localhost:8080/marks";
  private httpHeaders = new HttpHeaders({"Content-Type": "application/json"});

  constructor(private http: HttpClient) {}

  getMarks(): Observable<Mark[]>
  {
    return this.http.get(this.baseUrl).pipe(
      map(data => data as Mark[])
    );
  }

  getMark(id: number): Observable<Mark>
  {
    return this.http.get<Mark>(`${this.baseUrl}/${id}`);
  }

  addMark(mark: Mark, userId: User, courseId: Course): Observable<Mark>
  {
    return this.http.post<Mark>(this.baseUrl + `/add/${userId.user_id}/${courseId.course_id}`, mark, {headers: this.httpHeaders});
  }

  updateMark(mark: Mark): Observable<Mark>
  {
    return this.http.put<Mark>(this.baseUrl + "/update", mark, {headers: this.httpHeaders});
  }

  deleteMark(id: number): Observable<Mark>
  {
    return this.http.delete<Mark>(`${this.baseUrl}/delete/${id}`, {headers: this.httpHeaders});
  }
}
