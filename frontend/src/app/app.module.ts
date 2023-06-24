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
import { AllCategoriesComponent } from './components/all-categories/all-categories.component';
import {AllRecipesComponent} from "./components/all-recipes/all-recipes.component";
import { FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import {HttpClientModule} from "@angular/common/http";
import { ProfileComponent } from './components/user/profile/profile.component';
import { UserRecipesComponent } from './components/user/user-recipes/user-recipes.component';
import { UserFavoriteRecipesComponent } from './components/user/user-favorite-recipes/user-favorite-recipes.component';
import {PostRecipeComponent} from "./components/post-recipe/post-recipe.component";
// import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { MatDialogModule } from '@angular/material/dialog';
import { RegisterComponent } from './components/user/register/register.component';
import { LoginComponent } from './components/user/login/login.component';
import { RatingComponent } from './components/shared/rating/rating.component';
import { ArchiveRecipesComponent } from './components/user/archive/archive-recipes.component';
import { EditingRecipeComponent } from './components/user/editing-user-recipe/editing-recipe.component';
import { AppRateComponent } from './components/app-rate/app-rate.component';



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
    RecipesComponent,
    DishAreaComponent,
    RegisterComponent,
    LoginComponent,
    PostRecipeComponent,
    ProfileComponent,
    UserRecipesComponent,
    UserFavoriteRecipesComponent,
    // EditingPofileComponent,
    RatingComponent,
    ArchiveRecipesComponent,
    EditingRecipeComponent,
    // EditingPofileComponent,
    AppRateComponent
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    AppRoutingModule,
    HttpClientModule,
    // MatSnackBarModule,
    // MatDialogModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
