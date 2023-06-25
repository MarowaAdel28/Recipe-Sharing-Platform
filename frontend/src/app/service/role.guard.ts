import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})

export class RoleGuard implements CanActivate {

  currentRole: any;

  constructor(private service: AuthService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.service.isLoggedIn()) {
      this.currentRole = this.service.GetRoleByToken(this.service.getToken());
      if (this.currentRole == "Superuser") {
        return true;
      } else {
        alert('you are not authorized to access this menu');
        this.router.navigate(['']);
        return false;
      }
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}


