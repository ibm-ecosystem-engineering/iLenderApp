import { Injectable , OnInit} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { User } from '../../model/user';
import { CommonAppService } from './common-app.service';
import { Observable, of } from 'rxjs';
import { CommonUrlUtilService } from './common-url-util.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService extends CommonAppService {

  constructor(public http: HttpClient, public urlService : CommonUrlUtilService) { super(http); }

  doLogin (mainData): Observable<any> {
    console.log("login ---> " + JSON.stringify(mainData));
    return this.http.post<any>(this.getLoginURL() , JSON.stringify(mainData), this.getHttpHeaders()).pipe(
      tap((user) => console.log(`added  w/ id=${user.loginId}`)),
      catchError(this.handleError<any>('login'))
    );
  }

  private fetchLoggedInUser(): Observable<any> {
    console.log("fetchLoggedInUser ...");
    return this.http.get(this.getLoggedInUserURL(), this.getHttpHeaders()).pipe(map(this.extractData));
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('userDisplayName')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    console.log("AuthenticationService logOut 1");
    sessionStorage.removeItem('userDisplayName')
  }


  public getLoginURL() {
    return this.urlService.getLocalHostUrl() + '/user/public/login';
    // return this.urlService.getLocalHostUrl() + '/user/public/login';
  }
  public getLoggedInUserURL() {
    return this.urlService.getLocalHostUrl() + '/userName';
  }
  public getLogoutURL() {
    return this.urlService.getLocalHostUrl() + '/logout';
  }

}
