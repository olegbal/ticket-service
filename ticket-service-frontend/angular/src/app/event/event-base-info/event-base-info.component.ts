import { Component, Input, OnInit } from '@angular/core';
import { Event } from "../../data/Event";
import { ActivatedRoute, Router } from "@angular/router";
import { EventService } from "../event.service";
import { AccountEntryService } from "../../header/account-entry/account-entry.service";

@Component({
  selector: 'app-event-base-info',
  templateUrl: './event-base-info.component.html',
  styleUrls: ['./event-base-info.component.css']
})
export class EventBaseInfoComponent implements OnInit {

  constructor(private router: Router,
              private activeRoute: ActivatedRoute,
              private eventService: EventService,
              private accountEntryService: AccountEntryService) {
  }

  @Input() selectedEvent: Event;

  ngOnInit() {
  }

  buyTickets() {
    this.router.navigate(['tickets'], {relativeTo: this.activeRoute});
  }

  approveEvent() {

    let event = JSON.parse(JSON.stringify(this.selectedEvent));
    event.approved = true;

    this.eventService.updateEvent(event).subscribe((updatedEvent: Event) => {
      this.selectedEvent = updatedEvent;
    });
  }
}
