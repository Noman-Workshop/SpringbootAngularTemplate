import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

import { TodoService } from 'src/app/todo/services/todo.service';
import { Todo } from '../../model/todo';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ApiResponse } from 'src/app/core/interface/api.response';

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-details.component.html',
  styleUrls: ['./todo-details.component.less']
})
export class TodoDetailsComponent implements OnInit {

  loading = false;
  failed = false;

  todo: Todo;
  subscription: Subscription;

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private todoService: TodoService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loading = true;
    this.subscription = this.route.params.subscribe(params => {
      const id = params['id'];
      this.load(id);
    });

  }

  load(id: number) {
    this.todoService.get(id).subscribe(
      ({data}) => {
        this.todo = data;
        this.loading = false;
      },
      (error) => {
        this.loading = false;
        this.failed = true;
        this.msg.error(error.message);
      }
    );
  }

  get name() {
    return this.todo.name;
  }

  get description() {
    return this.todo.description;
  }

}
