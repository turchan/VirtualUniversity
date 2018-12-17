import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs';
import { Material }                from '../model/material';
import { map }                     from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  private baseUrl = "http://localhost:8080/materials"
  private httpHeaders = new HttpHeaders({"Content-Type": "application/json"});

  constructor(private http: HttpClient) {}

  getMaterials(): Observable<Material[]>
  {
    return this.http.get(this.baseUrl).pipe(
      map(data => data as Material[])
    );
  }

  getMaterial(id: number): Observable<Material>
  {
    return this.http.get<Material>(`${this.baseUrl}/{$id}`);
  }

  createMaterial(material: Material): Observable<Material>
  {
    return this.http.post<Material>(this.baseUrl + '/create', material, {headers: this.httpHeaders});
  }

  updateMaterial(material: Material): Observable<Material>
  {
    return this.http.put<Material>(this.baseUrl + `/update`, material, {headers: this.httpHeaders});
  }

  deleteMaterial(id: number): Observable<Material>
  {
    return this.http.delete<Material>(`${this.baseUrl}/${id}`, {headers: this.httpHeaders});
  }
}
