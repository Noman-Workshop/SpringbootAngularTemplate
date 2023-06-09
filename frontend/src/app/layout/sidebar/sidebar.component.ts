import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { SettingsService } from '../../core/settings/settings.service';
import { HttpClient } from '@angular/common/http';

import { AuthService } from '../../core/auth/auth.service';
import { Router } from '@angular/router';
import { User } from '../../core/user/user';
import { Subscription } from 'rxjs';

interface MenuItem {
  id: number;
  name: string;
  url: string;
  position: number;
  featureId: number | null;
  isActive: boolean;
  parentId: number | null;
  subMenus: MenuItem[];
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.less'],
  encapsulation: ViewEncapsulation.None
})

export class SidebarComponent implements OnInit {
  menus: MenuItem[] = [];
  private hasFetchedMenu: boolean = false;

  principal: User;
  private principalChange: Subscription;
  
  constructor(private http: HttpClient, private router: Router,private authService: AuthService) {
    this.principalChange = authService.authenticationState.subscribe((principal) => {
      this.principal = principal;
    });
  }
  // constructor(public settings: SettingsService,
  //             public messageService: NzMessageService) {
  // }

  ngOnInit() {
    this.authService.getPrincipal().subscribe(
      (principal) => {
        this.principal = principal;
        this.fetchMenu();
      }
    );

    
  }

  fetchMenu() {
    const userId = +this.userId;
    this.http.post('api/menu/view', userId).subscribe((response) => {
      this.menus = response.data.menu.map((menu: any) => {
        return {
          id: menu.id,
          name: menu.name,
          url: menu.url,
          position: menu.position,
          featureId: menu.featureId,
          isActive: menu.activeUrl,
          parentId: menu.parentId,
          subMenus: this.mapSubMenus(menu.submenus)
        };
        
      });

      this.menus.sort((a, b) => a.position - b.position); // Sort the menus array

      this.menus.forEach((menu) => {
        menu.subMenus.sort((a, b) => a.position - b.position); // Sort the subMenus array for each menu
        menu.subMenus.forEach((subMenu) => {
          subMenu.subMenus.sort((a, b) => a.position - b.position); // Sort the subMenus array for each subMenu
        });
      });
    });
  } 



  //to map submenus
  mapSubMenus(submenus: any[]): MenuItem[] {
    return submenus.map((subMenu: any) => {
      return {
        id: subMenu.id,
        name: subMenu.name,
        url: subMenu.url,
        position: subMenu.position,
        featureId: subMenu.featureId,
        isActive: subMenu.activeUrl,
        parentId: subMenu.parentId,
        subMenus: this.mapSubMenus(subMenu.submenus) // Recursively map subMenus of subMenus
      };
    });
  }

  get userId(): string {
    if (this.principal) {
      return `${(this.principal.id)}`
    } else {
      return 'User';
    }
  }


}0
