import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { EventHelper } from "./eventhelper";

@Injectable()
export class EventService {

  constructor(private http: HttpClient) {

  }

  getEvents() {
    return this.http.get(EventHelper.apiEventUrl);
  }

  getEvent(id: number) {
    return this.http.get(EventHelper.apiEventDetails + id);
  }
}
