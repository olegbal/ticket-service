import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Order} from "../../data/Order";

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent implements OnInit {

  constructor(private router: Router) {
  }

  @Input() orders: Order[];

  ngOnInit() {
  }

  enableOrderDetails(orderId: number) {
    this.router.navigate(["/cabinet/orders", orderId]);
  }

}
