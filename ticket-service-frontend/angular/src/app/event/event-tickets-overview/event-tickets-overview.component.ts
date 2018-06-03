import { Component, OnInit } from '@angular/core';
import { TicketService } from "../../ticket/ticket.service";
import { Ticket } from "../../data/Ticket";
import { ActivatedRoute } from "@angular/router";
import { DisplayingTicketInfo } from "../../data/DisplayingTicketInfo";
import { OrderService } from "../../order/order.service";
import { AccountEntryService } from "../../header/account-entry/account-entry.service";

@Component({
  selector: 'app-event-buying-menu',
  templateUrl: './event-ticket-overview.component.html',
  styleUrls: ['./event-tickets-overview.component.css']
})
export class EventTicketsOverviewComponent implements OnInit {

  constructor(private ticketService: TicketService,
              private activatedRoute: ActivatedRoute,
              private orderService: OrderService,
              private accountEntryService: AccountEntryService) {
  }

  tickets: Ticket[];
  ticketsInfo: DisplayingTicketInfo[] = [];
  selectedTickets: DisplayingTicketInfo[] = [];


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
      this.ticketsInfo.push(new DisplayingTicketInfo(amountOfTickets, 0, ticket));
    }
    this.selectedTickets = JSON.parse(JSON.stringify(this.ticketsInfo));
  }

  incrementAmount(ticketDisplayInfo: DisplayingTicketInfo) {
    ticketDisplayInfo.selectedAmount++;
  }

  decrementAmount(ticketDisplayInfo: DisplayingTicketInfo) {
    if (ticketDisplayInfo.selectedAmount != 0) ticketDisplayInfo.selectedAmount--;
  }

  addToSelected() {
    let ticketInfoList = this.ticketsInfo.filter(x => x.selectedAmount > 0);

    for (let i = 0; i < ticketInfoList.length; i++) {
      let ticketInfo = this.selectedTickets.find(x => x.ticket.id == ticketInfoList[i].ticket.id);

      if (ticketInfo) {
        ticketInfo.selectedAmount += ticketInfoList[i].selectedAmount;
        ticketInfoList[i].amount -= ticketInfoList[i].selectedAmount;
      }

    }
    ticketInfoList.forEach(item => item.selectedAmount = 0);
  }

  hasSelectedTickets(): boolean {
    return this.selectedTickets.filter(x => x.selectedAmount > 0).length > 0;
  }

  prepareOrder(orderingTickets: DisplayingTicketInfo[]) {
    //FIXME select EXACT  tickets. System selects tickets randomly for now.
    let orderingTicketList = [];
    for (let oneTicketInfo of orderingTickets) {
      let freeTicketsOfType = this.tickets.filter(x => x.ticketState == 0 && x.ticketType.id === oneTicketInfo.ticket.ticketType.id);
      for (let i = 0; i < oneTicketInfo.selectedAmount; i++) {
        orderingTicketList.push(freeTicketsOfType.pop());
      }
    }
    this.orderService.createOrder(orderingTicketList, this.accountEntryService.loggedUser.id).subscribe(() => {
      this.updateDataAfterOrder();
    });
  }

  updateDataAfterOrder() {
    this.ticketsInfo = [];
    this.selectedTickets = [];
    this.activatedRoute.parent.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.ticketService.getEventTickets(params.id).subscribe((tickets: Ticket[]) => {
        this.tickets = tickets;
        this.prepareForDisplaying();
      });
    });
  }

  removeSelection(displayingTicketInfo: DisplayingTicketInfo) {
    let returningAmountTicket = this.ticketsInfo.find(x => x.ticket.id == displayingTicketInfo.ticket.id);
    returningAmountTicket.amount += displayingTicketInfo.selectedAmount;
    returningAmountTicket.selectedAmount = 0;
    displayingTicketInfo.selectedAmount = 0;
  }
}
