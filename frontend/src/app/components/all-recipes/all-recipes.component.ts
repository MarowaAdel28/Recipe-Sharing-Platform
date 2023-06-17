import { Component } from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import {RecipeService} from "../../services/recipe/recipe.service";

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent {
  recipes: RecipeModel[];

  constructor(private recipeService: RecipeService) { }
  ngOnInit() {
    this.recipeService.getAll().subscribe((recipes) => {
      this.recipes = recipes;
    },(error) => {
      console.error('Failed to load recipes', error);
    });
  }

}
