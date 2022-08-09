import { UtilityService } from './../services/utility.service';
import { Component, ElementRef, ViewChild, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";

@Component({
  selector: "goal",
  templateUrl: "./goal.component.html",
  styleUrls: ["./goal.component.scss"]
})
export class GoalComponent implements OnInit {
  @Input() goalsList: [];
  editThisUser: any;
  deleteThisUser: any;
  editDeleteUserEnabled: boolean = false;
  deleteUserEnabled: boolean = false;

  addUserEnabled: boolean;

  constructor(private utility: UtilityService) { }

  ngOnInit() {
    this.utility.currentState.subscribe(state => this.addUserEnabled = state);
  }

  editUser(user: any, action: string) {
    this.editThisUser = user;
    this.editDeleteUserEnabled = true;
    console.log('Edit :', this.editThisUser, action);
  }

  deleteUser(user: any, action: string) {
    this.deleteThisUser = user;
    this.editDeleteUserEnabled = true;
    console.log('Delete :', this.deleteThisUser, action);
  }

  editBlockToggle(event: boolean) {
    console.log(event);
    this.editDeleteUserEnabled = event;
  }

  addBlockToggle(event: boolean) {
    console.log(event);
    this.addUserEnabled = event;
  }
}