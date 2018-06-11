import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable()
export class EventPlaceService {

  constructor(private http: HttpClient) {
  }

  public getAllPlaces() {
    return this.http.get("/api/v1/event-places");
  }

}
