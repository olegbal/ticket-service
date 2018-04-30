import { Injectable } from '@angular/core';
import { LoginData } from "../../data/LoginData";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthHelper } from "../authhelper";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  signIn(login: LoginData) {
    return this.http.post(AuthHelper.apiLoginUrl, JSON.stringify(login), httpOptions)
  }

}
