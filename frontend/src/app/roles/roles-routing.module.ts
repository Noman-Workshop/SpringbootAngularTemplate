import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RolesComponent } from './roles.component';
import { AuthGuard } from '../core/auth/auth.guard';
import { RoleAddComponent } from './components/role-add/role-add.component';
import { RolesListComponent } from './components/roles-list/roles-list.component';
import { RoleUpdateComponent } from './components/role-update/role-update.component';

const routes: Routes = [
  {
    path: '',
    component: RolesComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'add',
        component: RoleAddComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'list',
        component: RolesListComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'update/:id',
        component: RoleUpdateComponent,
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


export class RolesRoutingModule { }
