import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { TicketHelper } from "./tickethelper";
import { TicketCreatorDto } from "../data/TicketCreatorDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class TicketService {

  constructor(private http: HttpClient) {
  }

  getEventTickets(id: number) {
    return this.http.get(TicketHelper.ticketsByEventRequestUrl + id);
  }

  createTickets(ticketCreatorDtos: TicketCreatorDto[], eventId: number) {
    return this.http.post("/api/v1/tickets" + "?eventId=" + eventId, JSON.stringify(ticketCreatorDtos), httpOptions);
  }
}
