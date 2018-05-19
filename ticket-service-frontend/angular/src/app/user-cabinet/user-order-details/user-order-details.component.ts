import {Component, Input, OnInit} from '@angular/core';
import {OrderService} from "../../order/order.service";
import {Order} from "../../data/Order";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-order-details',
  templateUrl: './user-order-details.component.html',
  styleUrls: ['./user-order-details.component.css']
})
export class UserOrderDetailsComponent implements OnInit {

  constructor(private orderService: OrderService,
              private activatedRoute: ActivatedRoute) {
  }

  order: Order = new Order(0, new Date(), null, []);
  totalPrice: number = 0;

  ngOnInit() {
    this.activatedRoute.params.subscribe((receivedParams) => {
      let params = receivedParams;
      this.orderService.getOrderById(params.orderId).subscribe((receivedOrder: Order) => {
        this.order = receivedOrder;
        this.calculateTotalPrice();
      });
    });
  }

  calculateTotalPrice() {
    for (let ticket of this.order.ticketList) {
      this.totalPrice += ticket.ticketPrice;
    }
  }

}
