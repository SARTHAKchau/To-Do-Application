import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class Guard1Guard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log("Requested Url @ canActivate:" + state.url);
    return this.checkLogin(state.url);
  }

  checkLogin(url: string) {
    if (this.authService.isLoggedIn)
      return true;
    this.authService.redirectUrl = url;
    return this.router.parseUrl('/login');
  }

}
