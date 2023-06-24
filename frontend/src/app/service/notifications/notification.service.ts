import { Injectable } from '@angular/core';
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  showNotification(message:string): void {
    Swal.fire({
      title: 'Success!',
      text: message,
      icon: 'success',
      confirmButtonText: 'OK',
    });
  }

  showErrorNotification(message:string): void {
    Swal.fire({
      title: 'Error!',
      text: message,
      icon: 'error',
      confirmButtonText: 'OK',
    });
  }

  showInfo(message:string) {
    Swal.fire({
      icon: 'info',
      title: 'message from admin',
      text: message,
      confirmButtonText: 'OK'
    });
  }

  // showNotification(): void {
  //   const horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  //   const verticalPosition: MatSnackBarVerticalPosition = 'top';
  //   this.snackBar.open('Adding Recipe Successful!', 'Close', {
  //     duration: 3000, // Set the duration for how long the notification will be displayed (in milliseconds)
  //     horizontalPosition: horizontalPosition,
  //     verticalPosition: verticalPosition
  //   });
  // }
}
