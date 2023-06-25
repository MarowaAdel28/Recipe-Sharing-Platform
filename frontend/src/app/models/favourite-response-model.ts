import {RecipeModel} from "./recipe-model";
import {UserModel} from "./user-model";

export class FavouriteResponseModel{
  id:number
  recipeId:RecipeModel
  userId:UserModel

  constructor(recipe:RecipeModel , userId:UserModel) {
    this.recipeId = recipe
    this.userId = userId
  }
}
