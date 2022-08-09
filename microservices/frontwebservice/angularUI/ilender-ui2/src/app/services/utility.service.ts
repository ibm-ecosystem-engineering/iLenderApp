import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import wealthManagersJson from "src/assets/MOCK_DATA_wealthmanagers.json";

@Injectable({
  providedIn: "root"
})
export class UtilityService {
  selectedUser: string;
  wealthManagersList: any;
  public clickedToState: Subject<any> = new Subject();
  public selectedUserAdd: Subject<any> = new Subject();
  public tabClickRequired: Subject<any> = new Subject();
  currentState = this.clickedToState.asObservable();
  clickedUser = this.selectedUserAdd.asObservable();
  chooseTab = this.tabClickRequired.asObservable();

  constructor() {
    this.wealthManagersList = wealthManagersJson;
  }

  addUser(state: boolean) {
    this.clickedToState.next(state);
  }

  chooseTabFn(tab: number) {
    this.tabClickRequired.next(tab);
  }

  selectedTabsAddUser(selectedUser: string) {
    console.log(this.selectedUser = selectedUser);
    this.selectedUserAdd.next(selectedUser);
  }
}
