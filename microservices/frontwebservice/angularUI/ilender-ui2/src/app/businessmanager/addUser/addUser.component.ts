import { UtilityService } from "./../../services/utility.service";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  ViewEncapsulation
} from "@angular/core";
import CountryJson from "src/assets/countries.json";
import { CustomerService } from '../../services/customer.service';
import { WealthmanagerService } from '../../services/wealthmanager.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: "wealthcare-add-user",
  templateUrl: "./addUser.component.html",
  styleUrls: ["./addUser.component.scss"]
})
export class AddUserComponent implements OnInit {
  @Input() addThisUser: string;
  @Input() clickedValue: string;
  @Output() closeAddContainer = new EventEmitter<boolean>();
  countriesList: any;
  @Input() selectedRole: string;
  wm: boolean = false;
  cs: boolean = false;
  isOpen: boolean = false;

  @Input() mainDataCs :any = {firstName: '', lastName: '', age: 18, gender:'Male', emailId: '', city : '',  country: '', avgIncome: 10000, wcWealthManagerId:''};
  @Input() mainDataWm :any = {firstName: '', lastName: '', age: 18, gender:'Male', emailId: '', city : '',  country: ''};

  constructor(private notifierService: NotifierService, private utility: UtilityService, public customerService: CustomerService, public wealthmanagerService: WealthmanagerService, ) {
    this.countriesList = CountryJson;
  }

  ngOnInit() {
    this.isOpen = true;
    if (this.selectedRole == "wm") {
      this.wm = true;
    } else {
      this.cs = true;
    }
  }

  closeAddBlock(message: boolean) {
    this.closeAddContainer.emit(message);
  }

  saveWealthmanager(message: boolean) {
    this.wealthmanagerService.add(this.mainDataWm).subscribe((result) => {
    }, (err) => {
      console.log(err);
    });
    this.closeAddContainer.emit(message);
    this.notifierService.notify('success','Added Successfully');
  }

  saveCustomer(message: boolean) {
    this.customerService.add(this.mainDataCs).subscribe((result) => {
    }, (err) => {
      console.log(err);
    });
    this.closeAddContainer.emit(message);
    this.notifierService.notify('success','Added Successfully');
  }

  onChange(event) {
    console.log(event.value);
  }
}
