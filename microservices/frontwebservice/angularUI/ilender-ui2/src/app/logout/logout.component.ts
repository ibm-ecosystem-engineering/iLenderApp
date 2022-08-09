import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import {  Router } from "@angular/router";
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private notifierService: NotifierService, private router: Router, public authService: AuthenticationService) { }

  ngOnInit() {
    this.logout();
  }

  logout() {
    this.authService.logOut();
    // this.notifierService.notify('success','You are logged out Successfully');
    sessionStorage.clear();
    this.router.navigate(["/login"]);
  }

}
