import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../data/User";
import {UserService} from "../user.service";

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(private userService: UserService) {
  }

  @Input() user: User;
  editingUser: User;
  accountInfoEditing: boolean = false;
  newPasswordEnabled: boolean = false;

  ngOnInit() {
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
