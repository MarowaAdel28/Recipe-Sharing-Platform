import {CategoryModel} from "./category-model";
import {IngredientModel} from "./ingredient-model";
import {ReviewModel} from "./review-model";
import {RateModel} from "./rate-model";
import {IngredientId} from "./ingredient-id-model";

export class RecipeModel{
  id:number;
  recipeName:string
  steps:string;
  cooks_count:string;
  status:string;
  date:string
  preparingTime:string;
  persons:string;
  category:CategoryModel;
  recipeHasIngredientsList:IngredientId[];
  reviewList:ReviewModel[];
  rateList:RateModel[];
  quantity:string
}
