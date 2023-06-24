import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeService} from "../../service/recipe/recipe.service";
import {RecipeModel} from "../../models/recipe-model";
import {ReviewService} from "../../service/review/review.service";
import {ReviewModelRequest} from "../../models/review-model-request";
import {ReviewModelResponse} from "../../models/review-model-response";
import {HttpParams} from "@angular/common/http";

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
  constructor(private _activatedRoute:ActivatedRoute,private recipeService: RecipeService , private _reviewService:ReviewService) {}

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(params => {
         this.recipeId = parseInt(<string>params.get('id'))

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

  postComment(comment:any){
    let reviewModel = new ReviewModelRequest()
    reviewModel.comment = comment;
    reviewModel.userId= 9
    reviewModel.recipeId = this.recipeId
    console.log(JSON.stringify(reviewModel))
    this._reviewService.post(reviewModel).subscribe(() => {
        this.loadComment()
      })
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
      this._reviewService.getCommentsByRecipeId(this.recipeId).subscribe((response:any) => {
          this.comments = response
          console.log(this.comments)
        })
    }
  }

}
