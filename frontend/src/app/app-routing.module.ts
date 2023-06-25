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
import { DashboardComponent } from './admin-dashboard/components/dashboard/dashboard.component';
import {AdminRoleGuard} from './service/admin-role.guard';
import {UserRoleGuard} from './service/user-role.guard';
import { SuperRoleGuard } from './service/super-role.guard';



const routes: Routes = [

  {path: 'dashboardSchema', component:DashboardSchemaComponent, canActivate:[AdminRoleGuard]},
  {path: 'dashboard', component:DashboardComponent, canActivate:[AdminRoleGuard]},
  {path: 'usersStat', component:UsersListComponent, canActivate:[AdminRoleGuard]},
  {path: 'recipesStat', component:RecipesListComponent, canActivate:[AdminRoleGuard]},

  {path:'home',component:HomeComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'view-recipe/details/:id',component:ViewRecipeDetailsComponent},
  {path:'contact',component:ContactComponent},
  {path:'about',component:AboutComponent},
  {path:'recipes' , component:RecipesComponent},
  {path:'dish_area' , component:DishAreaComponent},
  {path:'all-recipes' , component:AllRecipesComponent},
  {path:'post-recipe' , component:PostRecipeComponent, canActivate:[SuperRoleGuard]},
  {path:'profile' , component:ProfileComponent , canActivate:[SuperRoleGuard]},
  // {path:'profile/edit', component:EditingPofileComponent},
  // {path:'profile/user-recipes',component:UserRecipesComponent},
  // {path:'profile/user-favorite-recipes',component:UserFavoriteRecipesComponent},
  {path: 'profile/user-recipes',component:UserRecipesComponent, canActivate:[SuperRoleGuard]},
  {path: 'profile/user-favorite-recipes',component:UserFavoriteRecipesComponent, canActivate:[SuperRoleGuard]},
  {path: 'profile/user-archive-recipes',component:ArchiveRecipesComponent, canActivate:[SuperRoleGuard]},
  {path: 'profile/user-recipes/:id/edit',component:EditingRecipeComponent, canActivate:[SuperRoleGuard]},
  {path:'**',component:HomeComponent},
  {path:'',component:HomeComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
