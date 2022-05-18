import { Component, OnInit } from '@angular/core';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-lowtask',
  templateUrl: './lowtask.component.html',
  styleUrls: ['./lowtask.component.css']
})
export class LowtaskComponent implements OnInit {

  constructor(private todo: TodoservicesService) { }

  tasks: any = []
  Hightask: any = []
  ngOnInit(): void {
    this.todo.gettask().subscribe((data) => {
      this.tasks = data;
      let j = 0
      for (let i = 0; i < this.tasks.length; i++) {
        if (this.tasks[i].priority == "LOW") {
          this.Hightask[j] = this.tasks[i]
          j++;
          console.log(this.Hightask[i]);
        }

      }
    })
  }

}
