import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {UserService} from "../../../service/user/user.service";

import Swal from 'sweetalert2';
import {NotificationService} from "../../../service/notifications/notification.service";
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup

  constructor(private formBuilder: FormBuilder,private router: Router,private userservice: UserService, private authservice:AuthService,private notificationService:NotificationService) {

  }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(25)]],
      email: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
       repeatPassword:['',[Validators.required, Validators.minLength(6), Validators.maxLength(6), this.matchPasswords.bind(this)]],
      // , this.matchPasswords.bind(this)
      age: ['', [Validators.required, Validators.min(12)]],
      gender: ['f', Validators.required]
    },);
  }

  matchPasswords(control: AbstractControl): { [key: string]: boolean } | null {
    if (!this.registrationForm) {
      return null;
    }
    const password = this.registrationForm.get('password')?.value;
    const repeatPassword = control.value;

    if (password !== repeatPassword) {
      return { passwordMismatch: true };
    }

    return null;
  }

    postData(user:any): void {
      this.userservice.register(user)
        .subscribe(
          (response: any) => {
            localStorage.setItem('token', response.access_token);
            this.router.navigateByUrl('home');
            this.userservice.setValue(this.authservice.GetNameByToken(this.authservice.getToken()));
            this.userservice.setLogged(true);
          },
          (error: any) => {
            console.error(error);
            this.notificationService.showErrorNotification("Failed to register")
          }
        );
    }


  register(): void {
    console.log(JSON.stringify(this.registrationForm.value))
    if (this.registrationForm.valid) {
      this.postData(this.registrationForm.value)
    } else {
      this.notificationService.showErrorNotification("Invalid Data")
    }
  }

}
