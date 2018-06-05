import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from "../data/User";
import { ChangePasswordDto } from "../data/ChangePasswordDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  getOrganizerInfo(eventId: number) {
    return this.http.get("/api/v1/users?eventId=" + eventId);
  }

  updateUser(user: User) {
    return this.http.put("/api/v1/users", JSON.stringify(user), httpOptions)
  }

  changePassword(changePasswordDto: ChangePasswordDto) {
    return this.http.put("/api/v1/users/change-password", JSON.stringify(changePasswordDto), httpOptions);
  }

}
