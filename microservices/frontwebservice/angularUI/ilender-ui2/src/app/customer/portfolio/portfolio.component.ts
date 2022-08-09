import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableModel, TableItem, TableHeaderItem } from 'carbon-components-angular';
import rows from 'src/assets/tablerows.json';

@Component({
  selector: 'portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.scss']
})
export class PortfolioComponent implements OnInit {
  @Input() selectedGoal;
  @Input() columnsForTable;
  @Input() rowsForTable;
  @Input() selectedIndex;


  @Output() closeContainer = new EventEmitter();
  public selectedGoalDetails: any;

  public addGoalPageEnabled: boolean;

  constructor() {
  }

  ngOnInit() {
  }
  closeBlock(message) {
    this.closeContainer.emit(message);
  }
  public addInvestment(goal, index) {
    this.addGoalPageEnabled = true;
    console.log('add investemnt' + goal + index);
    goal.name = 'Goal #' + index + ':' + goal.name;
    console.log(goal);
    this.selectedGoalDetails = goal;

  }
  public closeGoalOverView(event) {
    this.addGoalPageEnabled = false;

  }
  public deleteGoal(goal, index) {

  }

}
