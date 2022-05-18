import { Component, OnInit } from '@angular/core';
import { TodoservicesService } from '../services/todoservices.service';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit {

  constructor(private todo: TodoservicesService) { }

  archiveTasks: any = []

  ngOnInit(): void {
    this.todo.getArchive().subscribe((data) => {
      this.archiveTasks = data
    })
  }

  delete(id: any) {
    this.todo.deleteArchive(id).subscribe((res) => {
      console.log("deleted");
      this.ngOnInit()

    }, (err) => {

      this.ngOnInit()
    })
  }
  moveToHome(data: any) {
    this.todo.savetask(data).subscribe(() => {
      alert("Moved back to Task")
      this.ngOnInit()
    })
  }


}
