import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs/internal/Observable';
import { User }                    from '../model/user';
import { map }                     from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/user";
  private httpHeaders = new HttpHeaders({"Content-Type": "application/json"});

  constructor(private http: HttpClient)
  {
    console.log("User Service")
  }

  getUsers(): Observable<User[]>
  {
    return this.http.get(this.baseUrl).pipe(
      map(data => data as User[])
    );
  }

  getUser(id: number): Observable<User>
  {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  createUser(user: User): Observable<User>
  {
    return this.http.post<User>(this.baseUrl + "/create", user, {headers: this.httpHeaders});
  }

  updateUser(user: User): Observable<User>
  {
    return this.http.put<User>(this.baseUrl + "/update", user, {headers: this.httpHeaders});
  }

  deleteUser(id: number): Observable<User>
  {
    return this.http.delete<User>(`${this.baseUrl}/${id}`, {headers: this.httpHeaders});
  }
}
