import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Subject, throwError } from 'rxjs';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  tokenResp: any;
 
isTokenValid() {
  const tokenData:any = jwt_decode(this.getToken());
  const expirationDate = new Date(tokenData.exp * 1000); 
  const currentDate = new Date();

  if (currentDate > expirationDate) {
    return false
  } return true;
}

  deleteToken() {
    localStorage.setItem('token','');
  }

  isLoggedIn() {
    return localStorage.getItem('token') != "";
  }
  getToken() {
    return localStorage.getItem('token') || '';
  }

  GetRoleByToken(token: any) {
    const decodedToken: any = jwt_decode(token);

    return decodedToken.role;
  }
  GetIDByToken(token: any) {
    const decodedToken: any = jwt_decode(token);

    return decodedToken.id;
  }
  GetNameByToken(token: any) {
    const decodedToken: any = jwt_decode(token);

    return decodedToken.name;
  }
  GetEmailByToken(token: any) {
    const decodedToken: any = jwt_decode(token);

    return decodedToken.sub;
  }
  GetGenderByToken(token: any) {
    const decodedToken: any = jwt_decode(token);

    return decodedToken.gender;
  }
}
