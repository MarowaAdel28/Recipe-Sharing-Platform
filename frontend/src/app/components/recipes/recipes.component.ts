import { Component } from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import {ReviewModel} from "../../models/review-model";
import {RecipeService} from "../../service/recipe/recipe.service";

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
        console.log(this.recipes)
        for (const item of recipes) {
          for (const item2 of item.reviews) {
            console.log(item2.rate + "...eeeeeeee............");
          }
        }
        },(error) => {
          console.error('Failed to load recipes', error);
        });
    }

    getRate(reviews: ReviewModel[]): number{

      const sum = reviews.reduce((accumulator, currentValue) => accumulator + currentValue.rate, 0);
      const count = reviews.length;
      if (count === 0) {
        return 0;
      }
      return sum / count;
  }
}
