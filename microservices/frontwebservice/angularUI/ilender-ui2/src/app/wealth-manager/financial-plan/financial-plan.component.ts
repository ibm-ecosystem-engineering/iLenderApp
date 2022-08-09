import { Component, OnInit, Input } from '@angular/core';
import rows from 'src/assets/tablerows.json';
import { NotifierService } from 'angular-notifier';
import { GoalService } from '../../services/goal.service';
import { ChartType } from 'chart.js';
import { MultiDataSet, Label } from 'ng2-charts';

@Component({
  selector: 'financial-plan',
  templateUrl: './financial-plan.component.html',
  styleUrls: ['./financial-plan.component.scss']
})
export class FinancialPlanComponent implements OnInit {

  @Input() customersLoanList;
  @Input() financialEnabled;

  public isBusinessManager: boolean;

  public addGoalPageEnabled: boolean;
  public deleteUserEnabled: boolean;

  public openGoalDetailsPageEnabled: boolean;
  public selectedGoal: any;
  public selectedIndex: any;
  public columnsForTable:any;
  public rowsForTable:any;
  public customerGoals:any;
  public deleteSelectedGoal:any;
  public testChange:any;
  public customerIdToRefresh:any;

  public currency:any;


  constructor(private notifierService: NotifierService, private goalService: GoalService ) { 

  }
  ngOnChanges(){
    this.customerGoals = this.customersLoanList;
  }

  ngOnInit() {
      this.currency = sessionStorage.getItem('currency')
      let userRole = sessionStorage.getItem('userRole')
      if (userRole ==  'BM') {
        this.isBusinessManager = true;
      } else {
        this.isBusinessManager = false;
      }
      this.testChange = "test test.....On iniit......";

      console.log("Role isBusinessManager: " + this.isBusinessManager);
  }

  public openGoalDetails(goal,index) {
    // this.customerGoals = [goal];
    this.selectedGoal = [goal];
    this.selectedIndex  = index + 1;
    this.openGoalDetailsPageEnabled = true;
    this.addGoalPageEnabled = false;
    this.financialEnabled = false;
    this.columnsForTable = [ 'Goal #', 'Date', 'Invested Amount', 'Stocks(INR)', 'Mutual Funds(INR)', 'Fixed Deposit(INR)', 'Current Value(INR)'];
//    this.rowsForTable = rows;
    
    //Added by Gandhi
    this.rowsForTable = [goal];
  }
  public requestForLoan(goalItem?){
    this.selectedIndex  = -1;

    this.addGoalPageEnabled = true;
    this.openGoalDetailsPageEnabled = false;
    this.financialEnabled =false;
    this.selectedGoal ='';
  }

  public deleteGoal(goal, action) {
    console.log('Delete goal' + goal + action);
    this.deleteSelectedGoal = goal;
    this.deleteUserEnabled = true;

    this.goalService.delete(goal.id).subscribe((result) => {
    }, (err) => {
      console.log(err);
    });

    this.notifierService.notify('success','Goal deleted Successfully');
  }
  public closeGoalOverView(event) {
    // this.customerGoals = this.customersLoanList;
    this.customerIdToRefresh = sessionStorage.getItem('userDisplayId');
    this.loadLoansByCustomerId();
    
    this.openGoalDetailsPageEnabled = false;
    this.addGoalPageEnabled = false;
    this.financialEnabled = true;
    this.deleteUserEnabled = event;

  }

  public loadLoansByCustomerId() {
    let customerId = this.customerIdToRefresh;
    this.goalService.getAllInfoByCustomerId(customerId).subscribe((data: any) => {
      console.log ("loadLoansByCustomerId .....The data 11......." + data);
      this.customerGoals = data;
    });
  }

  clickGetCreditScore(customerId, loanId) {
    this.customerIdToRefresh = sessionStorage.getItem('customerId');

    this.goalService.getCreditScore(customerId, loanId).subscribe((result) => {
      this.loadLoansByCustomerId();
    }, (err) => {
      console.log(err);
    });

    this.notifierService.notify('success','Credit Score Request submitted Successfully');
  }

  clickReject(customerId, loanId) {
    this.customerIdToRefresh = sessionStorage.getItem('customerId');

    this.goalService.rejectLoanRequest(loanId).subscribe((result) => {
      this.loadLoansByCustomerId();
    }, (err) => {
      console.log(err);
    });

    this.notifierService.notify('success','Reject LoanRequest submitted Successfully');
  }

  clickApprove(customerId, loanId) {
    this.customerIdToRefresh = sessionStorage.getItem('customerId');
    this.goalService.approveLoanRequest(loanId).subscribe((result) => {
      this.loadLoansByCustomerId();
    }, (err) => {
      console.log(err);
    });

    this.notifierService.notify('success','Approve LoanRequest submitted Successfully');
  }

  clickAccept(customerId, loanOfferId) {
    this.customerIdToRefresh = sessionStorage.getItem('userDisplayId');
    this.goalService.acceptLoanOffer(loanOfferId).subscribe((result) => {
      this.loadLoansByCustomerId();
    }, (err) => {
      console.log(err);
    });

    this.notifierService.notify('success','Accept LoanOffer submitted Successfully');
  }
}
