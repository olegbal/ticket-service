import {Injectable} from '@angular/core';
import {Ticket} from "../data/Ticket";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {OrderCreatorDto} from "../data/OrderCreatorDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class OrderService {

  constructor(private http: HttpClient) {
  }

  createOrder(tickets: Array<Ticket>, userId: number) {
    let ticketIds = this.createTicketIdList(tickets);
    let order = new OrderCreatorDto(ticketIds, userId);
    return this.http.post("/api/v1/orders", JSON.stringify(order), httpOptions)
  }

  private createTicketIdList(tickets: Array<Ticket>): Array<number> {

    let ticketIds = [];

    for (let ticket of tickets) {
      ticketIds.push(ticket.id);
    }

    return ticketIds;
  }

  getUsersOrders(userId: number) {
    return this.http.get("/api/v1/orders?userId=" + userId);
  }

  getOrderById(orderId: number) {
    return this.http.get("/api/v1/orders/" + orderId);
  }

}
