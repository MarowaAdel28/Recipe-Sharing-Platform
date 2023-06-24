import {RecipeModel} from "./recipe-model";
import {UserModel} from "./user-model";

export class FavouriteRequestModel{
  id:number
  recipeId:number
  userId:number

  constructor(recipe:number , userId:number) {
    this.recipeId = recipe
    this.userId = userId
  }
}
