import { Component, Input, OnInit } from '@angular/core';
import { Event } from "../../data/Event";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-event-base-info',
  templateUrl: './event-base-info.component.html',
  styleUrls: ['./event-base-info.component.css']
})
export class EventBaseInfoComponent implements OnInit {

  constructor(private router: Router,
              private activeRoute: ActivatedRoute) {
  }

  @Input() selectedEvent: Event;

  ngOnInit() {
  }

  buyTickets() {
    this.router.navigate(['tickets'], {relativeTo: this.activeRoute});
  }
}
