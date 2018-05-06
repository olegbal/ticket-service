import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-place-map',
  templateUrl: './event-place-map.component.html',
  styleUrls: ['./event-place-map.component.css']
})
export class EventPlaceMapComponent implements OnInit {

  constructor() {
  }

  @Input() selectedEvent: Event;

  ngOnInit() {
  }

}
