import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";

@Injectable({
  providedIn: 'root'
})
export class UserRecipeService {

  constructor(private _apiService:ApiService) { }

  getPaginationUserRecipes(id:number,params: { page: string; size: string; }) {
    return this._apiService.getPaginationUserRecipes("user-recipe",id,params)
  }

  getPaginationUserRecipesArchive(id:number,params: { page: string; size: string; }) {
    return this._apiService.getPaginationUserRecipes("user-recipe/archive",id,params)
  }

  updateRecipeDeletion(id:number,params: { isDeleted:boolean; }) {
    return this._apiService.softDelete("recipe",id,params)
  }

  getRejectedRecipe(id:number) {
    return this._apiService.getById("rejectedRecipe",id);
  }
}
