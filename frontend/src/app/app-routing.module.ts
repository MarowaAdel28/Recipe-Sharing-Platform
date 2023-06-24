import { DashboardSchemaComponent } from './admin-dashboard/components/dashboard/dashboard-schema/dashboard-schema.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersListComponent } from './admin-dashboard/components/users-list/users-list.component';
import { RecipesListComponent } from './admin-dashboard/components/recipes-list/recipes-list.component';
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
import {ProfileComponent} from "./components/user/profile/profile.component";
import {UserRecipesComponent} from "./components/user/user-recipes/user-recipes.component";
import {UserFavoriteRecipesComponent} from "./components/user/user-favorite-recipes/user-favorite-recipes.component";
// import { SearchRecipeComponent } from './components/search-recipe/search-recipe.component';
import {ArchiveRecipesComponent} from "./components/user/archive/archive-recipes.component";
import {EditingRecipeComponent} from "./components/user/editing-user-recipe/editing-recipe.component";

const routes: Routes = [
  {path: 'dashboard', component:DashboardSchemaComponent},
  {path: 'usersStat', component:UsersListComponent},
  {path: 'recipesStat', component:RecipesListComponent},

  {path:'home',component:HomeComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'view-recipe/details/:id',component:ViewRecipeDetailsComponent},
  {path:'contact',component:ContactComponent},
  {path:'about',component:AboutComponent},
  {path:'recipes' , component:RecipesComponent},
  {path:'dish_area' , component:DishAreaComponent},
  {path:'all-recipes' , component:AllRecipesComponent},
  {path:'post-recipe' , component:PostRecipeComponent},
  {path:'profile' , component:ProfileComponent},
  // {path:'profile/edit', component:EditingPofileComponent},
  // {path:'profile/user-recipes',component:UserRecipesComponent},
  // {path:'profile/user-favorite-recipes',component:UserFavoriteRecipesComponent},
  {path: 'profile/user-recipes',component:UserRecipesComponent},
  {path: 'profile/user-favorite-recipes',component:UserFavoriteRecipesComponent},
  {path: 'profile/user-archive-recipes',component:ArchiveRecipesComponent},
  // {path: 'profile/user-recipes/:id/edit',component:EditingRecipeComponent},
  {path:'**',component:HomeComponent},
  {path:'',component:HomeComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
