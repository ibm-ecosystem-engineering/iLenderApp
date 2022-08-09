import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogoutComponent } from './logout/logout.component';
import { LoginComponent } from "./login/login.component";
import { BusinessmanagerComponent } from "./businessmanager/businessmanager.component";
import { WealthManagerComponent } from './wealth-manager/wealth-manager.component';
import { CustomerComponent } from "./customer/customer.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'businessmanager', component: BusinessmanagerComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'wealthmanager', component: WealthManagerComponent},
  { path: 'customer', component: CustomerComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
