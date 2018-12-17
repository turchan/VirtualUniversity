import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs/internal/Observable';
import { Mark }                    from '../model/mark';
import { map }                     from 'rxjs/operators';

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

  createMark(mark: Mark): Observable<Mark>
  {
    return this.http.post<Mark>(this.baseUrl + "/create", mark, {headers: this.httpHeaders});
  }

  updateMark(mark: Mark): Observable<Mark>
  {
    return this.http.put<Mark>(this.baseUrl + "/update", mark, {headers: this.httpHeaders});
  }

  deleteMark(id: number): Observable<Mark>
  {
    return this.http.delete<Mark>(`${this.baseUrl}/${id}`, {headers: this.httpHeaders});
  }
}
