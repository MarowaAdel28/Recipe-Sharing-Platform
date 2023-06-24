import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {FavouriteResponseModel} from "../../models/favourite-response-model";


@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  constructor(private _apiService: ApiService) {
  }

  post(favoruiteRecipe: any) {
    const url = "favoriteRecipe";
    const body = JSON.stringify(favoruiteRecipe);
    const headers = {
      "Content-Type": "application/json",
    };
    return this._apiService.post(url, body,  headers );
  }

  findByRecipeAndUserIds(favouriteModel:FavouriteResponseModel){
    return this._apiService.getByTwoIds('favoriteRecipe/getByIds',favouriteModel)
  }

  delete(favouriteModel:FavouriteResponseModel){
    return this._apiService.deleteFromFavourite(`favoriteRecipe/deletefav`,favouriteModel)
  }
}
