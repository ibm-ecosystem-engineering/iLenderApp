import { Injectable , OnInit} from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../../model/api.response';
import { switchMap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CommonUrlUtilService  {

  apiGatewayUrlUser : string;
  apiGatewayUrlFinancialplan : string;
  localhostURL : string;
  text1 : string;
  text2 : string;

  constructor(public http: HttpClient) {
    this.loadOnStartup();
  }

  loadOnStartup() {
    console.log("CommonUrlUtilService  loadOnStartup started: " );
    
    //Comment any of the method as per the need
    // this.initUrlDev();
    this.initUrlPROD();
    
    console.log("CommonUrlUtilService ----> loadOnStartup apiGatewayUrlUser 1: " + this.apiGatewayUrlUser);
    console.log("CommonUrlUtilService ----> loadOnStartup apiGatewayUrlFinancialplan 1: " + this.apiGatewayUrlFinancialplan);
    console.log("CommonUrlUtilService ----> loadOnStartup localhostURL 1: " + this.localhostURL);

    this.initApiGatewayURL();

    console.log("CommonUrlUtilService  loadOnStartup completed: " );
  }

  public initUrlDev() {
    this.localhostURL = "http://localhost:9070";
    // this.localhostURL = "http://52.116.180.217:31320";
  }
  public initUrlPROD() {
    this.localhostURL = "";
  }

  private initApiGatewayURL() {
    console.log("CommonUrlUtilService  initApiGatewayURL 1: " );

    this.fetchApiGatewayURLFromServer().subscribe( (data) => {
      console.log("CommonUrlUtilService  initApiGatewayURL 2: data :" + data );
      this.apiGatewayUrlUser = data.url1;
      this.apiGatewayUrlFinancialplan = data.url2;
      this.text1 = data.value1;
      
      //Set the hostName for future reference
      sessionStorage.setItem('hostName', this.text1);

      console.log("CommonUrlUtilService ----> initApiGatewayURL text1 1: " + this.text1);
      console.log("CommonUrlUtilService ----> initApiGatewayURL apiGatewayUrlUser 2: " + this.apiGatewayUrlUser);
      console.log("CommonUrlUtilService ----> loadOinitApiGatewayURLnStartup apiGatewayUrlFinancialplan 2: " + this.apiGatewayUrlFinancialplan);
      console.log("CommonUrlUtilService ----> initApiGatewayURL localhostURL 2: " + this.localhostURL);    });
  }

  public fetchApiGatewayURLFromServer(): Observable<any> {
    console.log("CommonUrlUtilService  fetchApiGatewayURLFromServer 1: ");
    return this.http.get(this.localhostURL + '/api/apiServiceURL', this.getHttpHeaders()).pipe(map(this.extractData));
  }

  public getApiGatewayUrlUser() {
    return this.apiGatewayUrlUser;
  }

  public getApiGatewayUrlFinancialplan() {
    return this.apiGatewayUrlFinancialplan;
  }

  public getLocalHostUrl() {
    return this.localhostURL;
  }


  public getText1l() {
    return this.text1;
  }
  public getText2l() {
    return this.text2;
  }


  public getHttpHeaders() {
    return httpOptions;
  }

  public extractData(res: Response) {
    let body = res;
    return body || { };
  }

  public handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
