import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PagedApiResponse } from '../../core/interface/paged-api.response';
import { ApiResponse } from 'src/app/core/interface/api.response';

import { Users } from '../model/users';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private resourceUrl = `api/user`;


  constructor(private http: HttpClient) { }

  validateUsernameNotTaken(username: String, id?: number): Observable<any> {
    return this.http.post(`api/validators/username-not-taken`, { value: username, id: id });
  }

  getAll(criteria): Observable<PagedApiResponse<Users>> {
    return this.http.post<PagedApiResponse<Users>>(`${this.resourceUrl}/all`, criteria);
  }
}
