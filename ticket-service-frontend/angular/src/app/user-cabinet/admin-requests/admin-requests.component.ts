import { Component, Input, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-requests',
  templateUrl: './admin-requests.component.html',
  styleUrls: ['./admin-requests.component.css']
})
export class AdminRequestsComponent implements OnInit {

  constructor(private router: Router) {
  }

  @Input() requests: Event[];
  page = 1;

  ngOnInit() {
  }

  enableRequestDetails(id: number) {
    this.router.navigate(["/events/", id])
  }

}
