import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RecipeService} from "../../services/recipe/recipe.service";

@Component({
  selector: 'app-view-recipe',
  templateUrl: './view-recipe.component.html',
  styleUrls: ['./view-recipe.component.css']
})
export class ViewRecipeComponent implements OnInit{

  constructor(private recipeService:RecipeService) {
  }
  ngOnInit(): void {
    this.recipeService.getById(1).subscribe(
      {
        next:response => {
          console.log(response)
        }
      }
    )
  }

}
