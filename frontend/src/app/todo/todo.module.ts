import { NgModule } from '@angular/core';

import { TodoRoutingModule } from './todo-routing.module';
import { SharedModule } from '../shared/shared.module';
import { TodoComponent } from './todo.component';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoAddComponent } from './components/todo-add/todo-add.component';
import { TodoDetailsComponent } from './components/todo-details/todo-details.component';
import { TodoUpdateComponent } from './components/todo-update/todo-update.component';

@NgModule({
  imports: [
    SharedModule,
    TodoRoutingModule
  ],
  declarations: [TodoComponent, TodoListComponent, TodoAddComponent, TodoDetailsComponent, TodoUpdateComponent]
})
export class TodoModule { }
