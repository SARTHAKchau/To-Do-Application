import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../guard1/auth.service';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private httpClient: HttpClient, private todoservice: TodoservicesService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {

  }
  formData: any = {};
  addUser = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })
  get username() { return this.addUser.controls['username'] }
  get password() { return this.addUser.controls['password'] }

  token: any = {}

  login(data: any) {
    this.todoservice.loginUser(data).subscribe((data) => {
      this.token = data;
      console.log(this.token.token);
      localStorage.setItem("token", this.token.token)
      console.log(localStorage.getItem("token"));
      this.authService.logincheck();
      this.router.navigate([this.authService.redirectUrl])
    },
      (err) => {
        alert("invalid cred")
      })
  }
  signUp() {
    this.router.navigate(['/register'])
  }

}
