import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ManageUserComponent} from './manage-user/manage-user.component';


const userRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'manage-user',
    component: ManageUserComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule {}
