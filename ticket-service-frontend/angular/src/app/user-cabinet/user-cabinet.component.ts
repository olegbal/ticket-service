import {Component, OnInit} from '@angular/core';
import {AccountEntryService} from "../header/account-entry/account-entry.service";
import {User} from "../data/User";
import {UserService} from "./user.service";
import {OrderService} from "../order.service";
import {Order} from "../data/Order";

@Component({
  selector: 'app-user-cabinet',
  templateUrl: './user-cabinet.component.html',
  styleUrls: ['./user-cabinet.component.css']
})
export class UserCabinetComponent implements OnInit {

  constructor(private accountEntryService: AccountEntryService,
              private userService: UserService,
              private orderService: OrderService) {
  }

  user: User = new User(0, "", "", "", "", "", "", "", null);
  orders: Order[] = [];
  editingUser: User;
  accountDetailsSelected: boolean = false;
  ordersDetailsSelected: boolean = false;
  eventsDetailsSelected: boolean = false;
  isAdmin: boolean = false;
  isUser: boolean = false;
  isOrganizer: boolean = false;
  accountInfoEditing: boolean = false;
  newPasswordEnabled: boolean = false;

  ngOnInit() {
    this.accountEntryService.checkUserToken().subscribe((receivedUser: User) => {
      this.user = receivedUser;
      this.isAdmin = this.accountEntryService.hasAdminRole;
      this.isOrganizer = this.accountEntryService.hasOrganizerRole;
      this.isUser = this.accountEntryService.hasUserRole;
      this.enableOrders();
    });
  }

  enableAcountInfo() {
    this.ordersDetailsSelected = false;
    this.eventsDetailsSelected = false;
    this.accountDetailsSelected = true;
  }

  enableOrders() {
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = true;
    this.eventsDetailsSelected = false;
    this.orderService.getUsersOrders(this.user.id).subscribe((receivedOrders: Order[]) => {
      this.orders = receivedOrders;
    });
  }

  enableEvents() {
    this.accountDetailsSelected = false;
    this.ordersDetailsSelected = false;
    this.eventsDetailsSelected = true;
  }

  enableAccountInfoEditing() {
    this.accountInfoEditing = true;
    this.editingUser = JSON.parse(JSON.stringify(this.user));
  }

  cancelAccountInfoEditing() {
    this.accountInfoEditing = false;
    this.user = this.editingUser;
  }

  submitUpdate() {
    console.log(this.user);
    this.userService.updateUser(this.user).subscribe((updatedUser: User) => {
      this.user = updatedUser;
      this.accountInfoEditing = false;
    });
  }
}
