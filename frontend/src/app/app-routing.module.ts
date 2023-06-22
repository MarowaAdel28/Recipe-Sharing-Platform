import { DashboardSchemaComponent } from './admin-dashboard/components/dashboard/dashboard-schema/dashboard-schema.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersListComponent } from './admin-dashboard/components/users-list/users-list.component';
import { RecipesListComponent } from './admin-dashboard/components/recipes-list/recipes-list.component';

const routes: Routes = [
  {path: 'dashboard', component:DashboardSchemaComponent},
  {path: 'users', component:UsersListComponent},
  {path: 'recipes', component:RecipesListComponent},
  {path: '',  component:DashboardSchemaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
