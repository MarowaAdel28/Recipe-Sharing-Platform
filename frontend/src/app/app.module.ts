import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { ViewRecipeComponent } from './components/view-recipe/view-recipe.component';
import { PostRecipeComponent } from './components/post-recipe/post-recipe.component';
import { FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import {HttpClientModule} from "@angular/common/http";
import { ProfileComponent } from './components/user/profile/profile.component';
import { UserRecipesComponent } from './components/user/user-recipes/user-recipes.component';
import { UserFavoriteRecipesComponent } from './components/user/user-favorite-recipes/user-favorite-recipes.component';
import { EditingPofileComponent } from './components/user/editing-pofile/editing-pofile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ViewRecipeComponent,
    PostRecipeComponent,
    ProfileComponent,
    UserRecipesComponent,
    UserFavoriteRecipesComponent,
    EditingPofileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
