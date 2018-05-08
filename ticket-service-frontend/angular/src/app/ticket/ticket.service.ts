import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { TicketHelper } from "./tickethelper";

@Injectable()
export class TicketService {

  constructor(private http: HttpClient) {
  }

  getEventTickets(id: number) {
    return this.http.get(TicketHelper.ticketsByEventRequestUrl + id);
  }

}
