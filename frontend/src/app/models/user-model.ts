export class UserModel{
  id:number;
  userName:string;
  email:string;
  gender:string;

  constructor(id:number) {
    this.id = id;
  }
}
