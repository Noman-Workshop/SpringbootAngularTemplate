import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

import { TodoService } from 'src/app/todo/services/todo.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TodoFilter } from '../../model/todo-filter';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.less']
})
export class TodoListComponent implements OnInit {

  expandForm = false;

  loading = false;

  criteria = <TodoFilter>{
    pagination: {
      page: 1,
      size: 5
    },
    filters: {
    }
  };

  sorts = {
    key: 'id', value: 'descend'
  }

  data = {
    content: [],
    page: 0,
    size: 0,
    totalItems: 0,
    totalPages: 0
  };

  validateForm!: FormGroup;
  collapsableFields: Array<
    { name: string, show: boolean }> = [
      { name: 'name', show: true },
      { name: 'status', show: true },
      { name: 'dueDate', show: false },
      { name: 'isImportant', show: false }
    ];
  isCollapse = true;

  constructor(
    private todoService: TodoService,
    private message: NzMessageService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.initializeForm();
    this.load();
  }

  initializeForm() {
    this.validateForm = this.fb.group({
      name: [null],
      dueDate: [null],
      status: [null],
      isImportant: [null]
    });
  }

  load() {
    this.loading = true;

    const payload = this.createPayload();

    this.todoService.getAll(payload).subscribe(
      data => {
        this.loading = false;
        this.data = data.data
      },
      error => {
        this.loading = false;
        this.message.error('Failed to load data.', error);
      }
    );
  }

  // TODO: Refactor this method
  sort({ sort }) {
    sort.map(item => {
      if (item.value) {
        this.sorts = item;
      }
    });
    this.load();
  }
  createPayload() {
    const pagination = Object.assign({}, this.criteria.pagination);
    pagination.page = pagination.page > 0 ? pagination.page : 1;
    pagination.size = pagination.size > 0 ? pagination.size : 5;

    pagination.sorts = [`${this.sorts.key},${this.sorts.value === 'ascend' ? 'asc' : 'desc'}`];

    const filters = Object.assign({}, this.criteria.filters);
    filters.name = this.validateForm.controls.name.value ?? null;
    filters.status = this.validateForm.controls.status.value !== '' ? this.validateForm.controls.status.value : null;
    filters.dueDate = this.validateForm.controls.dueDate.value ? new Date(this.validateForm.controls.dueDate.value.toDateString()) : null;
    const isImportant = this.validateForm.controls.isImportant.value;
    filters.isImportant = isImportant !== '' && isImportant !== null ? (isImportant === 'true') : null;

    return {
      pagination,
      filters
    }
  }

  updateImportance(todo) {
    todo.isImportant = !todo.isImportant;
    this.todoService.update(todo).subscribe(
      () => {
        this.load();
        this.message.success('Todo updated successfully');
      },
      (error) => {
        this.message.error('Failed to update todo');
      }
    );
  }

  updateStatus(todo) {
    if (todo.status !== 'COMPLETED') {
      todo.status = 'COMPLETED';
    } else {
      todo.status = 'TODO';
    }
    this.todoService.update(todo).subscribe(
      () => {
        this.load();
        this.message.success('Todo updated successfully');
      },
      (error) => {
        this.message.error('Failed to update todo');
      }
    );
  }

  delete(id) {
    this.todoService.delete(id).subscribe(
      () => {
        this.load();
        this.message.success('Todo deleted successfully');
      },
      error => {
        this.message.error('Failed to delete todo', error);
      }
    );
  }

  expandSet = new Set<number>();
  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

  toggleCollapse(): void {
    this.isCollapse = !this.isCollapse;
    this.collapsableFields.forEach((field, index) => {
      field.show = this.isCollapse ? index < 2 : true;
    });
  }

  isCollapsedField(name: string): boolean {
    return this.collapsableFields.find(field => field.name === name).show;
  }

  resetForm(): void {
    this.validateForm.reset();
  }

  showMsg(msg: string) {
    this.message.info(msg);
  }

  getStatusColor(status: string) {
    switch (status) {
      case 'COMPLETED':
        return 'green';
      case 'DOING':
        return 'blue';
      case 'PAUSE AND HOLD':
        return 'orange';
      case 'FAILED':
        return 'red';
      default:
        return 'default';
    }
  }
}
