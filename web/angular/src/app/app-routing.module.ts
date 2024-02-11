import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {ProfileComponent} from "./profile/profile.component";
import {UserComponent} from "./user/user.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'user',
    component: UserComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}

