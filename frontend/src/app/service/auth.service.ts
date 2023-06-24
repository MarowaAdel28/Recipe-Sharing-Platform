import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Subject, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  tokenResp: any;
  private _updatedMenu = new Subject<void>();
  get updatedMenu() {
    return this._updatedMenu;
  }

  proceedLogin(usercred: any) {
    return this.http.post("http://127.0.0.1:8000/Home/login/", usercred)
  }
  proceedLoginCatchError(usercred: any) {
    return this.http.post("http://127.0.0.1:8000/Home/login/", usercred)
      .pipe(
        catchError((err) => {
          console.log('error caught in service');
          console.error(err);
          return throwError(err);
        })
      )
  }
  isLoggedIn() {
    return localStorage.getItem('token') != null;
  }
  getToken() {
    return localStorage.getItem('token') || '';
  }
  /* haveAccess() {
     var logginToken = localStorage.getItem('token') || '';
     var _exractedToken = logginToken.split('.')[1];
     var _atobdata = atob(_exractedToken);
     var _finaldata = JSON.parse(_atobdata);
     if (_finaldata.role == 'admin') {
       return true;
     }
     alert('Yo do not have access');
     return false;
   }*/
  GetRoleByToken(token: any) {
    let _token = token.split('.')[1];
    this.tokenResp = JSON.parse(atob(_token))
    return (this.tokenResp.role);
  }
  GetIDByToken(token: any) {
    let _token = token.split('.')[1];
    this.tokenResp = JSON.parse(atob(_token))
    return (this.tokenResp.id);
  }
}
