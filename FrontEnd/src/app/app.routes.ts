import { Routes }            from '@angular/router';
import { AddUserComponent }  from './components/add-user/add-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { MainComponent }     from './components/main/main.component';

export const ROUTES: Routes = [
  { path: 'main', component: MainComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: '', pathMatch: 'full', redirectTo: 'main' },
  { path: '**', pathMatch: 'full', redirectTo: 'main' }
];
