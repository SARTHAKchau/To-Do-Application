import { Component, OnInit } from '@angular/core';
import { LandingpageComponent } from '../landingpage/landingpage.component';

import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {


  tasks: any = {}
  notifications: any = [];
  total: any = 0;
  date = new Date();
  results: any = []

  count: any = 0;
  constructor(private todo: TodoservicesService, private landing: LandingpageComponent) {
    this.todo.gettask().subscribe(task => {
      this.tasks = task;
      console.log(this.tasks);

      console.log(this.todo.notificationCount);
      let j = 0;

      for (let i = 0; i < this.tasks.length; i++) {
        const a = this.tasks[i];


        let currentDate = new Date().getDate();
        console.log(currentDate);

        let lastDate = (a.endDate.substring(8));
        let noOfDays: number = lastDate - currentDate;
        console.log(noOfDays);


        if (noOfDays > 0 && noOfDays <= 2) {
          this.todo.getNotification(2).subscribe((res) => {

            this.results[j] = res;
            this.results[j].title = a.title
            j++

            console.log(this.results);
            this.count++;
            console.log(this.count);
            this.todo.notificationCount = this.count
            console.log(this.todo.notificationCount);
            sessionStorage.setItem("count", this.count)

          }, (err) => {
            console.log("Something went wrong");
          })
        }
      }
    })
  }
  ngOnInit(): void {
    console.log(this.todo.notificationCount);

  }


}

