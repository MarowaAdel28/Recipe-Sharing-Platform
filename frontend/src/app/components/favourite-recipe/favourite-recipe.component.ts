import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FavouriteService} from "../../service/favourite/favourite.service";
import {FavouriteResponseModel} from "../../models/favourite-response-model";

@Component({
  selector: 'app-favourite-recipe',
  templateUrl: './favourite-recipe.component.html',
  styleUrls: ['./favourite-recipe.component.css']
})
export class FavouriteRecipeComponent implements OnInit{
  @Input() favouritModel:FavouriteResponseModel
  @Output() status= new EventEmitter<number>()
  constructor(private _favouriteService:FavouriteService ) {
  }

  ngOnInit(): void {
    console.log(this.favouritModel);
    this._favouriteService.findByRecipeAndUserIds(this.favouritModel).subscribe(
      {
        next:(response:any) => {
          console.log(response)

        }

      },

    )
  }



}
