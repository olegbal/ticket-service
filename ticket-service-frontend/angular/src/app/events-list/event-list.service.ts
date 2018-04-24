import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { ApiHelper } from "../apiHelper";

@Injectable()
export class EventListService {

  constructor(private http: HttpClient) {

  }

  getEvents() {
    return this.http.get(ApiHelper.v1 + "events");
  }

  getEvent(id: number) {
    return this.http.get(ApiHelper.v1 + "events/" + id);
  }
}
