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
              let user = new UserModel(9)
              this.favouritModel = new FavouriteResponseModel(this.recipeModel,user)
              // alert(response)
              // console.log(response)
              console.log(this.favouritModel)
              this.loadFavStatus()
            }
          }
        )
    })
  }

  loadFavStatus(){
    this._favouriteService.findByRecipeAndUserIds(this.favouritModel).subscribe(
      {
        next:(response:any) => {
          console.log(response)
          this.isFav = response != null;
          console.log(this.isFav)
        }

      },

    )
  }

  postComment(comment:any):void{
    let reviewModel = new ReviewModelRequest()
    reviewModel.comment = comment;
    reviewModel.userId= 9
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


  loadStatus(){
    let status = this.isFav
    if(status)
      this.addToFavourites(status)
    else
      this.removeFromFavourites()
}

  addToFavourites(status:boolean){

    let favModel = new FavouriteRequestModel(this.recipeModel.id,9)
    console.log(favModel);
    this._favouriteService.post(favModel).subscribe(
      (response:any) => {
        console.log("Fav inserted <3")
      }
    )
  }

  removeFromFavourites(){
    let user = new UserModel(9) //dumy user
    let favModel = new FavouriteResponseModel(this.recipeModel,user)
      this._favouriteService.delete(favModel).subscribe(
        (response:any) => {
          console.log("Fav Deleted")
        }
      )
  }

}
