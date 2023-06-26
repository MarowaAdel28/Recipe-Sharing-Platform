import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FavouriteRequestModel} from "../../models/favourite-request-model";
import {FavouriteService} from "../../service/favourite/favourite.service";
import {UserModel} from "../../models/user-model";
import {FavouriteResponseModel} from "../../models/favourite-response-model";
import {ActivatedRoute} from "@angular/router";
import {RecipeModel} from "../../models/recipe-model";

@Component({
  selector: 'app-favourite-recipe',
  templateUrl: './favourite-recipe.component.html',
  styleUrls: ['./favourite-recipe.component.css']
})
export class FavouriteRecipeComponent implements OnInit{

  @Input() status :boolean;
  @Input() favouritModel:FavouriteRequestModel
  @Input() recipeModel:RecipeModel
  @Output() change = new EventEmitter<boolean>()
   recipeId: number;

  constructor(private _favService:FavouriteService
              ,private _activatedRoute:ActivatedRoute
              ,private authService:AuthService) {
  }
  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(parms => {
      this.recipeId = parseInt(<string>parms.get('id'))
    })
    console.log("INNNN " ,this.favouritModel)
    this._favService.findFavouriteRecipeById(this.favouritModel.userId,this.favouritModel.recipeId).subscribe(
      response => {
        console.log("response : ", response)
        if(response.id == null) this.status = false
        else
          this.status = true

      },
      error => {
        console.log(error)
      }
    )

  }
  setStatus(){
    console.log(this.status);
    this.change.emit(this.status)
  }

  loadStatus(value : boolean){
    console.log(value)
    let status = value
    if(status)
      this.addToFavourites(status)
    else
      this.removeFromFavourites()
  }

  addToFavourites(status:boolean){
    let user = new UserModel(this.authService.GetIDByToken(this.authService.getToken()))

    let favModel = new FavouriteRequestModel(this.recipeId, user.id)
    console.log(favModel);
    this._favService.post(favModel).subscribe(
      (response:any) => {
        console.log("Fav inserted <3")
      }
    )
  }

  removeFromFavourites(){
    let user = new UserModel(this.authService.GetIDByToken(this.authService.getToken()))
    let favModel = new FavouriteResponseModel(this.recipeModel,user)
    this._favService.delete(favModel).subscribe(
      (response:any) => {
        console.log("Fav Deleted")
      }
    )
  }
}
