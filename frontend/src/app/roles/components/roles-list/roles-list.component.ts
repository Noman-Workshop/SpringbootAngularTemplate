import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { FormBuilder, FormGroup } from '@angular/forms';

import { RoleFilter } from '../../model/role-filter';

import { RoleService } from 'src/app/roles/services/role.service';


@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.less']
})
export class RolesListComponent implements OnInit {

    expandForm = false;

    loading = false;

    criteria = <RoleFilter>{
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
        { name: 'name', show: true }
      ];
    isCollapse = true;

    constructor(
      private roleService: RoleService,
      private message: NzMessageService,
      private fb: FormBuilder
    ) { }

    ngOnInit() {
      this.initializeForm();
      this.load();
    }

    initializeForm() {
      this.validateForm = this.fb.group({
        name: [null]
      });
    }

    load() {
      this.loading = true;

      const payload = this.createPayload();

      this.roleService.getAll(payload).subscribe(
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

      return {
        pagination,
        filters
      }
    }




    delete(id) {
      this.roleService.delete(id).subscribe(
        () => {
          this.load();
          this.message.success('Role deleted successfully');
        },
        error => {
          this.message.error('Failed to delete role', error);
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

    // getStatusColor(status: string) {
    //   switch (status) {
    //     case 'COMPLETED':
    //       return 'green';
    //     case 'DOING':
    //       return 'blue';
    //     case 'PAUSE AND HOLD':
    //       return 'orange';
    //     case 'FAILED':
    //       return 'red';
    //     default:
    //       return 'default';
    //   }
    // }
  }
