import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/auth/auth.guard';
import { TodoComponent } from './todo.component';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoAddComponent } from './components/todo-add/todo-add.component';
import { TodoDetailsComponent } from './components/todo-details/todo-details.component';
import { TodoUpdateComponent } from './components/todo-update/todo-update.component';

// const routes: Routes = [
//   {
//     path: '/todo',
//     component: TodoComponent,
//     canActivate: [AuthGuard],
//     children: [
//       {
//         path: '/list',
//         component: TodoListComponent,
//         data: {
//           // authorities: ['ROLE_ADMIN']
//         }
//       },
//     ]
//   }
// ];


const routes: Routes = [
  {
    path: '',
    component: TodoComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'list',
        component: TodoListComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'add',
        component: TodoAddComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'details/:id',
        component: TodoDetailsComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'update/:id',
        component: TodoUpdateComponent,
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
    ]
    // data: { authorities: ['ROLE_USER'] }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TodoRoutingModule { }
