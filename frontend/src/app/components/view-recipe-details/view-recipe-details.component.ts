import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeService} from "../../services/recipe/recipe.service";
import {RecipeModel} from "../../models/recipe-model";
import {ReviewService} from "../../services/review/review.service";
import {ReviewModelRequest} from "../../models/review-model-request";
import {ReviewModelResponse} from "../../models/review-model-response";
import {FavouriteService} from "../../services/favourite/favourite.service";
import {FavouriteRequestModel} from "../../models/favourite-request-model";
import {FavouriteResponseModel} from "../../models/favourite-response-model";
import {UserModel} from "../../models/user-model";

@Component({
  selector: 'app-view-recipe-details',
  templateUrl: './view-recipe-details.component.html',
  styleUrls: ['./view-recipe-details.component.css']
})
export class ViewRecipeDetailsComponent implements OnInit{

  recipeModel:RecipeModel = new RecipeModel();
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
              console.log(this.steps)
            }
          }
        )

    })
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
    console.log(this.recipeRate)
    this._reviewService.update(this.recipeModel,this.recipeRate).subscribe(
      response => {
        alert("Updated")
      }
    )
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
      response => {
        console.log("Fav inserted <3")
      }
    )
  }

  removeFromFavourites(){
    let user = new UserModel(9) //dumy user
    let favModel = new FavouriteResponseModel(this.recipeModel,user)
      this._favouriteService.delete(favModel).subscribe(
        response => {
          console.log("Fav Deleted")
        }
      )
  }

}
