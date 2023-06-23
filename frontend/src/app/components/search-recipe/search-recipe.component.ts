import { Component } from '@angular/core';
import { RecipeService } from "../../services/recipe/recipe.service";

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent {
  currentPage: number = 0;
  pageSize: number = 9;
  name: string = '';
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];

  constructor(private recipeService: RecipeService) {}

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

  goToPage(pageNumber: number) {
    if (pageNumber >= 0 && pageNumber < this.totalPages) {
      this.currentPage = pageNumber;
      this.searchRecipesByName(this.name);
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.searchRecipesByName(this.name);
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.searchRecipesByName(this.name);
    }
  }
}
