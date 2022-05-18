import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private todo: TodoservicesService) { }
  ngOnInit(): void {
  }
  category = new FormGroup({
    category: new FormControl('')
  })
  get categorys() { return this.category.controls['category'] }
  findByCategory: any;
  tasks: any = [];
  Hightask: any = [];
  submit(data: any) {
    console.log(data);
    console.log(data.category);
    this.findByCategory = data.category
    console.log(this.findByCategory);
    this.todo.gettask().subscribe((data) => {
      this.tasks = data;
      let j = 0
      for (let i = 0; i < this.tasks.length; i++) {
        if (this.tasks[i].category == this.findByCategory) {
          this.Hightask[j] = this.tasks[i]
          j++;
          console.log(this.Hightask[j]);
        }

      }
    })
  }

}
