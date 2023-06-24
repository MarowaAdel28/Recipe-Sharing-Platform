export class RegisterModel {
  UserName:string;
  Age:number;
  Email:string;
  Password:string;
  Gender:string;
   RepeatPassword:string;
  //CreateDate:Date;
  // persons:string;

  constructor(UserName:string,Age:number,Email:string,Password:string,Gender:string,RepeatPassword:string) {
    this.UserName = UserName;
    this.Age = Age;
    this.Email=Email;
    this.Password=Password;
    this.Gender=Gender;
    this.RepeatPassword=RepeatPassword;
    //this.CreateDate=CreateDate;
  }

}
