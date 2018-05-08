import { Component, OnInit } from '@angular/core';
import { TicketService } from "../../ticket/ticket.service";
import { Ticket } from "../../data/Ticket";
import { ActivatedRoute } from "@angular/router";
import { DisplayingTicketInfo } from "../../data/DisplayingTicketInfo";

@Component({
  selector: 'app-event-buying-menu',
  templateUrl: './event-ticket-overview.component.html',
  styleUrls: ['./event-tickets-overview.component.css']
})
export class EventTicketsOverviewComponent implements OnInit {

  constructor(private ticketService: TicketService,
              private activatedRoute: ActivatedRoute) {
  }

  tickets: Ticket[];
  ticketsInfo = [];


  ngOnInit() {
    this.activatedRoute.parent.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.ticketService.getEventTickets(params.id).subscribe((tickets: Ticket[]) => {
        this.tickets = tickets;
        this.prepareForDisplaying();
      });
    });
  }


  prepareForDisplaying() {
    let availableTickets = this.tickets.filter(x => x.ticketState == 0);
    let uniqTickets = [];
    for (let i = 0; i < availableTickets.length; i++) {
      if (uniqTickets.find(x => x.ticketType.id == availableTickets[i].ticketType.id)) {
        continue;
      }
      else {
        uniqTickets.push(availableTickets[i]);
      }
    }
    for (let i = 0; i < uniqTickets.length; i++) {
      let ticket = uniqTickets[i];
      let amountOfTickets = availableTickets.filter(x => x.ticketType.id == ticket.ticketType.id).length;
      this.ticketsInfo.push(new DisplayingTicketInfo(amountOfTickets, ticket.ticketPrice, ticket.ticketType));
    }
  }

}
