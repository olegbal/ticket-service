import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RegistrationData} from "../../data/RegistrationData";
import {AuthHelper} from "../authhelper";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class RegistrationService {

  constructor(private http: HttpClient) {
  }

  register(registrationData: RegistrationData) {
    return this.http.post(AuthHelper.registrationUrl, JSON.stringify(registrationData), httpOptions)
  }

}
