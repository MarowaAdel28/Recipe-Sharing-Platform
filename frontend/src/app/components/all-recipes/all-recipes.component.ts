import {Component, OnInit} from '@angular/core';
import {RecipeService} from "../../service/recipe/recipe.service";
import {CategoryModel} from "../../models/category-model";
import {CategoryService} from "../../service/category/category.service";
import {ReviewModel} from "../../models/review-model";
import {RateModel} from "../../models/rate-model";

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
  categoryId: string = '';


  constructor(private recipeService: RecipeService, private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getPaginatedData();
    this.getAllCategories();
  }

  getRate(rates: RateModel[]): number {

    const sum = rates.reduce((accumulator, currentValue) => accumulator + currentValue.rate, 0);
    const count = rates.length;
    if (count === 0) {
      return 0;
    }
    return sum / count;
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
      this.totalPagesArray = Array.from({length: this.totalPages}, (_, i) => i);
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

  findRecipesByNameAndCategory(recipeName: string, recipeCategoryId: string) {
    const params = {
      name: recipeName,
      categoryId: recipeCategoryId,
      page: this.currentPage.toString(),
      size: this.pageSize.toString()
    };

    this.recipeService.findRecipesByNameAndCategory(params).subscribe((response: any) => {
      this.paginatedList = response.data;
      this.totalItems = response.totalItems;
      this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i );
    });
  }
  protected readonly requestIdleCallback = requestIdleCallback;

}
