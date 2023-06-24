import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeService} from "../../service/recipe/recipe.service";
import {RecipeModel} from "../../models/recipe-model";

@Component({
  selector: 'app-view-recipe-details',
  templateUrl: './view-recipe-details.component.html',
  styleUrls: ['./view-recipe-details.component.css']
})
export class ViewRecipeDetailsComponent implements OnInit{

  recipeModel:RecipeModel = new RecipeModel();
  steps:string[]
  constructor(private _activatedRoute:ActivatedRoute, private recipeService: RecipeService ) {
  }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(parms => {
        let id = parms.get('id')

      if (typeof id === "string") {
        this.recipeService.getById(parseInt(id)).subscribe(
          {
            next: response => {
              this.recipeModel = response
              this.steps = this.recipeModel.steps.split("/");
              this.steps.splice(this.steps.length-1,1)
              console.log(this.steps)
            }
          }
        )
      }
    })
  }
}
