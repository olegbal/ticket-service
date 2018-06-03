import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-organizer-events',
  templateUrl: './organizer-events.component.html',
  styleUrls: ['./organizer-events.component.css']
})
export class OrganizerEventsComponent implements OnInit {

  constructor(private router: Router) {
  }

  @Input() events: Event[];
  eventSelected: boolean = false;
  page = 1;

  ngOnInit() {
  }

  enableEventDetails(id: number) {
    this.router.navigate(["/events/", id])
  }

}
