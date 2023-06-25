import {ErrorHandler,NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { TokenInterceptorService } from './service/token-interceptor.service';
import { AuthService } from './service/auth.service';
import { GlobalErrorHandlerService } from './service/global-error-handler.service';
import { GlobalHttpInterceptorService } from './service/global-http-interceptor.service';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './admin-dashboard/components/dashboard/dashboard.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSliderModule} from '@angular/material/slider';
import {MatRadioModule} from '@angular/material/radio';
import { MatGridListModule } from '@angular/material/grid-list'
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatTabsModule} from '@angular/material/tabs';
import { BarChartComponent } from './admin-dashboard/components/dashboard/bar-chart/bar-chart.component';
import { DashboardSchemaComponent } from './admin-dashboard/components/dashboard/dashboard-schema/dashboard-schema.component';
import { LineChartComponent } from './admin-dashboard/components/dashboard/line-chart/line-chart.component';
import { CardComponent } from './admin-dashboard/components/dashboard/card/card.component';
import { MiniCardComponent } from './admin-dashboard/components/dashboard/mini-card/mini-card.component';
import { PieChartComponent } from './admin-dashboard/components/dashboard/pie-chart/pie-chart.component';
import {AdminHeaderComponent } from './admin-dashboard/components/header/admin-header.component';
import { UsersListComponent } from './admin-dashboard/components/users-list/users-list.component';
import { RecipesListComponent } from './admin-dashboard/components/recipes-list/recipes-list.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { StepsPipe } from './pipes/steps.pipe';


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
import {HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
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
import { ApiService } from './service/api.service';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DashboardComponent,
    BarChartComponent,
    DashboardSchemaComponent,
    LineChartComponent,
    CardComponent,
    MiniCardComponent,
    PieChartComponent,
    AdminHeaderComponent,
    UsersListComponent,
    RecipesListComponent,
    StepsPipe,
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
    RatingComponent,
    ArchiveRecipesComponent,
    EditingRecipeComponent,
    AppRateComponent,
    RatingComponent,

  ],
  imports: [
    MatExpansionModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatButtonModule,
    MatToolbarModule,
    MatSelectModule,
    MatTabsModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatTableModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatSidenavModule,
    MatListModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    AppRoutingModule,
    HttpClientModule,
    // MatSnackBarModule,
    // MatDialogModule,
  ],
  providers: [AuthService,ApiService,

    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true },
    { provide: ErrorHandler, useClass: GlobalErrorHandlerService }],


  bootstrap: [AppComponent]
})
export class AppModule { }
