import { TicketType } from "./TicketType";

export class TicketCreatorDto {
  constructor(public ticketType: TicketType,
              public amountOfTickets: number,
              public price: number) {
  }
}
