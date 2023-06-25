export class UserProfile {
   userName: string;
   age: number;
   email: string;
   gender: string;


  constructor(userName: string, age: number, email: string, gender: string) {
    this.userName = userName;
    this.age = age;
    this.email = email;
    this.gender = gender;
  }
}
