import { Injectable, OnInit } from '@angular/core';
import { Role } from "../../data/Role";
import { User } from "../../data/User";
import { CookieService } from "ngx-cookie-service";
import { AuthHelper } from "../../auth/authhelper";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class AccountEntryService implements OnInit {

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router) {
    this.ngOnInit();
  }

  ngOnInit(): void {
    this.isLoggedIn = this.cookieService.check(AuthHelper.authCookie);
    if (this.isLoggedIn) {
      this.checkUserToken().subscribe((user: User) => {
        this.loggedUser = user;
        if (this.loggedUser) {
          this.checkRoles(this.loggedUser.roles);
        }
      });
    }
  }


  isLoggedIn = false;
  loggedUser: User = null;
  hasAdminRole: boolean = false;
  hasUserRole: boolean = false;
  hasOrganizerRole: boolean = false;

  setLoggedIn(user: User) {
    this.isLoggedIn = true;
    this.loggedUser = user;
    this.checkRoles(this.loggedUser.roles);
    this.router.navigate(['/cabinet']);
  }

  setLogout() {
    this.isLoggedIn = false;
    this.loggedUser = null;
    this.hasAdminRole = false;
    this.hasUserRole = false;
    this.hasOrganizerRole = false;
    this.cookieService.delete(AuthHelper.authCookie, "/");
    this.cookieService.delete(AuthHelper.authCookie, "/api/v1/");
  }

  checkUserToken() {
    return this.http.get("api/v1/check-auth");
  }

  checkRoles(roles: Role[]) {
    for (let role of roles) {
      if (role.roleName.includes('USER')) {
        this.hasUserRole = true;
      }
      if (role.roleName.includes('ORGANIZER')) {
        this.hasOrganizerRole = true;
      }
      if (role.roleName.includes('ADMIN')) {
        this.hasAdminRole = true;
      }
    }
  }
}
