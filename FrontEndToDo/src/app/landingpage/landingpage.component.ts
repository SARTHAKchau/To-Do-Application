import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { TodoservicesService } from '../services/todoservices.service';
import { AuthService } from '../guard1/auth.service';
import { Router } from '@angular/router';
import { SnackbarComponent } from '../snackbar/snackbar.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.css']
})
export class LandingpageComponent implements OnInit {


  counter: any = 0
  ngOnInit(): void {
    this.openSnackBar()

    this.counter = sessionStorage.getItem("count")
    console.log(this.counter);


    this.todo.getImage().subscribe((a) => {
      console.log(a);
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
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  countNotification: any = 0;
  constructor(private breakpointObserver: BreakpointObserver, private todo: TodoservicesService, private authService: AuthService, private router: Router, private _snackBar: MatSnackBar) {
    this.countNotification = this.todo.notificationCount


  }

  username = localStorage.getItem("username")

  logout() {
    this.authService.logout();
    console.log("hi");
    this.router.navigate(['/login'])
  }
  durationInSeconds = 5;
  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }

}
