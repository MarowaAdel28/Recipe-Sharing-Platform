import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable,Injector} from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private inject:Injector) { }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(!req.url.includes('user/login') && !req.url.includes('user/register')){
    let authservice=this.inject.get(AuthService);
    const modifiedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authservice.getToken()}`,
        "Content-Type": "Application/json"
      }
    });
    return next.handle(modifiedRequest);
  }
  return next.handle(req)
}
}

   

  