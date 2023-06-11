import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './client/components/user/user.component';
import {HttpClientModule} from "@angular/common/http";
import { ViewRecipeComponent } from './client/components/view-recipe/view-recipe.component';
import {HeaderComponent} from "./client/components/shared/header/header.component";
import { FooterComponent } from './client/components/shared/footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ViewRecipeComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
