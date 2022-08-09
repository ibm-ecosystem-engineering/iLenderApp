import { Component, OnInit, Input } from '@angular/core';
import { GoalService } from '../../../services/goal.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  @Input() columnLoanHistory;
  @Input() rowsLoanHistory;
  @Input() columnTransHistory;
  @Input() rowsTransHistory;
  public currency:any;

  constructor(private goalService: GoalService) { }

  ngOnInit() {
    this.currency = sessionStorage.getItem('currency')
  }

}
