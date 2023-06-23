import {RecipeModel} from "./recipe-model";
import {UserModel} from "./user-model";

export class ReviewModelResponse{
  id:number
  comment:string;
  rate:number;
  date:string;
  recipeId:number | null;
  userId:UserModel;

}
