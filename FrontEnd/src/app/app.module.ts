import { BrowserModule }                                                                                      from '@angular/platform-browser';
import { NgModule }                                                                                           from '@angular/core';
import { AppComponent }                                                                                       from './app.component';
import { EditUserComponent }                                                                                  from './components/user/edit-user/edit-user.component';
import { ListUserComponent }                                                                                  from './components/user/list-user/list-user.component';
import { BrowserAnimationsModule }                                                                            from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule }                                                                   from '@angular/forms';
import {HttpClientModule}                                                                                     from '@angular/common/http';
import {RouterModule}                                                                                         from '@angular/router';
import {ROUTES}                                                                                               from './app.routes';
import { MzSelectModule }                                                                                     from 'ngx-materialize';
import { MatSelectModule, MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { MainNavComponent }                                                                                   from './main-nav/main-nav.component';
import { LayoutModule }                                                                                       from '@angular/cdk/layout';
import { MainComponent }                                                                                      from './components/main/main.component';
import { LoginComponent }                                                                                     from './components/user/login/login.component';
import { httpInterceptorProviders }                                                                           from './auth/auth-interceptor';
import { ShowUserComponent }                                                                                  from './components/user/show-user/show-user.component';
import { CreateUserComponent }      from './components/user/create-user/create-user.component';
import { CreateAdminComponent }     from './components/user/create-admin/create-admin.component';
import { CreateProfessorComponent } from './components/user/create-professor/create-professor.component';
import { ListCourseComponent }      from './components/course/list-course/list-course.component';
import { ShowCourseComponent } from './components/course/show-course/show-course.component';
import { EditCourseComponent }       from './components/course/edit-course/edit-course.component';
import { CreateCourseComponent }     from './components/course/create-course/create-course.component';
import { AddUserCourseComponent }   from './components/course/add-user-course/add-user-course.component';
import { AddMarksCourseComponent }  from './components/course/add-marks-course/add-marks-course.component';
import { ShowMarksCourseComponent } from './components/course/show-marks-course/show-marks-course.component';
import { FormUploadComponent }      from './upload/form-upload/form-upload.component';
import { ListUploadComponent }      from './upload/list-upload/list-upload.component';
import { DetailsUploadComponent }          from './upload/details-upload/details-upload.component';
import { AddMaterialComponent }            from './components/course/add-material/add-material.component';
import { UploadFileService }               from './services/upload-file.service';
import { ShowProfessorsCoursesComponent }  from './components/user/show-professors-courses/show-professors-courses.component';
import { ShowParticipantsCourseComponent } from './components/course/show-participants-course/show-participants-course.component';
import { ShowUsersCoursesComponent }       from './components/user/show-users-courses/show-users-courses.component';
import { ShowUsersMarksComponent }         from './components/course/show-users-marks/show-users-marks.component';
import { SearchUserComponent }             from './components/user/search-user/search-user.component';
import { SearchCourseComponent } from './components/course/search-course/search-course.component';
import { UsersForAdminComponent } from './components/user/users-for-admin/users-for-admin.component';



@NgModule({
  declarations: [
    AppComponent,
    EditUserComponent,
    ListUserComponent,
    MainNavComponent,
    MainComponent,
    LoginComponent,
    ShowUserComponent,
    CreateUserComponent,
    CreateAdminComponent,
    CreateProfessorComponent,
    ListCourseComponent,
    ShowCourseComponent,
    EditCourseComponent,
    CreateCourseComponent,
    AddUserCourseComponent,
    AddMarksCourseComponent,
    ShowMarksCourseComponent,
    FormUploadComponent,
    ListUploadComponent,
    DetailsUploadComponent,
    AddMaterialComponent,
    ShowProfessorsCoursesComponent,
    ShowParticipantsCourseComponent,
    ShowUsersCoursesComponent,
    ShowUsersMarksComponent,
    SearchUserComponent,
    SearchCourseComponent,
    UsersForAdminComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot( ROUTES, { useHash: true } ),
    MzSelectModule,
    MatSelectModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [
    httpInterceptorProviders,
    UploadFileService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
