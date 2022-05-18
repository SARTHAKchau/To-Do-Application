import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LandingpageComponent } from '../landingpage/landingpage.component';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {

  constructor(private todo: TodoservicesService, private http: HttpClient, private router: Router, private landing: LandingpageComponent) { }

  getFile: any = File
  ngOnInit(): void {

    this.todo.getUser().subscribe((a) => {
      this.user = a;
      console.log(this.user);
      console.log(this.user.username);
    })

    this.todo.getImage().subscribe((a) => {

      this.retrieveResonse = a;
      this.base64Data = this.retrieveResonse[0].data
      this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;


      console.log("inside ng onit");

    }, (error) => {
      console.log(error);

    })
  }
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string = '';
  imageName: any;

  selectedFileDetails: any = File
  url: string = ""
  selectFile(event: any) {
    if (event.target.files) {
      var reader = new FileReader()
      this.selectedFileDetails = event.target.files[0];
      reader.readAsDataURL(event.target.files[0])
      reader.onload = (event: any) => {
        this.url = event.target.result
      }
    }
  }

  updateImage() {
    console.log(this.selectedFileDetails.name);
    const uploadImageData = new FormData();
    uploadImageData.append('image', this.selectedFileDetails, this.selectedFileDetails.name);
    console.log(uploadImageData);

    this.todo.uploadImage(uploadImageData).subscribe((a) => {
      console.log(a);




    }, (error) => {

      this.landing.ngOnInit();

    })

  }
  user: any = []
  addUser = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    contact: new FormControl(''),
    gender: new FormControl(''),
  })
  get email() { return this.addUser.controls['email'] }
  get password() { return this.addUser.controls['password'] }
  get gender() { return this.addUser.controls['gender'] }
  get username() { return this.addUser.controls['username'] }
  get contact() { return this.addUser.controls['contact'] }
  saveForm(data: any) {
    console.log(data);
    this.todo.updateUser(data).subscribe((a) => {
      console.log(a);
      alert("UPDATED SUCESSFULLY")
      console.log("updated the user");
    }, (err) => {
      console.log("some error");
      console.log(err);
    })
  }





}
