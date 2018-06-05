import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Order} from "../../data/Order";
import {OrderService} from "../../order/order.service";

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent implements OnInit {

  constructor(private router: Router,
              private orderService: OrderService) {
  }

  @Input() orders: Order[];
  orderSelected: boolean = false;
  selectedOrder = new Order(0, new Date(Date.now()), null, []);
  page = 1;

  ngOnInit() {
  }

  selectOrder(order: Order) {
    if (order.id != 0) this.orderSelected = true;
    this.selectedOrder = order;

  }

}
