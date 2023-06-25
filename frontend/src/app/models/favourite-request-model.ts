import {RecipeModel} from "./recipe-model";
import {UserModel} from "./user-model";

export class FavouriteRequestModel{
  id:number
  recipeId:number | null
  userId:number

  constructor(recipe: number | null, userId: number) {
    this.recipeId = recipe
    this.userId = userId
  }
}
