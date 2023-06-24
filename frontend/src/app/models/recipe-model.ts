import {CategoryModel} from "./category-model";
import {IngredientModel} from "./ingredient-model";
import {ReviewModel} from "./review-model";

export class RecipeModel{
  id:number;
  name:string
  steps:string;
  cooks_count:string;
  status:string;
  date:string
  preparingTime:string;
  persons:string;
  category:CategoryModel;
  recipeHasIngredientsList:IngredientModel[];
  reviewList:ReviewModel[];
}
