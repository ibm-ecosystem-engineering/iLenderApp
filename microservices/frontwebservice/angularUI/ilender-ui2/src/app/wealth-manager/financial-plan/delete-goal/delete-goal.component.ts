import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-delete-goal',
  templateUrl: './delete-goal.component.html',
  styleUrls: ['./delete-goal.component.scss']
})
export class DeleteGoalComponent implements OnInit {

  @Input() deleteGoal;
  @Output() closeContainer = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() {
  }
closeEditBlock(message: boolean) {
    this.closeContainer.emit(message);
  }

  cancel() {
    this.closeEditBlock(false);
  }

  delete() {
    alert('deleted');
    this.closeEditBlock(false);
  }
}
