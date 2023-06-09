import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './core/auth/auth.guard';
//
import { LayoutComponent } from './layout/layout.component';
import { ForbiddenComponent } from './page/forbidden/forbidden.component';
import { NotFoundComponent } from './page/not-found/not-found.component';
import { ErrorComponent } from './page/error/error.component';
import { LoginComponent } from './login/login.component';
import { SelectivePreloadingStrategy } from './core/preloading-strategy/selective-preloading-strategy';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/todo/list',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
      data: {
        title: 'Login'
      }
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent,
    data: {
      title: 'Forbidden'
    }
  },
  {
    path: 'error',
    component: ErrorComponent,
    data: {
      title: 'Error'
    }
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'dashboard',
        loadChildren: './dashboard/dashboard.module#DashboardModule',
        canLoad: [AuthGuard],
        data: {
          preload: true,
          // authorities: ['ROLE_USER']
        }
      },
      {
        path: 'users',
        loadChildren: './user/user.module#UserModule',
        canLoad: [AuthGuard],
        data: {
          // authorities: ['ROLE_ADMIN']
        }
      },
      {
        path: 'todo',
        loadChildren: './todo/todo.module#TodoModule',
        canLoad: [AuthGuard],
        data: {
        }
      },
    ],
    canActivateChild: [AuthGuard],
    data: {
      // authorities: ['ROLE_USER']
    }
  },
  {
    path: '**',
    component: NotFoundComponent,
    data: {
      title: 'Not Found'
    }
  }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes,
      {
        //enableTracing: true, // <-- debugging purposes only
        preloadingStrategy: SelectivePreloadingStrategy
      })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
