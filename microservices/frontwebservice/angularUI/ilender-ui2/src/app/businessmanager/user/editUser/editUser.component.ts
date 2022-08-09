import { UtilityService } from './../../../services/utility.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";
import CountryJson from "src/assets/countries.json";
import { CustomerService } from "./../../../services/customer.service";
import { WealthmanagerService } from "./../../../services/wealthmanager.service";
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'wealthcare-edit-user',
  templateUrl: './editUser.component.html',
  styleUrls: ['./editUser.component.scss']
})
export class EditUserComponent implements OnInit {
  @Input() editUserData: any;
  @Input() editUserType: string;
  @Input() deleteUserData: string;
  @Output() closeEditContainer = new EventEmitter<boolean>();
  countriesList: any;

  constructor(private notifierService: NotifierService, private utility: UtilityService,  private customerService: CustomerService, private wealthmanagerService: WealthmanagerService) {
    this.countriesList = CountryJson;
  }

  ngOnInit() {}

  closeEditBlock(message: boolean) {
    this.closeEditContainer.emit(message);
    this.editUserData = null;
    this.editUserType = null;
  }

  onChange(aa) {}
  
  save() {
    this.notifierService.notify('success','Updated Successfully');
    if (this.editUserType == "wm") {
      this.updateWealthManager()
    } else {
      this.updateCustomer()
    }
    this.closeEditBlock(false);
  }

  public updateWealthManager() {
    this.wealthmanagerService.update(this.editUserData.id, this.editUserData)
      .subscribe(res => {
        }, (err) => {
          console.log(err);
        }
      );
  }

  public updateCustomer() {
    this.customerService.update(this.editUserData.id, this.editUserData)
      .subscribe(res => {
        }, (err) => {
          console.log(err);
        }
      );
  }

}
