import { Component, OnInit } from '@angular/core';
import { RecipeService } from "../../services/recipe/recipe.service";
import { CategoryService } from "../../services/category/category.service";
import { CategoryModel } from "../../models/category-model";

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit {
  currentPage: number = 0;
  pageSize: number = 9;
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];
  categories: CategoryModel[];
  name: string = '';


  constructor(private recipeService: RecipeService, private categoryService: CategoryService) {}

  ngOnInit() {
    this.getPaginatedData();
    this.getAllCategories();
  }

  getPaginatedData() {
    const params = {
      page: this.currentPage.toString(),
      size: this.pageSize.toString()
    };

    this.recipeService.getPaginationRecipes(params).subscribe((response: any) => {
      this.paginatedList = response.data;
      this.totalItems = response.totalItems;
      this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i);
    });
  }

  goToPage(pageNumber: number) {
    if (pageNumber >= 0 && pageNumber < this.totalPages) {
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
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getPaginatedData();
    }
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe(
      (response: CategoryModel[]) => {
        this.categories = response;
        console.log('Categories:', this.categories);
      },
      (error) => {
        console.log('Error:', error);
      }
    );
  }
  searchRecipesByName(recipeName: string) {
    const params = {
      name: recipeName,
      page: this.currentPage.toString(),
      size: this.pageSize.toString()
    };

    this.recipeService.findRecipesByName(params).subscribe((response: any) => {
      this.paginatedList = response.data;
      this.totalItems = response.totalItems;
      this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i );
    });
  }
  protected readonly requestIdleCallback = requestIdleCallback;
}
