import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, FormsModule, NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { NotifierService } from 'angular-notifier';

import { AuthenticationService } from '../services/authentication.service';
import { CommonUrlUtilService } from '../services/common-url-util.service';

@Component({
  selector: 'wealthcare-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  
  proForm: FormGroup;
  private readonly notifier: NotifierService;
  hostName : string;
  mainData:any = {loginId: 'sam', password: 'sam', returnMessage:''};

  constructor(
    public urlService : CommonUrlUtilService, public authService: AuthenticationService, 
    private formBuilder: FormBuilder,
    private router: Router,
    private notifierService: NotifierService
  ) {
    this.notifier = notifierService;
  }

  ngOnInit() {
    this.createForm();
    this.getHostName();
  }

  createForm() {
    this.proForm = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
      role: [null, Validators.required]
    });
  }

  public onSubmit() {
    this.mainData.loginId = this.proForm.value.username ;
    this.mainData.password = this.proForm.value.password ;

    this.authService.doLogin(this.mainData).subscribe(data => {
        if (data) {
          console.log("LoginComponent : " + "login response sucess :"  + data);
          this.mainData = data;
          this.postLogin();
          this.routeAfterLogin();
        } else {
          this.notifier.notify('error', 'Wrong Credentials');
        }
      }, (err) => {
        console.log(err);
      }
    );
  }

  public postLogin() {

    let userDisplayRole = "";
    switch (this.mainData.userRole) {
      case 'BM': {
        userDisplayRole = "Business Manager";
        this.populateColorsBM();
        break;
      }
      case 'FA': {
        userDisplayRole = "Field Agent";
        this.populateColorsFA();
        break;
      }
      case 'CU': {
        userDisplayRole = "Customer";
        this.populateColorsCustomer();
        break;
      }
    }





    sessionStorage.setItem('currency', 'INR');
    sessionStorage.setItem('userDisplayName', this.mainData.userDisplayName);
    sessionStorage.setItem('userDisplayId', this.mainData.userDisplayId);
    sessionStorage.setItem('userRole', this.mainData.userRole);
    sessionStorage.setItem('userDisplayRole', userDisplayRole);

    console.log("Role ----> " + this.mainData.userRole);
    console.log("userDisplayName ----> " + this.mainData.userDisplayName);
    console.log("userDisplayRole ----> " + userDisplayRole);
  }

  populateColorsBM() {
    document.documentElement.style.setProperty("--wc-header-blue", "#4682B4");
    document.documentElement.style.setProperty("--wc-white", "#ffffff");
    document.documentElement.style.setProperty("--wc-blue", "#87CEEB");
    document.documentElement.style.setProperty("--wc-gray", "#333333");
    document.documentElement.style.setProperty("--wc-title", "#5776b3");
    document.documentElement.style.setProperty("--wc-table-title", "#B8CDD7");
    document.documentElement.style.setProperty("--wc-button-primary", "#007fbf");
    document.documentElement.style.setProperty("--wc-button-primary-hover", "#53bbf0");
  }
  populateColorsCustomer() {
    // document.documentElement.style.setProperty("--wc-header-blue", "#114168");
    document.documentElement.style.setProperty("--wc-header-blue", "#0093af");
    document.documentElement.style.setProperty("--wc-white", "#ffffff");
    document.documentElement.style.setProperty("--wc-blue", "#6babc5");
    document.documentElement.style.setProperty("--wc-gray", "#d1d0d0");
    document.documentElement.style.setProperty("--wc-title", "red");
    document.documentElement.style.setProperty("--wc-table-title", "#e6f4f7");
    document.documentElement.style.setProperty("--wc-button-primary", "#0093af");
    document.documentElement.style.setProperty("--wc-button-primary-hover", "#5cd4ec");
  }
  populateColorsFA() {
    document.documentElement.style.setProperty("--wc-header-blue", "#182641");
    document.documentElement.style.setProperty("--wc-white", "#ffffff");
    document.documentElement.style.setProperty("--wc-blue", "#0034ad");
    document.documentElement.style.setProperty("--wc-gray", "#333333");
    document.documentElement.style.setProperty("--wc-title", "#5776b3");
    document.documentElement.style.setProperty("--wc-table-title", "#B8CDD7");
    document.documentElement.style.setProperty("--wc-button-primary", "#D9ECF4");
    document.documentElement.style.setProperty("--wc-button-primary-hover", "#5cd4ec");
  }

  routeAfterLogin() {
    let userRole = sessionStorage.getItem('userRole')
    console.log("Role : " + userRole);
    switch (userRole) {
      case 'BM': {
        this.gotoBusinessmanager();
        break;
      }
      case 'FA': {
        this.gotoFieldAgent();
        break;
      }
      case 'CU': {
        this.gotoCustomer();
        break;
      }
    }
  }

  gotoLogout() {
    this.authService.logOut();
    this.router.navigate(["/logout"]);
  }
  
  gotoBusinessmanager() {
    this.router.navigate(['/wealthmanager', {queryParams: true, skipLocationChange: true}]);
  }
  gotoFieldAgent() {
    this.router.navigate(['/businessmanager', {queryParams: true, skipLocationChange: true}]);
  }
  gotoCustomer() {
    this.router.navigate(['/customer', {queryParams: true, skipLocationChange: true}]);
  }

  private getHostName() {
    console.log("LoginComponent  getHostName 1: " );
    this.urlService.fetchApiGatewayURLFromServer().subscribe( (data) => {
      this.hostName = data.value1;
      console.log("LoginComponent ----> getHostName 2: " + this.hostName);    
    });
  }

}
