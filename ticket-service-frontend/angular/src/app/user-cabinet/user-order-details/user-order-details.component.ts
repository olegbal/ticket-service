import { Component, Input, OnInit } from '@angular/core';
import { Order } from "../../data/Order";

@Component({
  selector: 'app-user-order-details',
  templateUrl: './user-order-details.component.html',
  styleUrls: ['./user-order-details.component.css']
})
export class UserOrderDetailsComponent implements OnInit {

  constructor() {
  }

  @Input() order: Order;
  totalPrice: number = 0;

  ngOnInit() {
    this.calculateTotalPrice();
  }

  calculateTotalPrice() {
    for (let ticket of this.order.ticketList) {
      this.totalPrice += ticket.ticketPrice;
    }
  }

}
