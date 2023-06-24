import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeService} from "../../services/recipe/recipe.service";
import {RecipeModel} from "../../models/recipe-model";
import {ReviewService} from "../../services/review/review.service";
import {ReviewModelRequest} from "../../models/review-model-request";
import {ReviewModelResponse} from "../../models/review-model-response";

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
  comments:ReviewModelResponse[]
  recipeRate:number
  constructor(private _activatedRoute:ActivatedRoute,private recipeService: RecipeService , private _reviewService:ReviewService) {
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
        alert("Success")
      }
    )
  }

  onRateChange(rate:number){
    this.recipeRate = rate;
    console.log(this.recipeRate)
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
