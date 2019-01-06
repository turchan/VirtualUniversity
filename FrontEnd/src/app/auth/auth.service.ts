import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse }         from './jwt-response';
import { AuthLoginInfo }       from './login-info';
import { CreateUserInfo }      from './createUser-info';
import { CreateAdminInfo }     from './createAdmin-info';
import { CreateProfessorInfo } from './createProfessor-info';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/auth';
  private createUserUrl = 'http://localhost:8080/auth/create';
  private createAdminUrl = 'http://localhost:8080/auth/create';
  private createProfessorUrl = 'http://localhost:8080/auth/create';

  constructor(private http: HttpClient) {}

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  createUser(info: CreateUserInfo): Observable<string> {
    return this.http.post<string>(this.createUserUrl, info, httpOptions);
  }

  createAdmin(info: CreateAdminInfo): Observable<string> {
    return this.http.post<string>(this.createAdminUrl, info, httpOptions);
  }

  createProfessor(info: CreateProfessorInfo): Observable<string> {
    return this.http.post<string>(this.createProfessorUrl, info, httpOptions);
  }
}
