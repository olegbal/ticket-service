import { ApiHelper } from "../apiHelper";

export class TicketHelper {
  static ticketsMainUrl = ApiHelper.v1 + "tickets";
  static ticketsByEventRequestUrl = TicketHelper.ticketsMainUrl + "?eventId=";
}
