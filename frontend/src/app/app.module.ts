// @ts-ignore
import { NgModule } from '@angular/core';
// @ts-ignore
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './client/components/user/user.component';
// @ts-ignore
import {HttpClientModule} from "@angular/common/http";
import { ViewRecipeComponent } from './client/components/view-recipe/view-recipe.component';
import {HeaderComponent} from "./client/components/shared/header/header.component";
import { FooterComponent } from './client/components/shared/footer/footer.component';
import { RegisterComponent } from './client/components/register/register.component';
import { LoginComponent } from './client/components/login/login.component';
import {ReactiveFormsModule} from "@angular/forms";


// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ViewRecipeComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
