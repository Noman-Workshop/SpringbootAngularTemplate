import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

import { TodoService } from 'src/app/todo/services/todo.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-add.component.html',
  styleUrls: ['./todo-add.component.less']
})
export class TodoAddComponent implements OnInit {

  form: FormGroup;
  loading = false;
  submitting = false;
  failed = false;

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private todoService: TodoService,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.form = this.fb.group({
      name: [
        null,
        [
          Validators.required
        ]
      ],
      description: [
        null,
        [
          Validators.maxLength(255)
        ]
      ],
      dueDate: [
        null,
        [
          Validators.required
        ]
      ]
    });
  }

  get name() {
    return this.form.controls.name;
  }

  get description() {
    return this.form.controls.description;
  }

  submit(): void {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.valid) {
      this.loading = true;
      this.failed = false;
      this.todoService.create(this.form.value).subscribe(
        (data) => {
          this.loading = false;
          this.submitting = false;
          this.msg.success('Todo added successfully');
          console.log(data);
          this.form.reset();
          this.router.navigate(['/todo/list']);
        },
        (error) => {
          this.loading = false;
          this.failed = true;
        }
      );
    }
  }

}
