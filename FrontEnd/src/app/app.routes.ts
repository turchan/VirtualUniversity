import { Routes }            from '@angular/router';
import { AddUserComponent }  from './components/add-user/add-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { MainComponent }     from './components/main/main.component';
import { LoginComponent }    from './components/login/login.component';
import { ShowUserComponent } from './components/show-user/show-user.component';

export const ROUTES: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainComponent },
  { path: 'show-user', component: ShowUserComponent},
  { path: 'add-user', component: AddUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: '', pathMatch: 'full', redirectTo: 'main' },
  { path: '**', pathMatch: 'full', redirectTo: 'main' }
];
