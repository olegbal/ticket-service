import {Component, OnInit} from '@angular/core';
import {EventService} from "../event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Event} from "../../data/Event";
import {EventPlace} from "../../data/EventPlace";
import {User} from "../../data/User";
import {UserService} from "../../user-cabinet/user.service";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  constructor(private eventService: EventService,
              private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private router: Router) {
  }

  event: Event = new Event(0, "", null, "", new EventPlace(0, "", ""), 0, 0, true);
  organizer: User = new User(0, "", "", "", "", "", "", "", []);

  ngOnInit() {
    this.activatedRoute.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.eventService.getEvent(params.id).subscribe((event: Event) => {
        this.event = event;
      });
      this.userService.getOrganizerInfo(params.id).subscribe((receivedUser: User) => {
        this.organizer = receivedUser;
      });
    });


    // this.router.navigate(['overview'], {relativeTo: this.activatedRoute})
  }

}
