import { NotifierService } from 'angular-notifier';
import { UtilityService } from './../../../services/utility.service';
import { AfterViewInit, Component, ElementRef, ViewChild, OnInit, OnDestroy,  Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";
import { CustomerService } from "./../../../services/customer.service";
import { WealthmanagerService } from "./../../../services/wealthmanager.service";

@Component({
  selector: 'wc-assignwealthmanagers',
  templateUrl: './assignwealthmanagers.component.html',
  styleUrls: ['./assignwealthmanagers.component.scss']
})
export class AssignwealthmanagersComponent implements OnDestroy {
  userDetails: any;
  chooseWealthManager: boolean = false;
  @Output() actionToClose = new EventEmitter<boolean>();
  @Input() assignTothisWM: any;
  ChoosenWMRadioValue: any;
  ChoosenWMRadioId: any;

  selected: boolean = false;
  checkedState: boolean = false;
  message: string;
  private readonly notifier: NotifierService;
  constructor(private customerService: CustomerService, private wealthmanagerService: WealthmanagerService, private utility: UtilityService, private notifierService: NotifierService, private elRef: ElementRef) {
    this.notifier = notifierService;
  }

  ngOnInit() {
    this.userDetails = [];
    this.loadWealthManager();
    this.chooseWealthManager = true;
  }

  cancel() {
    this.actionToClose.emit(false);
  }

  save() {
    this.updateCustomer();
    this.message = 'Successfully added' + ' ' + this.ChoosenWMRadioValue +' '+' as a Wealth manager';
    this.notifier.notify('success',this.message);
    this.chooseWealthManager = false;
    this.actionToClose.emit(false);
  }

  ngOnDestroy(): void {
    this.chooseWealthManager = false;
  }

  onChange(event, id) {
    this.selected = true;
    if(event.source.checked == true) {
      this.checkedState = true;
    } else {
      this.checkedState = false;
    }
    this.ChoosenWMRadioValue = event.value;
    this.ChoosenWMRadioId = id;
  }

  public updateCustomer() {
    this.assignTothisWM.wcWealthManagerId = this.ChoosenWMRadioId;
    this.customerService.update(this.assignTothisWM.id, this.assignTothisWM)
      .subscribe(res => {
        }, (err) => {
          console.log(err);
        }
      );
  }

  public loadWealthManager() {
    this.wealthmanagerService.getAll().subscribe((data: {}) => {
      this.userDetails = data;
    });
  }


}
