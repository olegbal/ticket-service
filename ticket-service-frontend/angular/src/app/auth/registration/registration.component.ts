import {Component, OnInit} from '@angular/core';
import {RegistrationService} from "./registration.service";
import {RegistrationData} from "../../data/RegistrationData";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private registrationService: RegistrationService) {
    this.registrationData = new RegistrationData();
  }

  registrationData:RegistrationData;

  ngOnInit() {
  }

  register(){

  }

}
