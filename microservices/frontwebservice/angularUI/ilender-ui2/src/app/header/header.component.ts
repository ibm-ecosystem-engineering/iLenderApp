import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'wealthcare-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @Input() userInfo;
  public logoutToggle: boolean;

  loginType : string ;
  loginName : string;
  loginId : string ;


  constructor( private router: Router) { }

  ngOnInit() {
    this.loginType = "";
    this.loginName = "";
    this.loginId = "";
    
  }
  ngOnChanges() {
    this.loadData();
  }

  loadData(){
    let temp = sessionStorage.getItem('userRole');
    if (temp) {
      this.loginType = sessionStorage.getItem('userRole');;
    }
    temp = sessionStorage.getItem('userDisplayName');
    if (temp) {
      this.loginName = temp;
    }
    temp = sessionStorage.getItem('userDisplayId');
    if (temp) {
      this.loginId = temp;
    }
  }
  public logout(){
    this.router.navigate(["/logout"]);
    //this.router.navigate(['/login']);
  }

}
