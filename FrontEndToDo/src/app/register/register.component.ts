import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  constructor(private router: Router, private httpClient: HttpClient, private todoService: TodoservicesService) { }

  ngOnInit(): void {
  }

  addUser = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")]),
    password: new FormControl('', [Validators.required, Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$")]),
    contact: new FormControl('', [Validators.required, Validators.pattern("^[7-9][0-9]{9}$")]),
    gender: new FormControl('', Validators.required),

  })
  get email() { return this.addUser.controls['email'] }
  get password() { return this.addUser.controls['password'] }
  get gender() { return this.addUser.controls['gender'] }
  get username() { return this.addUser.controls['username'] }
  get contact() { return this.addUser.controls['contact'] }

  saveUser(data: any) {
    console.log("subscribing observable");
    this.todoService.register(data).subscribe((a) => {
      console.log(a);
      alert("Registered Successfully")
      this.router.navigate(['/login'])
    },
      (err) => {
        alert("Already Registered")
        console.log(err);
      });
  }


}
