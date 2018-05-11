import { Injectable } from '@angular/core';
import { Ticket } from "./data/Ticket";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Order } from "./data/Order";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class OrderService {

  constructor(private http: HttpClient) {
  }

  createOrder(tickets: Array<Ticket>, userId: number) {
    let ticketIds = this.createTicketIdList(tickets);
    let order = new Order(ticketIds, userId);
    return this.http.post("/api/v1/orders", JSON.stringify(order), httpOptions)
  }

  private createTicketIdList(tickets: Array<Ticket>): Array<number> {

    let ticketIds = [];

    for (let ticket of tickets) {
      ticketIds.push(ticket.id);
    }

    return ticketIds;
  }

}
