import { Component, OnInit, Input, ViewChild } from '@angular/core';
import customerJSON from 'src/assets/wealth_manager_custmers.json';
import goalDetails from 'src/assets/goalDetails.json';
import rows from 'src/assets/tablerows.json';
import { CustomerService } from '../services/customer.service';
import { GoalService } from '../services/goal.service';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Color, BaseChartDirective, Label } from "ng2-charts";
import * as pluginAnnotations from "chartjs-plugin-annotation";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-wealth-manager',
  templateUrl: './wealth-manager.component.html',
  styleUrls: ['./wealth-manager.component.scss']
})
export class WealthManagerComponent implements OnInit {

  @Input() customersGoalList;

  public customersList: any;
  public customerDetails:any;
  public loanList:any;
  public financialEnabled: boolean;
  public columnLoanHistory: any;
  public rowsLoanHistory: any;
  public columnTransHistory: any;
  public rowsTransHistory: any;

  userInfo:any = {userDisplayName: "", userDisplayRole : ""};

  constructor(private activatedRoute: ActivatedRoute, private customerService: CustomerService, private goalService: GoalService,) { 
    this.columnLoanHistory = ['S.No', 'Date', 'Bank', 'Purpose', 'Loan Amount', 'Paid Amount', 'Date Closed'];
    this.columnTransHistory = ['Date', 'Particulars', 'Withdrawl', 'Deposit', 'Balance'];

    // this.rowsForTable = rows;
    this.activatedRoute.queryParams.subscribe(params => {
      //this.userInfo = params;
    });
  }

  ngOnInit() {
    // this.customersList = customerJSON ;
    // this.goalList = goalDetails;
    this.loadCustomers();

    this.userInfo.userDisplayName =  sessionStorage.getItem("userDisplayName");
    this.userInfo.userDisplayRole =  sessionStorage.getItem("userDisplayRole");
  }

  public displayCustomerdetails(event) {
    this.customerDetails = event;
    this.financialEnabled = true;
    sessionStorage.setItem('customerId', this.customerDetails.id);
    
    this.refreshPage();
  }

  public refreshPage() {
    this.loadLoansByCustomerId(sessionStorage.getItem("customerId"));
  }

  public onFinancialPlanClick(event) {
    this.financialEnabled = true;
  }

  public openCustomers() {
    this.financialEnabled = false;
  }

  public loadCustomers() {
    this.customerService.getAll().subscribe((data: {}) => {
      this.customersList = data;
    });
  }

  public loadLoansByCustomerId(id) {
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
