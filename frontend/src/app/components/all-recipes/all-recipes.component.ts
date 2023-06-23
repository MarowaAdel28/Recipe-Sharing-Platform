import {Component, OnInit} from '@angular/core';
import {RecipeModel} from "../../models/recipe-model";
import {RecipeService} from "../../services/recipe/recipe.service";
import {HttpClient} from "@angular/common/http";
import {ReviewModel} from "../../models/review-model";

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit{
  currentPage: number = 0;
  pageSize: number = 4;
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];


  constructor(private recipeService: RecipeService) {}

  ngOnInit() {
    this.getPaginatedData();
  }

  getRate(reviews: ReviewModel[]): number{

    const sum = reviews.reduce((accumulator, currentValue) => accumulator + currentValue.rate, 0);
    const count = reviews.length;
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
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i );
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


  protected readonly requestIdleCallback = requestIdleCallback;
}
