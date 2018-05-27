import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { EventHelper } from "./eventhelper";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class EventService {

  constructor(private http: HttpClient) {

  }

  getEvents() {
    return this.http.get(EventHelper.apiEventUrl);
  }

  getEvents(approved: boolean){
    return this.http.get(EventHelper.apiEventUrl + "?approved="+ approved);
  }

  getEvent(id: number) {
    return this.http.get(EventHelper.apiEventDetails + id);
  }

  getEventsByUserId(userId:number){
    return this.http.get(EventHelper.apiEventsByUserId + userId);
  }

  updateEvent(event: Event) {
    return this.http.put(EventHelper.apiEventUrl, JSON.stringify(event), httpOptions);
  }

}
