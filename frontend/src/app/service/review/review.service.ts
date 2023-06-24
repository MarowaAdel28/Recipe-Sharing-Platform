import { Injectable } from '@angular/core';
import {ApiService} from "../../service/api.service";
import {ReviewModelRequest} from "../../models/review-model-request";
import {RecipeModel} from "../../models/recipe-model";
import {HttpParams} from "@angular/common/http";
import {RateModel} from "../../models/rate-model";

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
  update(recipeModel:RecipeModel){
    return this._apiService.put("review",recipeModel)
  }

  postRate(rateData:any) {
    const headers = {
      "Content-Type": "application/json",
    }
    return this._apiService.post("rate",rateData,headers)
  }

  getRate(rates: RateModel[]): number {

    const sum = rates.reduce((accumulator, currentValue) => accumulator + currentValue.rate, 0);
    const count = rates.length;
    if (count === 0) {
      return 0;
    }
    return sum / count;
  }
}


