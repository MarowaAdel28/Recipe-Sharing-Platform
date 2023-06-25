import { Component } from '@angular/core';
import {UserRecipeService} from "../../../service/user-recipe/user-recipe.service";
import {NotificationService} from "../../../service/notifications/notification.service";
import {RateModel} from "../../../models/rate-model";
import {ReviewService} from "../../../service/review/review.service";

@Component({
  selector: 'app-archive-recipes',
  templateUrl: './archive-recipes.component.html',
  styleUrls: ['./archive-recipes.component.css']
})
export class ArchiveRecipesComponent {

  currentPage: number = 0;
  pageSize: number = 9;
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];


  constructor(private userRecipeService: UserRecipeService, private notificationService:NotificationService, private reviewService:ReviewService) {}

  ngOnInit() {
    this.getPaginatedData();
  }

  restoreRecipe(id:number){
    console.log("delete recipe")
    this.userRecipeService.updateRecipeDeletion(id,{isDeleted:false}).subscribe(response=>{
      this.notificationService.showNotification("Restore Successfully")
      this.getPaginatedData();
    }, error => {
      this.notificationService.showErrorNotification("Failed to restore recipe")
    })
  }

  getPaginatedData() {
    const params = {
      page: this.currentPage.toString(),
      size: this.pageSize.toString()
    };

    this.userRecipeService.getPaginationUserRecipesArchive(1,params).subscribe((response: any) => {
      this.paginatedList = response.data;
      this.totalItems = response.totalItems;
      this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      this.totalPagesArray = Array.from({ length: this.totalPages }, (_, i) => i );
      console.log(response.data)

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
  getRate(rateList: RateModel[]):number {
    return this.reviewService.getRate(rateList)
  }
}
