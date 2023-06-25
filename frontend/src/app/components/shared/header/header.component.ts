import { OnInit } from '@angular/core';
// @ts-ignore
import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user/user.service';


// @ts-ignore
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogedIn: boolean = false;



  username: string = "";

  constructor(private userservice: UserService) { }

  ngOnInit() {
    this.userservice.username$.subscribe((newValue: any) => {
      this.username = newValue;
    });

    this.userservice.isLogedIn$.subscribe((newValue: any) => {
      this.isLogedIn = newValue;
    });
  }


  logout() {
    this.userservice.logout()

    }
}