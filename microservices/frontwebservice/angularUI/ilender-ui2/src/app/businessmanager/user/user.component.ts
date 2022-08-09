import { UtilityService } from './../../services/utility.service';
import { Component, ElementRef, ViewChild, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";

@Component({
  selector: "wealthcare-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.scss"]
})
export class UserComponent implements OnInit {
  @Input() userDetails: [];
  @Input() typeData: string;
  editThisUser: any;
  editThisUserType: any;
  deleteThisUser: any;
  editUserEnabled: boolean = false;
  deleteUserEnabled: boolean = false;
  deleteThisUserType: any;
  addUserEnabled: boolean;
  assignIsCalled: boolean = false;
  collabModalToggle: boolean = false;
  wmChoosen: any;

  constructor(private utility: UtilityService) {}

  ngOnInit() {
    this.utility.currentState.subscribe(state => this.addUserEnabled = state);
  }

  editUser(user: any, action: string) {
    this.editThisUser = user;
    this.editUserEnabled = true;
    this.editThisUserType = action;

    console.log('Edit :',this.editThisUser, action);
  }

  deleteUser(user: any, action: string) {
    this.deleteThisUser = user;
    this.deleteUserEnabled = true;
    this.deleteThisUserType = action;

    console.log('Delete :',this.deleteThisUser, action);
  }

  editBlockToggle(event: boolean) {
    this.editUserEnabled = event;
    this.deleteUserEnabled = event;
  }

  addBlockToggle(event: boolean) {
    this.addUserEnabled = event;
  }

  assignWealthManager(user: any, action: string) {
    this.utility.chooseTabFn(0);
    this.assignIsCalled = true;
    console.log(user, action);
    this.wmChoosen = user;
  }

  getMessage(message: boolean) {
    this.assignIsCalled = message;
  }
}