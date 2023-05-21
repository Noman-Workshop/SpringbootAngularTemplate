import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { StateStorageService } from './state-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private authService: AuthService;

  constructor(private injector: Injector,
    private stateStorageService: StateStorageService,
    private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let url = req.url;
    let authReq = req.clone();

    if (!url.startsWith('https://') && !url.startsWith('http://') && !url.startsWith('assets/')) {
      url = environment.SERVER_URL + url;

      this.authService = this.injector.get(AuthService);
      const token = this.authService.getToken();

      if (!!token) {
        authReq = req.clone({ url: url, setHeaders: { Authorization: 'Bearer ' + token } });
      } else {
        authReq = req.clone({ url: url });
      }
    }

    return next.handle(authReq).pipe(
      catchError(error => {
        if (error.status === 401 || error.status === 403) {
          return this.authService.refreshTokens().pipe(
            switchMap(response => {
              this.authService.storeAuthenticationToken(response.body['data'], false);
              const newRequest = req.clone({
                headers: req.headers.set('Authorization', 'Bearer ' + response.body['data'])
              });
              return next.handle(newRequest);
            }),
            catchError(error => {
              this.authService.logout();
              return throwError(error);
            })
          );
        }
        return throwError(error);
      })
    );

  }
}
