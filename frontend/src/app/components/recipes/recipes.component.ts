import { Component } from '@angular/core';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent {
recipes: any[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.recipeService.getAll().subscribe((recipes) => {
      this.recipes = recipes;
    });
  }
}
