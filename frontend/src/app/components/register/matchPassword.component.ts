import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export const matchPassword : ValidatorFn= (control:AbstractControl):ValidationErrors|null=>{

  let Password= control.get('password');
  let RepeatPassword = control.get('repeatPassword');
  if(Password && RepeatPassword && Password?.value != RepeatPassword?.value){
    return { passwordmatcherror : true }
  }
  return null;
  // // @ts-ignore
  // const password = form.get('password').value;
  // // @ts-ignore
  // const repeatPassword = form.get('repeatPassword').value;
  //
  // if (password !== repeatPassword) {
  //   // @ts-ignore
  //   form.get('repeatPassword').setErrors({ passwordMismatch: true });
  // } else {
  //   // @ts-ignore
  //   form.get('repeatPassword').setErrors(null);
  // }
}
