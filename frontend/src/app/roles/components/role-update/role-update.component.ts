import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

import { RoleService } from 'src/app/roles/services/role.service';
import { Roles } from '../../model/roles';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-role-update',
  templateUrl: './role-update.component.html',
  styleUrls: ['./role-update.component.less']
})
export class RoleUpdateComponent implements OnInit {


  form: FormGroup;
  loading = false;
  submitting = false;

  roles: Roles;
  subscription: Subscription;

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private roleService: RoleService,
    private route: ActivatedRoute,private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [ '', [Validators.required]]
    });
    this.subscription = this.route.params.subscribe(params => {
      const id = +params['id'];
      this.load(id);
    });
  }

  load(id: number) {
    this.loading = true;
    this.roleService.get(id).subscribe(
      ({ data }) => {
        this.roles = data;
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
      const data = Object.assign({}, this.roles, this.form.value);
      this.roleService.update(data).subscribe(
        (data) => {
          this.loading = false;
          this.submitting = false;
          this.msg.success(data.message);
          this.router.navigate(['/roles/list']);
        },
        (error) => {
          this.loading = false;
          this.msg.error(error.error.message);
        }
      );
    }
  }
}
