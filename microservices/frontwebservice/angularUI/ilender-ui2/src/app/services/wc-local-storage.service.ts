import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WcLocalStorageService {

  constructor() { }

  //Customer Details
  public putCustomerDetails(id, name) {
    this.put("Customer-Id", id);
    this.put("Customer-Name", name);
  }
  public getCustomerId() {
    return this.getWithNullCheck("Customer-Id");
  }
  public getCustomerName() {
    return this.getWithNullCheck("Customer-Name");
  }

  //WealthManager Details
  public putWealthManagerId(id) {
    this.put("WealthManager-Id", id);
  }
  public getWealthManagerId() {
    return this.getWithNullCheck("WealthManager-Id");
  }

  //JSON
  public putJson(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
  }
  public getJson(key) {
    let item = JSON.parse(localStorage.getItem(key));
    return item;
  }

  //Get, Put
  public put(key, value) {
    localStorage.setItem(key, value);
  }
  public get(key) {
    return localStorage.getItem(key);
  }
  public getWithNullCheck(key) {
    let value = this.get(key);
    if (value === null) {
      value = "";
    }
    return value;
  }


  //Clear
  public clear() {
    localStorage.clear();
  }
}
