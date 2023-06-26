import { Injectable } from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import { ApiService } from '../api.service';
import { Subject } from 'rxjs';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _apiService:ApiService, private auth: AuthService,private router: Router) { }

  register(userData:any){
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.post("user/register",JSON.stringify(userData),headers)
  }

  login(userData:any) {
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.post("user/login",JSON.stringify(userData),headers)
  }


  put(userData:any){
    console.log("put method user service")
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.put_header("user/edit",JSON.stringify(userData), headers)
  }

  getUserById(id:number) {
    return this._apiService.getById("user",id);
  }




  private username = new Subject<string>();
  username$ = this.username.asObservable();

  setValue(username: string) {
    this.username.next(username);  }




  private isLogedIn = new Subject<boolean>();
  isLogedIn$ = this.isLogedIn.asObservable();

  setLogged(isLogedIn: boolean) {
    this.isLogedIn.next(isLogedIn);
   }


    logout() {
      this.setValue("");
      this.setLogged(false);
      this.auth.deleteToken();
      this.router.navigateByUrl('home');

    }
}
