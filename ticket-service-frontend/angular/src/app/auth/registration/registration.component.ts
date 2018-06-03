import { Component, OnInit } from '@angular/core';
import { RegistrationService } from "./registration.service";
import { RegistrationData } from "../../data/RegistrationData";
import { AccountEntryService } from "../../header/account-entry/account-entry.service";
import { Router } from "@angular/router";
import { User } from "../../data/User";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private registrationService: RegistrationService,
              private accountEntryService: AccountEntryService,
              private router: Router) {
    this.registrationData = new RegistrationData("", "", "", "", "", "", "", "");
  }

  registrationData: RegistrationData;
  registerSpecialAccount: boolean = false;

  ngOnInit() {
  }

  register() {
    this.registrationService.register(this.registrationData).subscribe((receivedUser: User) => {
        this.accountEntryService.setLoggedIn(receivedUser);
        this.router.navigate(["/cabinet"]);
      },
      error => {
        //todo add toaster
        alert("registraton failed");
      });
  }

}
