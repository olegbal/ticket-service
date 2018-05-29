import {Component, OnInit} from '@angular/core';
import {AccountEntryService} from "../header/account-entry/account-entry.service";
import {User} from "../data/User";
import {UserService} from "./user.service";
import {OrderService} from "../order/order.service";
import {Order} from "../data/Order";
import {Event} from "../data/Event";
import {EventService} from "../event/event.service";

@Component({
  selector: 'app-user-cabinet',
  templateUrl: './user-cabinet.component.html',
  styleUrls: ['./user-cabinet.component.css']
})
export class UserCabinetComponent implements OnInit {

  constructor(private accountEntryService: AccountEntryService,
              private userService: UserService,
              private orderService: OrderService,
              private eventService: EventService) {
  }

  user: User = new User(0, "", "", "", "", "", "", "", null);
  orders: Order[] = [];
  organizedEvents: Event[] = [];
  unapprovedEvents: Event[] = [];
  accountDetailsSelected: boolean = false;
  ordersDetailsSelected: boolean = false;
  organizedEventsSelected: boolean = false;
  requestsDetailsSelected: boolean = false;
  unorganizedEventsSelected: boolean = false;
  isAdmin: boolean = false;
  isUser: boolean = false;
  isOrganizer: boolean = false;

  ngOnInit() {
    this.accountEntryService.checkUserToken().subscribe((receivedUser: User) => {
      this.user = receivedUser;
      this.isAdmin = this.accountEntryService.hasAdminRole;
      this.isOrganizer = this.accountEntryService.hasOrganizerRole;
      this.isUser = this.accountEntryService.hasUserRole;
      this.enableAccountInfo();
    });
  }

  enableAccountInfo() {
    this.accountDetailsSelected = true;
    this.ordersDetailsSelected = false;
    this.organizedEventsSelected = false;
    this.requestsDetailsSelected = false;
    this.unorganizedEventsSelected = false;
  }

  enableOrders() {
    this.ordersDetailsSelected = true;
    this.accountDetailsSelected = false;
    this.organizedEventsSelected = false;
    this.requestsDetailsSelected = false;
    this.unorganizedEventsSelected = false;
    this.orderService.getUsersOrders(this.user.id).subscribe((receivedOrders: Order[]) => {
      this.orders = receivedOrders;
    });
  }

  enableApprovedEvents() {
    this.organizedEventsSelected = true;
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = false;
    this.requestsDetailsSelected = false;
    this.unorganizedEventsSelected = false;
    this.eventService.getEventsByUserId(this.user.id, true).subscribe((receivedEvents: Event[]) => {
      this.organizedEvents = receivedEvents;
    });
  }

  enableUnapprovedEvents() {
    this.unorganizedEventsSelected = true;
    this.organizedEventsSelected = false;
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = false;
    this.requestsDetailsSelected = false;
    this.eventService.getEventsByUserId(this.user.id, false).subscribe((receivedEvents: Event[]) => {
      this.unapprovedEvents = receivedEvents;
    });
  }

  enableRequests() {
    this.requestsDetailsSelected = true;
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = false;
    this.organizedEventsSelected = false;
    this.unorganizedEventsSelected = false;
    this.eventService.getEventsByApproved(false).subscribe((receivedEvents: Event[]) => {
      this.unapprovedEvents = receivedEvents;
    });
  }
}
