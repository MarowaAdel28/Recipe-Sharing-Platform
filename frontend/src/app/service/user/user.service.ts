import { Injectable } from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import { ApiService } from '../api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _apiService:ApiService) { }

  post(userData:any){
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.post("user",JSON.stringify(userData),headers)
  }

  put(userData:any){
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.put_header("user",JSON.stringify(userData),headers)
  }

  getUserById(id:number) {
    return this._apiService.getById("user",id);
  }
}