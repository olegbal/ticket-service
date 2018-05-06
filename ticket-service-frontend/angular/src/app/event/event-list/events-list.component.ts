import { Component, OnInit } from '@angular/core';
import { Event } from "../../data/Event";
import { EventService } from "../event.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})

export class EventsListComponent implements OnInit {

  constructor(private eventListService: EventService,
              private router: Router) {
  }

  rowCount: Array<number>;
  events: Event[];

  ngOnInit() {
    this.eventListService.getEvents().subscribe((result: Event[]) => {
      this.events = result;
      this.calculateRowsCount(result.length);
    });
  }

  calculateRowsCount(itemsCount: number) {
    let finalRowNumber = itemsCount % columnLimit == 0 ? Math.round(itemsCount / columnLimit) : Math.round(itemsCount / columnLimit) + 1;
    this.rowCount = Array(finalRowNumber).fill(0).map((x, i) => i);
  }

  redirectToDetailsPage(id: number) {
    this.router.navigate(["events", id])
  }
}

const columnLimit = 5;

