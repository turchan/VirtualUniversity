import { Routes }                   from '@angular/router';
import { EditUserComponent }        from './components/user/edit-user/edit-user.component';
import { ListUserComponent }        from './components/user/list-user/list-user.component';
import { MainComponent }            from './components/main/main.component';
import { LoginComponent }           from './components/user/login/login.component';
import { ShowUserComponent }        from './components/user/show-user/show-user.component';
import { CreateUserComponent }      from './components/user/create-user/create-user.component';
import { CreateAdminComponent }     from './components/user/create-admin/create-admin.component';
import { CreateProfessorComponent } from './components/user/create-professor/create-professor.component';
import { ListCourseComponent }     from './components/course/list-course/list-course.component';
import { ShowCourseComponent }      from './components/course/show-course/show-course.component';
import { EditCourseComponent }      from './components/course/edit-course/edit-course.component';
import { CreateCourseComponent }          from './components/course/create-course/create-course.component';
import { AddUserCourseComponent }          from './components/course/add-user-course/add-user-course.component';
import { AddMarksCourseComponent }         from './components/course/add-marks-course/add-marks-course.component';
import { ShowMarksCourseComponent }        from './components/course/show-marks-course/show-marks-course.component';
import { AddMaterialComponent }            from './components/course/add-material/add-material.component';
import { ShowProfessorsCoursesComponent }  from './components/user/show-professors-courses/show-professors-courses.component';
import { ShowParticipantsCourseComponent } from './components/course/show-participants-course/show-participants-course.component';
import { ShowUsersCoursesComponent } from './components/user/show-users-courses/show-users-courses.component';
import { ShowUsersMarksComponent }   from './components/course/show-users-marks/show-users-marks.component';
import { SearchUserComponent }       from './components/user/search-user/search-user.component';
import { SearchCourseComponent }     from './components/course/search-course/search-course.component';
import { UsersForAdminComponent }    from './components/user/users-for-admin/users-for-admin.component';

export const ROUTES: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainComponent },
  { path: 'show-user', component: ShowUserComponent },
  { path: 'create-user', component: CreateUserComponent },
  { path: 'create-admin', component: CreateAdminComponent},
  { path: 'create-professor', component: CreateProfessorComponent},
  { path: 'edit-user', component: EditUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'list-course', component: ListCourseComponent},
  { path: 'show-course', component: ShowCourseComponent},
  { path: 'edit-course', component: EditCourseComponent},
  { path: 'create-course', component: CreateCourseComponent},
  { path: 'addUser-course', component: AddUserCourseComponent},
  { path: 'addMark-course', component: AddMarksCourseComponent},
  { path: 'showMark-course', component: ShowMarksCourseComponent},
  { path: 'addMaterial-course', component: AddMaterialComponent},
  { path: 'show-professors-courses', component: ShowProfessorsCoursesComponent},
  { path: 'show-participants-course', component: ShowParticipantsCourseComponent},
  { path: 'show-users-courses', component: ShowUsersCoursesComponent },
  { path: 'show-users-marks', component: ShowUsersMarksComponent },
  { path: 'search-user', component: SearchUserComponent },
  { path: 'search-course', component: SearchCourseComponent },
  { path: 'search-users', component: UsersForAdminComponent },
  { path: '', pathMatch: 'full', redirectTo: 'main' },
  { path: '**', pathMatch: 'full', redirectTo: 'main' }
];
