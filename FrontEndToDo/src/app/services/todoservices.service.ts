import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoservicesService {
  username: any
  notificationCount: number = 0;
  dataOfTask: any
  constructor(private httpClient: HttpClient) { }

  register(data: any) {
    console.log("posting " + data);
    console.log("inside post method");
    return this.httpClient.post(`http://localhost:8083/api/v1/register`, data);
  }


  loginUser(data: any) {
    console.log("posting " + data);
    console.log("inside post method");
    this.username = data.username;
    localStorage.setItem("username", this.username)
    this.username = localStorage.getItem("username")
    console.log(this.username);
    return this.httpClient.post(`http://localhost:8083/api/v1/login`, data);
  }

  savetask(data: any) {
    console.log("inisde save task method");
    console.log(data);
    const url2 = `http://localhost:8083/api/v1/tasks/` + localStorage.getItem("username")
    let header = new HttpHeaders().set(
      "Authorization", "Bearer " + localStorage.getItem("token"))
    console.log(header);
    return this.httpClient.post(url2, data, { headers: header })
  }
  gettask() {
    console.log("inside get method");
    let header = new HttpHeaders().set(
      "Authorization", "Bearer " + localStorage.getItem("token"))
    const url3 = `http://localhost:8083/api/v1/tasks/` + localStorage.getItem("username")
    return this.httpClient.get(url3, { headers: header })
  }
  delete(id: any) {
    const url4 = `http://localhost:8083/api/v1/tasks/` + localStorage.getItem("username") + `/` + id
    console.log(url4);
    let header = new HttpHeaders().set(
      "Authorization", "Bearer " + localStorage.getItem("token"))
    return this.httpClient.delete(url4, { headers: header })
  }

  updateTask(data: any, id: any) {
    console.log("updating");

    const url5 = `http://localhost:8083/api/v1/task/` + localStorage.getItem("username") + `/` + id
    console.log(url5);
    console.log("updated");
    return this.httpClient.put<any>(url5, data)

  }

  getTaskById(id: any) {
    const url6 = `http://localhost:8083/api/v1/task/` + localStorage.getItem("username") + `/` + id
    console.log(url6);
    return this.httpClient.get<any>(url6)

  }

  getArchive() {
    return this.httpClient.get<any>(`http://localhost:8083/api/v1/archive/` + localStorage.getItem("username"))
  }

  moveToArchive(data: any) {
    return this.httpClient.post<any>(`http://localhost:8083/api/v1/archiveTask/` + localStorage.getItem("username"), data)
  }

  deleteArchive(id: any) {
    return this.httpClient.delete<any>(`http://localhost:8083/api/v1/archive/` + localStorage.getItem("username") + `/` + id)
  }

  getNotification(id: any) {
    return this.httpClient.get(`http://localhost:8083/api/v1/notifications/` + id)
  }
  uploadImage(data: any) {
    return this.httpClient.post<any>(`http://localhost:8085/api/image/addImage/` + localStorage.getItem("username"), data)
  }
  getImage() {
    return this.httpClient.get<any>(`http://localhost:8085/api/image/getImages/` + localStorage.getItem("username"))
  }
  pendingTasks() {
    return this.httpClient.get<any>(`http://localhost:8083/api/v1/pending/` + localStorage.getItem("username"))
  }
  completedTasks() {
    return this.httpClient.get<any>(`http://localhost:8083/api/v1/completed/` + localStorage.getItem("username"))
  }
  markComplete(id: any) {
    return this.httpClient.put<any>(`http://localhost:8083/api/v1/mark-complete/` + localStorage.getItem("username") + `/` + id, '')
  }
  getUser() {
    return this.httpClient.get(`http://localhost:8081/api/user/users/` + localStorage.getItem("username"))
  }
  updateUser(data: any) {
    return this.httpClient.post(`http://localhost:8083/api/v1/register`, data);
  }
}
