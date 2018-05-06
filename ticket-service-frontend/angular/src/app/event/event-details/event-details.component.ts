import { Component, OnInit } from '@angular/core';
import { EventService } from "../event.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Event } from "../../data/Event";
import { EventPlace } from "../../data/EventPlace";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  constructor(private eventService: EventService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  event: Event = new Event(0, "", null, "", new EventPlace(0, "", ""), 0, 0);

  ngOnInit() {
    this.activatedRoute.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.eventService.getEvent(params.id).subscribe((event: Event) => {
        this.event = event;
      });
    });

    this.router.navigate(['overview'], {relativeTo: this.activatedRoute})
  }

}
