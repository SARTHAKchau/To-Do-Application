import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoservicesService } from '../services/todoservices.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LandingpageComponent } from '../landingpage/landingpage.component';
import { MatDialog } from '@angular/material/dialog';
import { TaskdetailsComponent } from '../taskdetails/taskdetails.component';
import { Dialogbox1Component } from '../dialogbox1/dialogbox1.component';
import { Dialogbox2Component } from '../dialogbox2/dialogbox2.component';



@Component({
  selector: 'app-taskview',
  templateUrl: './taskview.component.html',
  styleUrls: ['./taskview.component.css']
})
export class TaskviewComponent implements OnInit {

  constructor(private dialog: MatDialog, private todo: TodoservicesService, private router: Router, private landingPage: LandingpageComponent) { }
  tasks: any
  ngOnInit(): void {

    this.todo.pendingTasks().subscribe((data) => {
      this.pendingData = data;
    });
    this.todo.completedTasks().subscribe((data: any) => {
      this.completedTasks = data
    })

    this.todo.gettask().subscribe((data) => {
      this.tasks = data;
      console.log(data);

    })

    this.getDate();
    console.log(this.getDate);
  }


  show = 'A'
  isShow = 'no'

  deletetask(id: any) {
    console.log(id);
    console.log(this.tasks);

    this.todo.delete(id).subscribe(() => {
      alert("Deleted Successfully")
      this.ngOnInit()
    }, (err) => {
      this.ngOnInit()
    });


  }





  movetoArchive(data: any) {
    console.log(data);
    this.todo.moveToArchive(data).subscribe((res) => {
      alert("Moved to Archive")
      this.ngOnInit();

    }, (err) => {
      alert("Task archived successfully")
      this.ngOnInit()
    })
  }

  minDate: any;

  getDate() {
    var date: any = new Date();
    var toDate: any = date.getDate();
    var month: any = date.getMonth() + 1;
    if (month < 10) {
      month = '0' + month;
    }
    var year: any = date.getFullYear();
    console.log(toDate);
    this.minDate = year + "-" + month + "-" + toDate;
  }



  openDialog1() {
    this.dialog.open(Dialogbox1Component)
      .afterClosed()
      .subscribe(() => this.ngOnInit());
    this.ngOnInit()

  }

  openDialog2(data: any) {
    console.log(data);

    this.dialog.open(Dialogbox2Component, {
      data: {
        dataKey: data
      }
    })
      .afterClosed()
      .subscribe(() => this.ngOnInit());
  }

  openDialog(data: any) {
    console.log(data);

    this.dialog.open(TaskdetailsComponent, {
      width: '400px',

      data: {
        dataKey: data
      }
    });
  }

  pendingData: any = []
  completedTasks: any = []
  completed(id: any) {
    this.todo.markComplete(id).subscribe(() => {
      alert("Task Completed")
      this.ngOnInit()
    })
  }
  delete(id: any) {
    this.todo.delete(id).subscribe(() => {
      this.ngOnInit()
    }, (err) => {
      this.ngOnInit()
    });
  }

  p: any = 1;
  count: any = 8;

}
