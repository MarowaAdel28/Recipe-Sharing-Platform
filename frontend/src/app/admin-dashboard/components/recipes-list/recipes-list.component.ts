import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ApiService } from 'src/app/service/api.service';
import Swal from 'sweetalert2';
import {HttpClient, HttpHeaders} from "@angular/common/http";


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


  constructor(private service: ApiService, private http: HttpClient) { }


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

  selectedFile: File | null = null;
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadImage(recipeId:number) {
    if (this.selectedFile) {
      const headers = new HttpHeaders();
      console.log("done")
      headers.append('enctype', 'multipart/form-data');
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('id',JSON.stringify(recipeId));

      this.http.post<any>('http://localhost:8080/image/upload', formData, { headers: headers }).subscribe(
        response => {
          console.log('Image uploaded successfully:', response);
        },
        error => {
          console.error('Failed to upload image:', error);
        }
      );
    }
  }

  uploadFile(file: File) {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const headers = new HttpHeaders();
    headers.append('enctype', 'multipart/form-data');

    this.http.post('/image/upload', formData, { headers }).subscribe(
      response => {
        console.log('File uploaded successfully:', response);
      },
      error => {
        console.error('Error uploading file:', error);
      }
    );
  }

}


