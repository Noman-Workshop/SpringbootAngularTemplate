import { NgModule } from '@angular/core';


import { RolesRoutingModule } from './roles-routing.module';
import { SharedModule } from '../shared/shared.module';
import { RolesComponent } from './roles.component';
import { RoleAddComponent } from './components/role-add/role-add.component';
import { AssignRoleComponent } from './components/assign-role/assign-role.component';
import { RolesListComponent } from './components/roles-list/roles-list.component';
import { RoleUpdateComponent } from './components/role-update/role-update.component';


@NgModule({
  declarations: [
    RolesComponent,
    RoleAddComponent,
    AssignRoleComponent,
    RolesListComponent,
    RoleUpdateComponent
  ],
  imports: [
    SharedModule,
    RolesRoutingModule
  ]
})
export class RolesModule { }
