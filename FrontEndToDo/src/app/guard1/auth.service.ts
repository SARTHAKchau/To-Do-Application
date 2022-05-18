import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isLoggedIn: boolean = false;

  redirectUrl: string = "home/tasks";

  logincheck() {
    this.isLoggedIn=true
    console.log("auth service");
    
  }

  logout()
  {
    this.isLoggedIn=false
  }

}
