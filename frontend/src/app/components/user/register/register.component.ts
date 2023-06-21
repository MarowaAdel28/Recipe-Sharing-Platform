import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {matchPassword} from "./matchPassword.component";
import { RegisterModel } from "../../../models/register-model";
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {UserService} from "../../../services/user/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup

  constructor(private formBuilder: FormBuilder,private userService: UserService) {

  }

  ngOnInit() {

    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(25)]],
      email: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
       repeatPassword:['',[Validators.required, Validators.minLength(6), Validators.maxLength(6), this.matchPasswords.bind(this)]],
      // , this.matchPasswords.bind(this)
      age: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      gender: ['', Validators.required]
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
      this.userService.post(user)
        .subscribe(
          response => {
            console.log(response); // Do whatever you want with the data
          },
          error => {
            console.error(error);
          }
        );
    }


  register(): void {
    alert(JSON.stringify(this.registrationForm.value))
    this.postData(this.registrationForm.value)
  }

}
