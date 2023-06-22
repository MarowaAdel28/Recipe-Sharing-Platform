import { Component } from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import {RecipeService} from "../../services/recipe/recipe.service";


@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent {
  recipes: RecipeModel[];

  constructor(private recipeService: RecipeService) { }
  ngOnInit() {
      this.recipeService.getTop3().subscribe((recipes) => {
        this.recipes = recipes;
        },(error) => {
          console.error('Failed to load recipes', error);
        });
    }

}
