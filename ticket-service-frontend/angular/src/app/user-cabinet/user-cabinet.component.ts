import {Component, OnInit} from '@angular/core';
import {AccountEntryService} from "../header/account-entry/account-entry.service";
import {User} from "../data/User";

@Component({
  selector: 'app-user-cabinet',
  templateUrl: './user-cabinet.component.html',
  styleUrls: ['./user-cabinet.component.css']
})
export class UserCabinetComponent implements OnInit {

  constructor(private accountEntryService: AccountEntryService) {
  }

  user: User = new User(0, "", "", "", "", "", "", "", null);
  accountDetailsSelected: boolean = false;
  ordersDetailsSelected: boolean = false;
  eventsDetailsSelected: boolean = false;
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

  enableAcountInfo() {
    this.accountDetailsSelected = true;
    this.ordersDetailsSelected = false;
    this.eventsDetailsSelected = false;
  }

  enableOrders() {
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = true;
    this.eventsDetailsSelected = false;
  }

  enableEvents() {
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = false;
    this.eventsDetailsSelected = true;
  }
}
