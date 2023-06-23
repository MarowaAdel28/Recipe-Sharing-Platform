import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {ReviewModel} from "../../models/review-model";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private _apiService:ApiService) { }

post(reviewModel:ReviewModel){
  const headers = {
    "Content-Type": "application/json",
  };
    return this._apiService.post("review",reviewModel,headers)
  }

  getCommentsByRecipeId(recipeId: number | null){

    return this._apiService.getAll(`review/recipe/${recipeId}`)

  }
}


