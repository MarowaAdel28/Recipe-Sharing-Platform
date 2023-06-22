import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ApiService } from 'src/app/Services/api.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-recipes-list',
  templateUrl: './recipes-list.component.html',
  styleUrls: ['./recipes-list.component.css'],
})
export class RecipesListComponent implements OnInit {


  recipesCount: any;
  pageIndex: any = 0;
  pageSize: any = 10;
  recipes: any = [];
  tabIndex: any = 0;


  constructor(private service: ApiService) { }


  ngOnInit(): void {
    this.onAcceptedTab();
  }


  setTabIndex(event: number) {
    this.recipes = []
    this.tabIndex = event;
    this.pageIndex = 0;

    if (this.tabIndex == 0) {
      this.onAcceptedTab();
    }
    else if (this.tabIndex == 1) {
      this.onRejectedTab();
    }
    else {
      this.onWaitingTab();
    }

  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;

    if (this.tabIndex == 0) {
      this.getAccepted();
    }
    else if (this.tabIndex == 1) {
      this.getRejected();
    }
    else {
      this.getWaiting();
    }

  }

  onAcceptedTab() {
    this.getAcceptedCount();
    this.getAccepted();
  }

  onRejectedTab() {
    this.getRejectedCount();
    this.getRejected();
  }

  onWaitingTab() {
    this.getWaitingCount();
    this.getWaiting();
  }

  getAccepted() {
    this.service.getِAcceptedRecipesData(this.pageIndex).subscribe({
      next: (res) => {
        this.recipes = res;
        console.log("accepted  " + this.recipes);
      }
    });
  }


  getAcceptedCount() {
    this.service.getAcceptedCount().subscribe({
      next: (res: any) => {
        this.recipesCount = res;
      }
    });
  }


  getRejected() {
    this.service.getِRejectedRecipesData(this.pageIndex).subscribe({
      next: (res) => {
        this.recipes = res
        console.log("rejected  " + this.recipes);
      }
    });
  }


  getRejectedCount() {
    this.service.getRejectedCount().subscribe({
      next: (res: any) => {
        this.recipesCount = res;
      }
    });
  }

  getWaiting() {
    this.service.getِWaitingRecipesData(this.pageIndex).subscribe({
      next: (res) => {
        this.recipes = res
        console.log("waiting  " + this.recipes);

      }
    });
  }


  getWaitingCount() {
    this.service.getWaitingCount().subscribe({
      next: (res: any) => {
        this.recipesCount = res;
      }
    });
  }


  acceptRecipe(recipeId: number) {
    Swal.fire({
      width: '350px',
      iconColor: 'rgb(191, 15, 86)',
      color: 'rgb(191, 15, 86)',
      cancelButtonColor: 'rgb(191, 15, 86)',
      confirmButtonColor: 'rgb(191, 15, 86)',
      background: '#777',
      toast: true,
      title: 'Accept recipe?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes, Accept!',
      cancelButtonText: 'No',
    }).then((result: any) => {
      if (result.value) {
        this.service.acceptRecipe(recipeId).subscribe({
          next: (res) => {
            this.afterSubmittion()
          },
          error: (res) => {
            Swal.fire('Sorry...', 'Something went wrong!')
          }
        })
      }
    })
  }


  rejectRecipe(recipeId: number) {

    Swal.fire({
      width: '350px',
      iconColor: 'rgb(191, 15, 86)',
      color: 'rgb(191, 15, 86)',
      cancelButtonColor: 'rgb(191, 15, 86)',
      confirmButtonColor: 'rgb(191, 15, 86)',
      background: '#777',
      toast: true,
      title: 'Send a rejection message',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Send',
      cancelButtonText: 'No',
      input: 'text',
      inputAttributes: {
        autocapitalize: 'off'
      },

    }).then((result: any) => {
      if (result.isConfirmed) {
        this.service.rejectRecipe(recipeId, result.value).subscribe({
          next: (res) => {
            this.afterSubmittion()
          },
          error: (res) => {
            Swal.fire('Sorry...', 'Something went wrong!')
          }
        })
      }
    })
  }

  afterSubmittion() {
    Swal.fire({
      position: 'bottom-end',
      icon: 'success',
      title: 'Submitted successfuly',
      showConfirmButton: false,
      background: '#777',
      toast: true,
      width: '250px',
      timer: 1500,
      iconColor: 'rgb(191, 15, 86)',
      color: 'rgb(191, 15, 86)',
    });
    this.onWaitingTab();
  }

}


