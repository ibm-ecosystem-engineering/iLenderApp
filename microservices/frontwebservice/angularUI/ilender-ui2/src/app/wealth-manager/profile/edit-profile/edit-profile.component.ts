import { Component, OnInit, Injector } from '@angular/core';
import { BaseModal } from 'carbon-components-angular';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent extends BaseModal implements OnInit  {

  public selectedUser: any;
  public maritalStatus: any;
  public childrens: any;
  public gender: any;
  public countriesList :any;
  public helperText :any;
  public disabled :any;



  constructor(private injector: Injector) {
    super();
    this.selectedUser = this.injector.get<object>(<any> 'user');
    this.maritalStatus = [
      { content: 'Single' },
      { content: 'Married' }
    ];
    this.childrens = [
      { content: 'Child1' },
      { content: 'Child2' }
    ];
   }

  ngOnInit() {
  }

  selected(a) {
  }

  onChange(a) {
  }

}
