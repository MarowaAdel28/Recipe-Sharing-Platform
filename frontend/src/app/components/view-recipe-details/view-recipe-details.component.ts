import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeModel} from "../../models/recipe-model";
import {ReviewModelRequest} from "../../models/review-model-request";
import {ReviewModelResponse} from "../../models/review-model-response";
import {FavouriteService} from "../../service/favourite/favourite.service";
import {FavouriteRequestModel} from "../../models/favourite-request-model";
import {FavouriteResponseModel} from "../../models/favourite-response-model";
import {UserModel} from "../../models/user-model";
import {RecipeService} from "../../service/recipe/recipe.service";
import {ReviewService} from "../../service/review/review.service";

@Component({
  selector: 'app-view-recipe-details',
  templateUrl: './view-recipe-details.component.html',
  styleUrls: ['./view-recipe-details.component.css']
})
export class ViewRecipeDetailsComponent implements OnInit{

  recipeModel:RecipeModel = new RecipeModel();
  favouritModel:FavouriteResponseModel
  favouritRequestModel:FavouriteRequestModel
  steps:string[]
  recipeId:number | null
  isChecked:boolean
  isFav:boolean
  comments:ReviewModelResponse[]
  recipeRate:number
  constructor(private _activatedRoute:ActivatedRoute
              ,private recipeService: RecipeService
              ,private _reviewService:ReviewService
              ,private _favouriteService:FavouriteService) {

  }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(parms => {
         this.recipeId = parseInt(<string>parms.get('id'))


        this.recipeService.getById(this.recipeId).subscribe(
          {
            next: response => {
              this.recipeModel = response

              this.steps = this.recipeModel.steps.split("/");
              this.steps.splice(this.steps.length-1,1)
              // alert(response)
              // console.log(response)

            }
          }
        )
    })
    let user = new UserModel(10)
    this.favouritRequestModel = new FavouriteRequestModel(this.recipeId,10)
              console.log(this.favouritRequestModel)
  }


  postComment(comment:any):void{
    let reviewModel = new ReviewModelRequest()
    reviewModel.comment = comment;
    reviewModel.userId= 10
    reviewModel.recipeId = this.recipeId
    console.log(JSON.stringify(reviewModel))
    this._reviewService.post(reviewModel).subscribe(
      response => {
        this.loadComment()
      }
    )
  }

  onRateChange(rate:number){
    this.recipeRate = rate;
    const httpParams = {
      'recipeId': this.recipeId,
      'userId': 2,
      'rate': this.recipeRate
    };
    console.log(this.recipeRate)
    this._reviewService.postRate(httpParams).subscribe((response:any)=>{
      console.log("rate done")
    },(error:any)=>{
      console.log("faild to rate")
    })
  }

  loadComment(){


    if (this.isChecked) {
      this._reviewService.getCommentsByRecipeId(this.recipeId).subscribe(
        response => {
          this.comments = response
          console.log(this.comments)
        }
      )
    }
  }




}
