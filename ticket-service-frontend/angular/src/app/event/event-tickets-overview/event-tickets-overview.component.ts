import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-buying-menu',
  templateUrl: './event-ticket-overview.component.html',
  styleUrls: ['./event-tickets-overview.component.css']
})
export class EventTicketsOverviewComponent implements OnInit, OnDestroy {

  constructor() {
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    console.log("destroyed");
  }

}
