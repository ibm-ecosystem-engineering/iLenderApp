import { UtilityService } from './../../../services/utility.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";
import { CustomerService } from "./../../../services/customer.service";
import { WealthmanagerService } from "./../../../services/wealthmanager.service";
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'wealthcare-delete-user',
  templateUrl: './deleteuser.component.html',
  styleUrls: ['./deleteuser.component.scss']
})
export class DeleteuserComponent implements OnInit {
  @Input() deleteUserData: any;
  @Input() deleteUserType: string;

  @Output() closeEditContainer = new EventEmitter<boolean>();

  constructor(private notifierService: NotifierService, private utility: UtilityService,  private customerService: CustomerService, private wealthmanagerService: WealthmanagerService) {}
  
  ngOnInit() {}
  
  closeEditBlock(message: boolean) {
    this.closeEditContainer.emit(message);
    this.deleteUserData = null;
    this.deleteUserType = null;
  }

  cancel() {
    this.closeEditBlock(false);
  }

  delete() {
    this.notifierService.notify('success','Deleted Successfully');
    if (this.deleteUserType == "wm") {
      this.deleteWealthManager(this.deleteUserData.id);
    } else {
      this.deleteCustomer(this.deleteUserData.id);
    }
    this.closeEditBlock(false);
  }

  public deleteWealthManager(id) {
    this.wealthmanagerService.delete(id)
      .subscribe(res => {
        }, (err) => {
          console.log(err);
        }
      );
  }

  public deleteCustomer(id) {
    this.customerService.delete(id)
      .subscribe(res => {
        }, (err) => {
          console.log(err);
        }
      );
  }

}

