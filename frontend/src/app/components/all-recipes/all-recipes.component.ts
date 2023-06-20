import {Component, OnInit} from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import {RecipeService} from "../../services/recipe/recipe.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit{
  currentPage: number = 1;
  pageSize: number = 4;
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];


  constructor(private recipeService: RecipeService) {}

  ngOnInit() {
    this.getPaginatedData();
  }

  getPaginatedData() {
    const params = {
      page: this.currentPage.toString(),
      pageSize: this.pageSize.toString()
    };

    this.recipeService.getPaginationRecipes(params).subscribe((response: any) => {
      this.paginatedList = response.data;
      this.totalItems = response.totalItems;
      this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i + 1);
    });
  }

  goToPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.totalPages) {
      this.currentPage = pageNumber;
      this.getPaginatedData();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.getPaginatedData();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.getPaginatedData();
    }
  }


}
//
//
// constructor(private recipeService: RecipeService) { }
// ngOnInit() {
//   this.recipeService.getAll().subscribe((recipes) => {
//     this.recipes = recipes;
//   },(error) => {
//     console.error('Failed to load recipes', error);
//   });
// }
//


