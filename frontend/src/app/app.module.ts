import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { ViewRecipeDetailsComponent } from './components/view-recipe-details/view-recipe-details.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { DishAreaComponent } from './components/dish-area/dish-area.component';
import {HttpClientModule} from "@angular/common/http";
import { AllCategoriesComponent } from './components/all-categories/all-categories.component';
import {AllRecipesComponent} from "./components/all-recipes/all-recipes.component";
import { FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import {PostRecipeComponent} from "./components/post-recipe/post-recipe.component";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { ViewRecipeComponent } from './components/view-recipe/view-recipe.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { DishAreaComponent } from './components/dish-area/dish-area.component';
// import { AllCategoriesComponent } from './components/all-categories/all-categories.component';
import { RegisterComponent } from './components/user/register/register.component';
import { LoginComponent } from './components/user/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    AboutComponent,
    ContactComponent,
    ViewRecipeDetailsComponent,
    RecipesComponent,
    DishAreaComponent,
    AllCategoriesComponent,
    PostRecipeComponent,
    AllRecipesComponent,
    ViewRecipeComponent,
    RecipesComponent,
    DishAreaComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    HttpClientModule,
    MatSnackBarModule,
    MatDialogModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
