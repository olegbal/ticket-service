import { Component, OnInit } from '@angular/core';
import { LoginData } from "../../data/LoginData";
import { LoginService } from "./login.service";
import { User } from "../../data/User";
import { AccountEntryService } from "../account-entry/account-entry.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService,
              private accountEntryService: AccountEntryService) {
    this.loginData = new LoginData("", "");
  }

  loginData: LoginData;

  ngOnInit() {
  }

  submitLogin() {
    this.loginService.signIn(this.loginData).subscribe((data: User) => {
      this.accountEntryService.setLoggedIn(data);
    });
  }
}
