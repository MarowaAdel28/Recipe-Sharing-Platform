import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ContactComponent} from "./components/contact/contact.component";
import {AboutComponent} from "./components/about/about.component";
import {ViewRecipeDetailsComponent} from "./components/view-recipe-details/view-recipe-details.component";

import {RecipesComponent} from './components/recipes/recipes.component';
import {DishAreaComponent} from "./components/dish-area/dish-area.component";
import {AllRecipesComponent} from "./components/all-recipes/all-recipes.component";
import {PostRecipeComponent} from "./components/post-recipe/post-recipe.component";
import {RegisterComponent} from "./components/user/register/register.component";
import {LoginComponent} from "./components/user/login/login.component";

const routes: Routes = [
  {path:'',component:HomeComponent},
   {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'view-recipe/details/:id',component:ViewRecipeDetailsComponent},
  {path:'contact',component:ContactComponent},
  {path:'about',component:AboutComponent},
  {path:'recipes' , component:RecipesComponent},
  {path:'dish_area' , component:DishAreaComponent},
  {path:'all-recipes' , component:AllRecipesComponent},
  {path:'post-recipe' , component:PostRecipeComponent},
  {path:'**',component:HomeComponent},
  {path:'view-recipe' , component:ViewRecipeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
