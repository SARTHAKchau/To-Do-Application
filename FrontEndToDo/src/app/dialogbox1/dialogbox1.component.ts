import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { TodoservicesService } from '../services/todoservices.service';


@Component({
  selector: 'app-dialogbox1',
  templateUrl: './dialogbox1.component.html',
  styleUrls: ['./dialogbox1.component.css']
})
export class Dialogbox1Component implements OnInit {

  constructor(private todo: TodoservicesService, private route: Router) { }
  taskform = new FormGroup({

    title: new FormControl(''),
    description: new FormControl(''),
    startDate: new FormControl(''),
    endDate: new FormControl(''),
    priority: new FormControl(''),
    category: new FormControl('')
  })

  //get taskId() { return this.taskform.controls['taskId'] }
  get tasktitle() { return this.taskform.controls['title'] }
  get description() { return this.taskform.controls['description'] }
  get startdate() { return this.taskform.controls['startDate'] }
  get enddate() { return this.taskform.controls['endDate'] }
  get priority() { return this.taskform.controls['priority'] }
  get category() { return this.taskform.controls['category'] }

  saveTask(data: any) {
    console.log("task adding")
    this.todo.savetask(data).subscribe((a) => {
      console.log(a);
      this.route.navigateByUrl('home/tasks')
      this.ngOnInit()
      alert("task added")

    })
  }
  ngOnInit(): void {
    this.getDate()

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

}
