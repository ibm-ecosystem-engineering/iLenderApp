import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'wealth-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class WealthCustomerComponent implements OnInit {

  @Input() userDetails;
  @Output() variableToEmit = new EventEmitter<boolean>();

  public currency:any;


  constructor() { }

  ngOnInit() {
    this.currency = "INR";
  }

  public openCustomerDetails(userDetails) {
    this.variableToEmit.emit(userDetails);
  }
}
