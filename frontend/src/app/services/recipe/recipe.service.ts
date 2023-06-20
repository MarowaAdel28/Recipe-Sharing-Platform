// @ts-ignore
import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {RecipeModel} from "../../models/recipe-model";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private _apiService:ApiService) { }

  getAll(){
    return this._apiService.getAll("recipe")
  }
  getById(id:number){
    return this._apiService.getById("recipe",id)
  }
  post(recipeModel:RecipeModel){
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.post("recipe",recipeModel,{headers})
  }
  update(recipeModel:RecipeModel){
    return this._apiService.put("recipe",recipeModel)
  }
  delete(id:number){
    return this._apiService.delete("recipe",id)
  }
}
