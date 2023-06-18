// @ts-ignore
import { NgModule } from '@angular/core';
// @ts-ignore
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./client/components/register/register.component";
import {LoginComponent} from "./client/components/login/login.component";

const routes: Routes = [
   {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent}
];

// @ts-ignore
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
