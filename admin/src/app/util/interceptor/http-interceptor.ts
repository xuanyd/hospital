import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest,HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { LocalStorage } from '../local.storage'


//const baseurl = 'http://47.103.32.14/server/';
const baseurl = 'http://localhost:8080/';

@Injectable()
export class BaseInterceptor implements HttpInterceptor {
  constructor(private injector: Injector, private ls: LocalStorage,) {}
  intercept(req, next: HttpHandler) {
    let newReq = req.clone({url: req.hadBaseurl ? `${req.url}` : `${baseurl}${req.url}`,});
    /*此处设置额外的头部，token常用于登陆令牌*/
    if(!req.cancelToken) {
      newReq.headers = newReq.headers.set('X-AUTH-TOKEN', this.ls.get('token'))
    }
    return next.handle(newReq).pipe(retry(0),
      catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    if(error.status == 401) {
      window.location.href="#/login";
    }
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }
    return throwError('Something bad happened; please try again later.');
  };
}