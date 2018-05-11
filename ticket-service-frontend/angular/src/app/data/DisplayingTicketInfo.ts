import { Ticket } from "./Ticket";

export class DisplayingTicketInfo {
  constructor(public amount: number,
              public selectedAmount: number,
              public ticket: Ticket) {
  }
}
