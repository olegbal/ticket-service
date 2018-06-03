import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-requests',
  templateUrl: './admin-requests.component.html',
  styleUrls: ['./admin-requests.component.css']
})
export class AdminRequestsComponent implements OnInit {

  constructor() {
  }

  @Input() requests: Event[];
  requestSelected: boolean = false;
  page = 1;

  ngOnInit() {
  }

}
