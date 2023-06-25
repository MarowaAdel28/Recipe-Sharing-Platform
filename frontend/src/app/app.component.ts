// @ts-ignore
import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';

// @ts-ignore
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private auth:AuthService){}

  ngOnInit(): void {
   this.isTokenValid();
  }


isTokenValid() {
   if(!this.auth.isTokenValid()){
        this.auth.deleteToken();
    }
}

  isAdmin() {
    if(this.auth.getToken()=== "" || this.auth.GetRoleByToken(this.auth.getToken())==="user"){
      return false;
    }    
      return true;
  }


}
