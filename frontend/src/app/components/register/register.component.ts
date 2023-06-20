import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {matchPassword} from "./matchPassword.component";
import { RegisterModel } from "../../models/register-model";
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {RegisterService} from "../../services/register/register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup

  constructor(private formBuilder: FormBuilder,private registerService: RegisterService) {
  }

  ngOnInit() {


    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
       repeatPassword:['',Validators.required],
      age: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      gender: ['', Validators.required]
      //createDate: ['', Validators.required]
    },);
  }



    postData(user:any): void {
      this.registerService.post(user)
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
