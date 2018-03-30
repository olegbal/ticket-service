import { Component, OnInit } from '@angular/core';
import { LoginData } from "../../data/LoginData";
import { LoginService } from "./login.service";
import { User } from "../../data/User";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService) {
    this.loginData = new LoginData("","");
  }

  loginData: LoginData;

  ngOnInit() {
  }

  submitLogin() {
    this.loginService.signIn(this.loginData).subscribe((data: User) => {
      alert("Name :"+ data.login + "LastName :"+ data.email);
    });
  }
}
