import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users.component';
import { AuthGuard } from '../core/auth/auth.guard';
import { UserListComponent } from './components/users-list/users-list.component';


const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'list',
        component: UserListComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
