import { TicketType } from "./TicketType";

export class Ticket {
  constructor(public id: number,
              public ticketPrice: number,
              public ticketState: number,
              public ticketType: TicketType) {
  }

}
