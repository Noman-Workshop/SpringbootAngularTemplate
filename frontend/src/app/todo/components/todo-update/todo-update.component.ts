import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

import { TodoService } from 'src/app/todo/services/todo.service';
import { Todo } from '../../model/todo';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-todo-update',
  templateUrl: './todo-update.component.html',
  styleUrls: ['./todo-update.component.less']
})
export class TodoUpdateComponent implements OnInit {

  form: FormGroup;
  loading = false;
  submitting = false;

  todo: Todo;
  subscription: Subscription;

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private todoService: TodoService,
    private route: ActivatedRoute,private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [ '', [Validators.required]],
      description: ['',[Validators.maxLength(255)]],
      dueDate: [ '',[Validators.required]],
      status: ['',[Validators.required]]
    });
    this.subscription = this.route.params.subscribe(params => {
      const id = +params['id'];
      this.load(id);
    });
  }

  load(id: number) {
    this.loading = true;
    this.todoService.get(id).subscribe(
      ({ data }) => {
        this.todo = data;
        this.form.patchValue(Object.assign({}, data));
        this.loading = false;
      },
      (error) => {
        this.loading = false;
        this.msg.error(error.error.message);
      }
    );
  }

  submit(): void {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.valid) {
      this.loading = true;
      const data = Object.assign({}, this.todo, this.form.value);
      this.todoService.update(data).subscribe(
        (data) => {
          this.loading = false;
          this.submitting = false;
          this.msg.success(data.message);
          this.router.navigate(['/todo/list']);
        },
        (error) => {
          this.loading = false;
          this.msg.error(error.error.message);
        }
      );
    }
  }
}
