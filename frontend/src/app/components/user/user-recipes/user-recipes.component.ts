import {Component, OnInit} from '@angular/core';
import {UserRecipeService} from "../../../service/user-recipe/user-recipe.service";
import {NotificationService} from "../../../service/notifications/notification.service";

@Component({
  selector: 'app-all-recipes',
  templateUrl: './user-recipes.component.html',
  styleUrls: ['./user-recipes.component.css']
})
export class UserRecipesComponent implements OnInit{
  currentPage: number = 0;
  pageSize: number = 9;
  totalItems: number = 0;
  totalPages: number = 0;
  paginatedList: any[] = [];
  totalPagesArray: number[] = [];


  constructor(private userRecipeService: UserRecipeService, private notificationService:NotificationService) {}

  ngOnInit() {
    this.getPaginatedData();
  }

  getRejectionMessage(id:number) {
    this.userRecipeService.getRejectedRecipe(id).subscribe(response => {
      alert(response.message)
    }, error => {
      this.notificationService.showErrorNotification("Oops! something is wrong")
    })
  }

  getStatusColorStyle(status: string): object {
    let color: string;

    if (status === 'waiting') {
      color = '#4682B4';
    } else if (status === 'accepted') {
      color = 'green';
    } else if (status === 'rejected') {
      color = 'red';
    } else {
      color = ''; // Default color if none of the conditions match
    }

    return { 'color': color };
  }

  // getStatusColorClass(status: string): string {
  //   if (status === 'waiting') {
  //     console.log('waiting')
  //     return 'status-yellow';
  //   } else if (status === 'accepted') {
  //     return 'status-green';
  //   } else if (status === 'rejected') {
  //     return 'status-red';
  //   } else {
  //     return ''; // Default class if none of the conditions match
  //   }
  // }


  deleteRecipe(id:number){
    console.log("delete recipe")
    this.userRecipeService.updateRecipeDeletion(id,{isDeleted:true}).subscribe(response=>{
      this.notificationService.showNotification("Recipe Deleted Successfully")
      this.getPaginatedData();
    }, error => {
     this.notificationService.showErrorNotification("Failed to delete recipe")
    })
  }

  getPaginatedData() {
    const params = {
      page: this.currentPage.toString(),
      size: this.pageSize.toString()
    };

    this.userRecipeService.getPaginationUserRecipes(1,params).subscribe((response: any) => {
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
}
