import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { NotificationService } from './notifications/notification.service';

@Injectable({
  providedIn: 'root'
})
export class AdminRoleGuard implements CanActivate {
  currentRole: any;

  constructor(private service: AuthService, private router: Router, private notification: NotificationService) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.service.isLoggedIn()) {
      this.currentRole = this.service.GetRoleByToken(this.service.getToken());
      if (this.currentRole == "admin" || this.currentRole == "user") {
        return true;
      } else {
        alert('you are not authorized to access this menu');
        this.router.navigate(['']);
        this.notification.showErrorNotification("Not Autherized...plz, login!")

        return false;
      }
    } else {
      this.router.navigate(['login']);
      this.notification.showErrorNotification("Not Autherized...plz, login!")

      return false;
    }
  }

}
