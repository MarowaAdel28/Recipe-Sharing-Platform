import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ContactComponent} from "./components/contact/contact.component";
import {AboutComponent} from "./components/about/about.component";
import {ViewRecipeDetailsComponent} from "./components/view-recipe-details/view-recipe-details.component";

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'view-recipe/details/:id',component:ViewRecipeDetailsComponent},
  {path:'contact',component:ContactComponent},
  {path:'about',component:AboutComponent},
  {path:'',component:HomeComponent},
  {path:'**',component:HomeComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
