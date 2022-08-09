import { UtilityService } from "../services/utility.service";
import { Component, OnInit } from "@angular/core";
import goalDetails from 'src/assets/goalDetails.json';
import customersList from 'src/assets/wealth_manager_custmers.json';
import { GoalService } from '../services/goal.service';
import { CustomerService } from '../services/customer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: "wealthcare-customer",
  templateUrl: "./customer.component.html",
  styleUrls: ["./customer.component.scss"]
})
export class CustomerComponent implements OnInit {
  loanList: any;
  addUserEnabled: boolean = false;
  goals: any;
  customerDetails: any;
  state: string;
  customerId : any;
  userInfo:any = {userDisplayName: "", userDisplayRole : ""};

  public columnLoanHistory: any;
  public rowsLoanHistory: any;
  public columnTransHistory: any;
  public rowsTransHistory: any;

  constructor(private utility: UtilityService, private activatedRoute: ActivatedRoute, private customerService: CustomerService, private goalService: GoalService) {
    this.columnLoanHistory = ['S.No', 'Date', 'Bank', 'Purpose', 'Loan Amount', 'Paid Amount', 'Date Closed'];
    this.columnTransHistory = ['Date', 'Particulars', 'Withdrawl', 'Deposit', 'Balance'];

    this.activatedRoute.queryParams.subscribe(params => {
      // this.userInfo = params;
    });
  }

  ngOnInit() {
    //this.goals = this.goalstList;
    // Test
    this.customerDetails = {};
    this.loanList = {};

    this.utility.currentState.subscribe(state => (this.state = state));

    this.customerId = sessionStorage.getItem('userDisplayId')
    this.loadCustomerById(this.customerId);
    this.loadGoalsByCustomerId(this.customerId);

    this.userInfo.userDisplayName =  sessionStorage.getItem("userDisplayName");
    this.userInfo.userDisplayRole =  sessionStorage.getItem("userDisplayRole");

  }

  addUser() {
    this.addUserEnabled = true;
    this.utility.addUser(true);
  }

  public loadCustomerById(id) {
    this.customerService.getById(id).subscribe((data: any) => {
      this.customerDetails = data;
    });
  }
  public loadGoalsByCustomerId(id) {
    this.loanList = [];
    this.rowsLoanHistory = [];
    this.rowsTransHistory = [];

    this.goalService.getAllInfoByCustomerId(id).subscribe((data: any) => {
      console.log ("loadLoansByCustomerId .....The data ......." + data);
      this.loanList = data;

      this.goalService.getAllAndHistoryInfoByCustomerId(id).subscribe((data: any) => {
        console.log ("getAllAndHistoryInfoByCustomerId .....The data ......." + data);
        this.rowsTransHistory = data[1];
        this.rowsLoanHistory = data[2];
      });
    });
  }
}



