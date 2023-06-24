import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {RecipeModel} from "../../models/recipe-model";

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
}
