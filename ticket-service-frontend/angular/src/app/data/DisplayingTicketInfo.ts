import { TicketType } from "./TicketType";

export class DisplayingTicketInfo {
  constructor(public amount: number,
              public ticketPrice: number,
              public ticketType: TicketType) {
  }

}
