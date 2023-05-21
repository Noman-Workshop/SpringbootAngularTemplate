import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder,FormGroup,Validators} from '@angular/forms';

import { AuthService } from '../core/auth/auth.service';
import { StateStorageService } from '../core/auth/state-storage.service';
import { SettingsService } from '../core/settings/settings.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  failed = false;
  loader = false;

  constructor(private router: Router,
              private formBuilder: FormBuilder,
              private authService: AuthService,
              private stateStorageService: StateStorageService,
              public settings: SettingsService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  get email() { return this.form.controls.email; }

  get password() { return this.form.controls.password; }

  submitForm(): void {
    for (const i in this.form.controls) {
      this.form.controls[ i ].markAsDirty();
      this.form.controls[ i ].updateValueAndValidity();
    }
    if (this.form.valid) {
      this.loader = true;
      this.failed = false;
      this.authService.login(this.form.value).subscribe(
        (data) => {
          this.loader = false;
          const redirect = this.stateStorageService.getUrl();
          if (redirect) {
            this.stateStorageService.storeUrl(null);
            this.router.navigate([redirect]);
          } else {
            this.router.navigate(['']);
          }
        },
        (error) => {
          this.loader = false;
          this.failed = true;
        }
      );
    }
  }

}
