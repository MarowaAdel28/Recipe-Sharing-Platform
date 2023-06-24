import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/user/user.service";
import { FormBuilder, FormGroup,FormControl, Validators } from '@angular/forms';
import {NotificationService} from "../../../services/notifications/notification.service";
import {UserProfile} from "../../../models/user-profile";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user:UserProfile;

  userForm: FormGroup;

  constructor(private _formBuilder:FormBuilder, private userService:UserService, private notificationService:NotificationService){}
  ngOnInit(): void {

    this.fetchData();

    this.userForm=this._formBuilder.group({
      userName:['',[Validators.required, Validators.minLength(3), Validators.maxLength(25)]],
      oldPassword:['',[Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
      newPassword:['',[Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
      repeatedPassword:['',[Validators.required,Validators.maxLength(6),Validators.minLength(6)]],
      age:[0,[Validators.required, Validators.min(12)]]
    });
  }

  isEditingUserName = false;

  toggleEditUserName() {
    this.isEditingUserName = !this.isEditingUserName;
  }

  saveUserName() {
    // Save the updated username here
    this.toggleEditUserName();
    // console.log(this.user)
    this.editUserName();
  }

    editUserName() {
      let body = {
        user: '5',
        userName: this.user.userName,
      };
      console.log(body)
      this.userService.put(body).subscribe(response => {
        this.notificationService.showNotification("Updated Successfully")
      }, error => {
        this.notificationService.showErrorNotification("Updated Failed")
      });
    }
  fetchData(){
    this.userService.getUserById(5).subscribe(response => {
        // console.log(response); // Do whatever you want with the data
        this.user=new UserProfile(response.username,response.age,response.email,response.gender);
        console.log(this.user)
        this.userForm.patchValue({
          userName: this.user.userName,
          age: this.user.age
        });
      },
      error => {
        // console.error(error);
        this.notificationService.showErrorNotification("Oops! Something is wrong!")
      }
    );
  }
//   updateUser():void
//   {
//     let body=this.userForm.value;
//     console.log(JSON.stringify(body));
//     body.user=5;
//     if (body.valid) {
//       this.userService.put(body).subscribe(response => {
//         this.notificationService.showNotification("Updated Successfully")
//       }, error => {
//         this.notificationService.showErrorNotification("Updated Failed")
//       });
//     }
// // else {
// //     this.notificationService.showErrorNotification("invalid Data")
// //   }
//
//   }
}


