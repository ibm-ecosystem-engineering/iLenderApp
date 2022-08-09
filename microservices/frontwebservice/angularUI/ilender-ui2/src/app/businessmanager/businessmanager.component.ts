import { UtilityService } from "./../services/utility.service";
import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation, Renderer, ElementRef, ViewChild, OnDestroy } from "@angular/core";
import wealthManagersJson from "src/assets/MOCK_DATA_wealthmanagers.json";
import customersJson from "src/assets/MOCK_DATA_customer.json";

import { CustomerService } from "./../services/customer.service";
import { WealthmanagerService } from "./../services/wealthmanager.service";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: "wealthcare-business-manager",
  templateUrl: "./businessmanager.component.html",
  styleUrls: ["./businessmanager.component.scss"]
})

export class BusinessmanagerComponent implements OnInit {
  wealthManagersList: any;
  customersList: any;
  addUserEnabled: boolean = false;
  wmList: any;
  cuList: any;
  role: string;
  state: string;
  tabClick: boolean;
  wm: string = 'wm';
  cs: string = 'cs';
  selectedIndex: number;
  userInfo:any = {userDisplayName: "", userDisplayRole : ""};

  constructor(private utility: UtilityService, private activatedRoute: ActivatedRoute, private customerService: CustomerService, private wealthmanagerService: WealthmanagerService) {

    this.activatedRoute.queryParams.subscribe(params => {
      // this.userInfo = params;
    });
    
    this.loadWealthManager();
    this.loadCustomer();
  }

  onClickedOutside() {
    console.log("Clicked outside:");
  }

  ngOnInit() {
    this.wmList = [];
    this.cuList = [];
    this.utility.currentState.subscribe(state => (this.state = state));
    this.utility.chooseTab.subscribe(tab => {
      this.tabClick = tab;
      console.log(this.tabClick);
    });

    this.userInfo.userDisplayName =  sessionStorage.getItem("userDisplayName");
    this.userInfo.userDisplayRole =  sessionStorage.getItem("userDisplayRole");
    
    this.loadWealthManager();
    this.loadCustomer();
  }

  addUser(val: string) {
    this.addUserEnabled = true;
    this.utility.addUser(true);
    this.role = val;
  }

  addBlockToggle(event: boolean) {
    this.addUserEnabled = event;
  }

  selectAddTab(val) {
    this.selectedIndex = val;
  }

  public loadWealthManager() {
    this.wealthmanagerService.getAll().subscribe((data: {}) => {
      this.wmList = data;
    });
  }

  public loadCustomer() {
    this.customerService.getAll().subscribe((data: {}) => {
      this.cuList = data;
    });
  }
}
