import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Todo } from '../model/todo';
import { PagedApiResponse } from '../../core/interface/paged-api.response';
import { ApiResponse } from 'src/app/core/interface/api.response';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private resourceUrl = `api/todo`;

  constructor(private http: HttpClient) { }

  validateUsernameNotTaken(username: String, id?: number): Observable<any> {
    return this.http.post(`api/validators/username-not-taken`, { value: username, id: id });
  }

  get(id: number): Observable<ApiResponse<Todo>> {
    return this.http.get<ApiResponse<Todo>>(`${this.resourceUrl}/get/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.resourceUrl}/delete/${id}`);
  }

  getAll(criteria): Observable<PagedApiResponse<Todo>> {
    return this.http.post<PagedApiResponse<Todo>>(`${this.resourceUrl}/get/all`, criteria);
  }

  create(todo: Todo): Observable<any> {
    return this.http.post(`${this.resourceUrl}/create`, todo);
  }

  update(todo: Todo): Observable<any> {
    return this.http.put(`${this.resourceUrl}/update`, todo);
  }

}
