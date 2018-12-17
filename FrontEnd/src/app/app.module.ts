import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AddUserComponent }        from './components/add-user/add-user.component';
import { EditUserComponent }       from './components/edit-user/edit-user.component';
import { ListUserComponent }       from './components/list-user/list-user.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ReactiveFormsModule}                                                                                  from '@angular/forms';
import {HttpClientModule}                                                                                     from '@angular/common/http';
import {RouterModule}                                                                                         from '@angular/router';
import {ROUTES}                                                                                               from './app.routes';
import { MzSelectModule }                                                                                     from 'ngx-materialize';
import { MatSelectModule, MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { MainNavComponent }                                                                                   from './main-nav/main-nav.component';
import { LayoutModule }                                                                                       from '@angular/cdk/layout';
import { MainComponent }                                                                                      from './components/main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    AddUserComponent,
    EditUserComponent,
    ListUserComponent,
    MainNavComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
