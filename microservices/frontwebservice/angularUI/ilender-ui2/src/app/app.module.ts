// Required components
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, Inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { NotifierModule } from 'angular-notifier';
import { GridModule, ListModule, TabsModule, DialogModule, RadioModule, SelectModule, TableModule, ModalModule, PlaceholderModule, DropdownModule, AccordionModule } from "carbon-components-angular";
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { ClickOutsideModule } from 'ng-click-outside';
import { ChartsModule } from 'ng2-charts';
import { AngularStickyThingsModule } from '@w11k/angular-sticky-things';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


// Services
import { UtilityService } from './services/utility.service';

// Custom components
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { BusinessmanagerComponent } from './businessmanager/businessmanager.component';
import { LogoutComponent } from './logout/logout.component';
import { UserComponent } from './businessmanager/user/user.component';
import { EditUserComponent } from './businessmanager/user/editUser/editUser.component';
import { AddUserComponent } from './businessmanager/addUser/addUser.component';
import { WealthManagerComponent } from './wealth-manager/wealth-manager.component';
import { WealthCustomerComponent } from './wealth-manager/customer/customer.component';
import { FinancialPlanComponent } from './wealth-manager/financial-plan/financial-plan.component';
import { AddGoalComponent } from './wealth-manager/financial-plan/add-goal/add-goal.component';
import { GoalOverviewComponent } from './wealth-manager/financial-plan/goal-overview/goal-overview.component';
import { CustomerComponent } from './customer/customer.component';
import { GoalComponent } from './goal/goal.component';
import { TableComponent } from './wealth-manager/financial-plan/table/table.component';
import { ProfileComponent } from './wealth-manager/profile/profile.component';
import { DeleteuserComponent } from './businessmanager/user/deleteuser/deleteuser.component';
import { PortfolioComponent } from './customer/portfolio/portfolio.component';
import { AssignwealthmanagersComponent } from './businessmanager/user/assignwealthmanagers/assignwealthmanagers.component';
import { EditProfileComponent } from './wealth-manager/profile/edit-profile/edit-profile.component';
import { DeleteGoalComponent } from './wealth-manager/financial-plan/delete-goal/delete-goal.component';
import { MatMenuModule } from '@angular/material/menu';

import { AuthInterceptorService } from './services/auth-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    BusinessmanagerComponent,
    LogoutComponent,
    UserComponent,
    EditUserComponent,
    AddUserComponent,
    WealthManagerComponent,
    WealthCustomerComponent,
    FinancialPlanComponent,
    AddGoalComponent,
    GoalOverviewComponent,
    CustomerComponent,
    GoalComponent,
    TableComponent,
    ProfileComponent,
    DeleteuserComponent,
    PortfolioComponent,
    AssignwealthmanagersComponent,
    EditProfileComponent,
    DeleteGoalComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ClickOutsideModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularStickyThingsModule,
    GridModule,
    ListModule,
    TabsModule,
    DialogModule,
    RadioModule,
    SelectModule,
    TableModule,
    MatTabsModule,
    MatRadioModule,
    ModalModule,
    ChartsModule,
    PlaceholderModule,
    DropdownModule,
    AccordionModule,
    HttpClientModule,
    MatMenuModule,
    NotifierModule.withConfig({
      position: {
        horizontal: {
          position: 'right',
          distance: 10
        },
        vertical: {
          position: 'top',
          distance: 80
        }
      }
    }),
  ],
  providers: [
    UtilityService, HttpClient, { provide:HTTP_INTERCEPTORS, useClass:AuthInterceptorService, multi:true }
  ],
  entryComponents: [EditProfileComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
