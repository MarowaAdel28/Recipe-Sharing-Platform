import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {ReviewModelRequest} from "../../models/review-model-request";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private _apiService:ApiService) { }

post(reviewModel:ReviewModelRequest){
  const headers = {
    "Content-Type": "application/json",
  };
    return this._apiService.post("review",reviewModel,headers)
  }

  getCommentsByRecipeId(recipeId: number | null){

    return this._apiService.getAll(`review/recipe/${recipeId}`)

  }
}


