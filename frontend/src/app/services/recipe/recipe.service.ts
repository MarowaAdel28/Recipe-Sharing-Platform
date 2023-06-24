import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {RecipeModel} from "../../models/recipe-model";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private _apiService:ApiService) { }

  getAll(){
    return this._apiService.getAll("recipe/getAll")
  }
  getById(id:number){
    return this._apiService.getById("recipe",id)
  }
  // post(recipeModel:RecipeModel){
  //   return this._apiService.post("recipe",recipeModel)
  // }
  post(recipeModel: any) {
    const url = "recipe";
    const body = JSON.stringify(recipeModel);
    const headers = {
      "Content-Type": "application/json",
    };

    return this._apiService.post(url, body,  headers );
  }
  update(recipeModel:RecipeModel){
    const headers = {
      "Content-Type": "application/json",
    };
    return this._apiService.put("recipe",recipeModel,headers)
  }
  delete(id:number){
    return this._apiService.delete("recipe",id)
  }
    getTop3(){
      return this._apiService.getTop3("recipe")
    }

  getPaginationRecipes(params: { page: string; size: string; }) {
    return this._apiService.getPaginationRecipes("recipe/getRecipesByPageNo", params)
  }
  findRecipesByNameAndCategory(params: {name:string; categoryId: string; page: string; size: string; }) {
   return this._apiService.findRecipesByNameAndCategory("recipe/findRecipesByNameAndCategory", params)
 }
}
