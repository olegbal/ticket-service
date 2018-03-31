import {Component, OnInit} from '@angular/core';
import {RegistrationService} from "./registration.service";
import {RegistrationData} from "../../data/RegistrationData";
import {User} from "../../data/User";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private registrationService: RegistrationService) {
    this.registrationData = new RegistrationData("", "", "", "", "", "", "", "");
  }

  registrationData: RegistrationData;

  ngOnInit() {
  }

  register() {
    this.registrationService.register(this.registrationData).subscribe((user: User) => {
      alert("User "+ user.login + " Email "+ user.email)
    });
  }

}
