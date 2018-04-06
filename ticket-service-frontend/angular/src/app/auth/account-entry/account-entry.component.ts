import { Component, OnInit } from '@angular/core';
import { AccountEntryService } from "./account-entry.service";

@Component({
  selector: 'app-account-entry',
  templateUrl: './account-entry.component.html',
  styleUrls: ['./account-entry.component.css']
})
export class AccountEntryComponent implements OnInit {

  constructor(private accountEntryService: AccountEntryService) {
  }

  ngOnInit() {
  }

}
