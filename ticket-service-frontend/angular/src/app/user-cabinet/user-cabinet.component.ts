import { Component, OnInit } from '@angular/core';
import { AccountEntryService } from "../header/account-entry/account-entry.service";
import { User } from "../data/User";
import { UserService } from "./user.service";
import { OrderService } from "../order/order.service";
import { Order } from "../data/Order";
import { Event } from "../data/Event";
import { EventService } from "../event/event.service";
import { NgbTabChangeEvent, NgbTabsetConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-cabinet',
  templateUrl: './user-cabinet.component.html',
  styleUrls: ['./user-cabinet.component.css'],
  providers: [NgbTabsetConfig]
})
export class UserCabinetComponent implements OnInit {

  constructor(private accountEntryService: AccountEntryService,
              private userService: UserService,
              private orderService: OrderService,
              private eventService: EventService,
              tabSetConfig: NgbTabsetConfig) {
    tabSetConfig.justify = "fill";
  }

  user: User = new User(0, "", "", "", "", "", "", "", null);
  orders: Order[] = [];
  organizedEvents: Event[] = [];
  unapprovedEvents: Event[] = [];
  isAdmin: boolean = false;
  isUser: boolean = false;
  isOrganizer: boolean = false;

  ngOnInit() {
    this.accountEntryService.checkUserToken().subscribe((receivedUser: User) => {
      this.user = receivedUser;
      this.isAdmin = this.accountEntryService.hasAdminRole;
      this.isOrganizer = this.accountEntryService.hasOrganizerRole;
      this.isUser = this.accountEntryService.hasUserRole;
    });
  }

  selectMethodOnChange($event: NgbTabChangeEvent) {
    switch ($event.nextId) {
      case "user-orders": {
        this.enableOrders();
        break;
      }
      case "organizer-events-tab": {
        this.enableApprovedEvents();
        break;
      }
      case "organizer-unapproved-tabs": {
        this.enableUnapprovedEvents();
        break;
      }
      case "event-editor-tab": {
        break;
      }
      case "admin-requests-tab": {
        this.enableRequests();
        break;
      }
    }
  }

  enableOrders() {
    this.orderService.getUsersOrders(this.user.id).subscribe((receivedOrders: Order[]) => {
      this.orders = receivedOrders;
    });
  }

  enableApprovedEvents() {
    this.eventService.getEventsByUserId(this.user.id, true).subscribe((receivedEvents: Event[]) => {
      this.organizedEvents = receivedEvents;
    });
  }

  enableUnapprovedEvents() {
    this.eventService.getEventsByUserId(this.user.id, false).subscribe((receivedEvents: Event[]) => {
      this.unapprovedEvents = receivedEvents;
    });
  }

  enableRequests() {
    this.eventService.getEventsByApproved(false).subscribe((receivedEvents: Event[]) => {
      this.unapprovedEvents = receivedEvents;
    });
  }
}
