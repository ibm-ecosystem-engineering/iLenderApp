import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonDataService {

  constructor() { }
  
  public getCountries() {
    return [
      { id: 'IN', name: 'India' },
      { id: 'US', name: 'USA' },
      { id: 'EU', name: 'Europe' },
      { id: 'UK', name: 'United Kingdom' }
    ];
  }

}
