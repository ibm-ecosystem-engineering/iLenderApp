import { Injectable , OnInit} from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../../model/api.response';
import { switchMap } from 'rxjs/operators';

import { CommonUrlUtilService } from './common-url-util.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Access-Control-Allow-Origin': '*'
    // ,
    // 'X-IBM-Client-Id': '82c3b3a1b20e44004654cc41ca4f8c17',
    // 'X-IBM-Client-Secret': '3594324067f7042c19b36667edb19828'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CommonAppService {

  constructor(public http: HttpClient) { }

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

