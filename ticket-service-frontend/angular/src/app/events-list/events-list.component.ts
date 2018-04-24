import { Component, OnInit } from '@angular/core';
import { Event } from "../data/Event";
import { EventListService } from "./event-list.service";

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})

export class EventsListComponent implements OnInit {

  constructor(private eventListService: EventListService) {
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
    let finalRowNumber = itemsCount % columnLimit == 0 ? itemsCount / columnLimit : itemsCount / columnLimit + 1;
    this.rowCount = Array(finalRowNumber).fill(0).map((x, i) => i);
  }

}

const columnLimit = 5;

