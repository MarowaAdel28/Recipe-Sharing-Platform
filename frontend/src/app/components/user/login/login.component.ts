import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/service/notifications/notification.service';
import { UserService } from 'src/app/service/user/user.service';
import {AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder,private router: Router,private authservice:AuthService,private userService: UserService,private notificationService:NotificationService, private userservice:UserService) {
  }

  ngOnInit() {
    console.log("hamadaaaaaa")
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')]],
      password: ['', [Validators.required, Validators.minLength(5),Validators.maxLength(50)]]
    });
  }

  
  postData(user:any): void {
    this.userService.login(user)
      .subscribe(
        (response: any) => {
          console.log(response);
          localStorage.setItem('token', response.access_token);
          if(this.authservice.GetRoleByToken(this.authservice.getToken()) === "user"){
            this.router.navigateByUrl('home');
          
          }
         else{
            this.router.navigateByUrl('dashboard');
         }
         this.userservice.setValue(this.authservice.GetNameByToken(this.authservice.getToken()));
         this.userservice.setLogged(true);
        },
        (error: any) => {
          console.error(error);
          this.notificationService.showErrorNotification("Failed to login")
        }
      );
  }


login(): void {

  console.log(JSON.stringify(this.loginForm.value))
  if (this.loginForm.valid) {
    this.postData(this.loginForm.value)
  } else {
    this.notificationService.showErrorNotification("Invalid Data")
  }
}


}



