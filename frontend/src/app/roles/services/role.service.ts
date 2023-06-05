import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Roles } from '../model/roles';
import { PagedApiResponse } from '../../core/interface/paged-api.response';
import { ApiResponse } from 'src/app/core/interface/api.response';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private resourceUrl = `api/role`;

  constructor(private http: HttpClient) { }

  // validateUsernameNotTaken(username: String, id?: number): Observable<any> {
  //   return this.http.post(`api/validators/username-not-taken`, { value: username, id: id });
  // }

  get(id: number): Observable<ApiResponse<Roles>> {
    return this.http.get<ApiResponse<Roles>>(`${this.resourceUrl}/get/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.resourceUrl}/delete/${id}`);
  }

  getAll(criteria): Observable<PagedApiResponse<Roles>> {
    return this.http.post<PagedApiResponse<Roles>>(`${this.resourceUrl}/all`, criteria);
  }

  create(roles: Roles): Observable<any> {
    return this.http.post(`${this.resourceUrl}/create`, roles);
  }

  update(roles: Roles): Observable<any> {
    return this.http.put(`${this.resourceUrl}/update`, roles);
  }

}
