import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {matchPassword} from "./matchPassword.component";
import { RegisterModel } from "../../models/register-model";
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  registrationForm: FormGroup

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {

    //Validators.pattern Password
    // At least 8 characters long
    // Contains at least one uppercase letter
    // Contains at least one lowercase letter
    // Contains at least one digit
    // Contains at least one special character (e.g., !@#$%^&*)
    // password pattern ,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})')]

    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required,Validators.minLength(3),Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')]],
      password: ['', [Validators.required,Validators.minLength(5),Validators.maxLength(50)]],
      repeatPassword:['',Validators.required],
      age:['',[Validators.required,Validators.pattern('[0-9]+')]],
      gender:['',Validators.required]
    },);
  }


  confirmPasswordMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      // console.log(controlName, matchingControlName)
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      // set error on matchingControl if validation fails
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ confirmPasswordMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    }
  }

  register():void{
    alert(JSON.stringify(this.registrationForm.value))
  }

  // onSubmit() {
  //   if (this.registrationForm.invalid) {
  //     return;
  //   }

    // Process the form submission
    // console.log(this.registrationForm.value);
  }






// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'student',
//   templateUrl: './student.component.html',
//   styleUrls: ['./student.component.css']
// })
// export class StudentComponent {
//   Students: Student[] = [];
//   // addStudent( ):string{
//   //   return "2"
//   // }
//   getFullName(student: Student): string {
//     return student.firstName + ' ' + student.lastName;
//   }
//
//   addStudent(firstName: string, lastName: string, age: number) {
//     const student:Student = new Student(firstName, lastName, age);
//     if(this.isExistStudent(student)){
//       this.Students.push(student);
//       console.log(this.Students);
//     }
//     else{
//       alert("Student "+ this.getFullName(student)+ " is already exist");
//     }
//   }
//
//   getCount():number{
//     return this.Students.length;
//   }
//
//   deleteStudent(index:number){
//     this.Students.splice(index, 1);
//   }
//
//
//
//   isExistStudent(student:Student):boolean{
//
//     for(var i=0 ; i<this.Students.length ; i++){
//       if( (this.Students[i].firstName + ' ' + this.Students[i].lastName)== this.getFullName(student)){
//         return false;
//       }
//     }
//     return true;
//   }
// }
//
//
