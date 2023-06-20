import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { ViewRecipeComponent } from './components/view-recipe/view-recipe.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { DishAreaComponent } from './components/dish-area/dish-area.component';
import {HttpClientModule} from "@angular/common/http";
import { AllCategoriesComponent } from './components/all-categories/all-categories.component';
import {AllRecipesComponent} from "./components/all-recipes/all-recipes.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ViewRecipeComponent,
    RecipesComponent,
    DishAreaComponent,
    AllCategoriesComponent,
    AllRecipesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
