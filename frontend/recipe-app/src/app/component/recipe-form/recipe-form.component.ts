import { Component } from '@angular/core';
import { Recipe } from '../../model/recipe.model';
import { Ingredient } from 'src/app/model/ingredient.model';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent {
  category: any;
  ingredients: Ingredient[] = [];
  steps: string[] = [];
  preparingTime: any;
  numberOfPersons: any;
  ingredient="";
  quantity="";
  step="";

  addIngredient() {
    this.ingredients.push(new Ingredient(this.ingredient,this.quantity));
    this.quantity="";
    this.ingredient="";
    console.log(this.ingredients.length)
  }

  editIngredient(index:number,ingredient:string,quantity:string) {
    console.log("edit")
    this.ingredients[index].ingredient=ingredient;
    this.ingredients[index].quantity=quantity;
    console.log(this.ingredients[index].ingredient+" "+this.ingredients[index].quantity)
  }

  removeIngredient(index: number) {
    console.log("ind "+index);
    this.ingredients.splice(index,1);
  }

  addStep() {
    this.steps.push(this.step);
    this.step="";
  }

  editStep(index:number,step:string) {
    this.steps[index]=step;
    console.log(this.steps[index]);
  }

  removeStep(index: number) {
      this.steps.splice(index, 1);
  }

  submitForm() {
    const recipe = {
      category: this.category,
      ingredients: this.ingredients,
      steps: this.steps,
      preparingDate: this.preparingTime,
      numberOfPersons: this.numberOfPersons
    };

    console.log("category "+this.category+" preparing time "+this.preparingTime+" no of persons "+this.numberOfPersons)

    console.log(recipe); // You can customize this logic to save the recipe data to your backend or perform any other actions
    for(let i=0;i<this.ingredients.length;i++) {
        console.log(this.ingredients[i].ingredient+" "+this.ingredients[i].quantity)
    }

    for(let i=0;i<this.steps.length;i++) {
      console.log(this.steps[i])
  }
  }
}
