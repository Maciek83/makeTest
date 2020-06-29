import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({providedIn:'root'})
export class HttpInterceptorClass  implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      console.log('Intercepted');
      return next.handle(req)
      .pipe(
        retry(3),
        catchError((err : HttpErrorResponse) => {
          let errorMessage = '';
          if (err.error instanceof ErrorEvent) {
            // client-side error
            errorMessage = `Error: ${err.message}`;
          } else {
            // server-side error
            errorMessage = `Error Code: ${err.status}\nMessage: ${err.message}`;
          }
          alert(errorMessage);
          return throwError(errorMessage);
        })
      )
    }
}