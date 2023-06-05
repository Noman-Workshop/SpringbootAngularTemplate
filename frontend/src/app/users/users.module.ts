import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { UsersRoutingModule } from './users-routing.module';
import { UserListComponent } from './components/users-list/users-list.component';
import { UsersComponent } from './users.component';


@NgModule({
  declarations: [
    UserListComponent,
    UsersComponent
  ],
  imports: [
    SharedModule,
    UsersRoutingModule
  ]
})
export class UsersModule { }
