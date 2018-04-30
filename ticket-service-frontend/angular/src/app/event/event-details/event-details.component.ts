import { Component, OnInit } from '@angular/core';
import { EventService } from "../event.service";
import { ActivatedRoute } from "@angular/router";
import { Event } from "../../data/Event";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  constructor(private eventService: EventService,
              private activatedRoute: ActivatedRoute) {
  }

  event: Event;

  ngOnInit() {
    this.activatedRoute.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.eventService.getEvent(params.id).subscribe((event: Event) => {
        this.event = event;
      });
    });
  }

}
