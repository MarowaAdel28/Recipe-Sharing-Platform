import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ViewRecipeComponent} from "./components/view-recipe/view-recipe.component";
import {HomeComponent} from "./components/home/home.component";
import {ProfileComponent} from "./components/user/profile/profile.component";
import {EditingPofileComponent} from "./components/user/editing-pofile/editing-pofile.component";
import {UserRecipesComponent} from "./components/user/user-recipes/user-recipes.component";
import {UserFavoriteRecipesComponent} from "./components/user/user-favorite-recipes/user-favorite-recipes.component";

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'view-recipe' , component:ViewRecipeComponent},
  {path:'profile' , component:ProfileComponent},
  {path: 'profile/edit' , component:EditingPofileComponent},
  {path: 'profile/user-recipes',component:UserRecipesComponent},
  {path: 'profile/user-favorite-recipes',component:UserFavoriteRecipesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
