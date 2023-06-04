import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

import {Router} from "@angular/router";
import { RoleService } from '../../services/role.service';

@Component({
  selector: 'app-role-add',
  templateUrl: './role-add.component.html',
  styleUrls: ['./role-add.component.less']
})
export class RoleAddComponent implements OnInit {

  form: FormGroup;
  loading = false;
  submitting = false;
  failed = false;

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private roleService: RoleService,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.form = this.fb.group({
      name: [
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

  submit(): void {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.valid) {
      this.loading = true;
      this.failed = false;
      this.roleService.create(this.form.value).subscribe(
        (data) => {
          this.loading = false;
          this.submitting = false;
          this.msg.success('Role added successfully');
          console.log(data);
          this.form.reset();
          this.router.navigate(['/roles/list']);
        },
        (error) => {
          this.loading = false;
          this.failed = true;
        }
      );
    }
  }

}
