import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ViewRecipeComponent} from "./components/view-recipe/view-recipe.component";
import {HomeComponent} from "./components/home/home.component";
import { RecipesComponent } from './components/recipes/recipes.component';

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'view-recipe' , component:ViewRecipeComponent},
  {path:'recipes' , component:RecipesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
