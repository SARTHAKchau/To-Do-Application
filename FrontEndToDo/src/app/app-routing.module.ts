import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArchiveComponent } from './archive/archive.component';
import { CategoryComponent } from './category/category.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { Guard1Guard } from './guard1/guard1.guard';
import { HightaskComponent } from './hightask/hightask.component';
import { LandingpageComponent } from './landingpage/landingpage.component';

import { LoginComponent } from './login/login.component';
import { LowtaskComponent } from './lowtask/lowtask.component';
import { MediumtaskComponent } from './mediumtask/mediumtask.component';
import { NotificationComponent } from './notification/notification.component';
import { RegisterComponent } from './register/register.component';
import { TaskviewComponent } from './taskview/taskview.component';


const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "home",
   // canActivate:[Guard1Guard],
    component: LandingpageComponent,
    children:[
      {
        path:"tasks",
        component: TaskviewComponent
      },
      {
        path:"archive",
        component: ArchiveComponent
      },
      {
        path:"notification",
        component:NotificationComponent
      },
      {
        path:"editprofile",
        component:EditprofileComponent
      },
      {
        path:"hightask",
        component:HightaskComponent
      },
      {
        path:"lowtask",
        component:LowtaskComponent
      },
      {
        path:"mediumtask",
        component:MediumtaskComponent
      },
      {
        path:"category",
        component:CategoryComponent
      }
    ]
  },
  
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full"
  }



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
