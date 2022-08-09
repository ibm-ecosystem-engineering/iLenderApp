import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { GoalService } from '../../../services/goal.service';
import { InvestmentService } from '../../../services/investment.service';
import { CustomerService } from '../../../services/customer.service';

import { NotifierService } from 'angular-notifier';
import { SelectMultipleControlValueAccessor } from '@angular/forms';

@Component({
  selector: 'add-goal',
  templateUrl: './add-goal.component.html',
  styleUrls: ['./add-goal.component.scss']
})
export class AddGoalComponent implements OnInit {
  @Input() selectedGoal;
  @Input() mainDataGoal:any = {lndCustomerId: 0, loanAmount:100000,  purpose: '', duration:5, loanRequestDate: '2020-12-25'};
  @Input() mainDataInvestment :any = {wcGoalId: 0, investmentDate: '2019-09-17', investmentAmount:100, gender : '', age : 0, avgIncome : 0, married : true,  spouseAge : 0 , spouseAvgIncome: 0, noOfChildren: 0  };
  @Output() closeAddContainer = new EventEmitter();

  wealthManagerId : any;
  public currency:any;

  constructor(private notifierService: NotifierService,private customerService: CustomerService,  private goalService: GoalService, private investmentService: InvestmentService) { }

  ngOnInit() {
    this.currency = sessionStorage.getItem('currency')
  }

  onChange(event) {
    console.log(event.value);
  }
  
  closeAddBlock(message) {
    this.closeAddContainer.emit(message);
  }

  saveLoanRequest() {
    this.mainDataGoal.lndCustomerId = sessionStorage.getItem('userDisplayId');

    this.goalService.add(this.mainDataGoal).subscribe((result) => {
      this.notifierService.notify('success','New Loan request submitted Successfully');
      this.closeAddBlock(false);
    }, (err) => {
      console.log(err);
    });



  }

}
