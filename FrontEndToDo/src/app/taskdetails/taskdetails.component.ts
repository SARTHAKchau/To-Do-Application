import { Component, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inject } from '@angular/core';


@Component({
  selector: 'app-taskdetails',
  templateUrl: './taskdetails.component.html',
  styleUrls: ['./taskdetails.component.css']
})
export class TaskdetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public taskData: any) { }

  ngOnInit(): void {
    console.log(this.taskData);

  }

}
