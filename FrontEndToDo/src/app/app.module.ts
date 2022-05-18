import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import {MatSelectModule} from '@angular/material/select';

import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import { TaskviewComponent } from './taskview/taskview.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { LandingpageComponent } from './landingpage/landingpage.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ArchiveComponent } from './archive/archive.component';
import {MatBadgeModule} from '@angular/material/badge';
import { NotificationComponent } from './notification/notification.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { TaskdetailsComponent } from './taskdetails/taskdetails.component';
import {MatTabsModule} from '@angular/material/tabs';
import { Dialogbox1Component } from './dialogbox1/dialogbox1.component';
import { Dialogbox2Component } from './dialogbox2/dialogbox2.component';
import { HightaskComponent } from './hightask/hightask.component';
import { MediumtaskComponent } from './mediumtask/mediumtask.component';
import { LowtaskComponent } from './lowtask/lowtask.component';
import { CategoryComponent } from './category/category.component';
import { SnackbarComponent } from './snackbar/snackbar.component';
import {MatSnackBarModule} from '@angular/material/snack-bar'
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
   
    LoginComponent,
    RegisterComponent,
    TaskviewComponent,
    LandingpageComponent,
    ArchiveComponent,
    NotificationComponent,
    EditprofileComponent,
    TaskdetailsComponent,
    Dialogbox1Component,
    Dialogbox2Component,
    HightaskComponent,
    MediumtaskComponent,
    LowtaskComponent,
    CategoryComponent,
    SnackbarComponent
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatCardModule,
    HttpClientModule,
    MatSelectModule,
    MatTableModule,
    MatDialogModule,
    MatTooltipModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatBadgeModule,
    MatTabsModule,
    MatSnackBarModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
