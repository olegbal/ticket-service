import {User} from "./User";
import {Ticket} from "./Ticket";

export class Order {
  constructor(public id: number,
              public orderDate: Date,
              public user: User,
              public ticketList: Ticket[]) {
  }
}
