//importing default angular dependencies 
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http'
import { ChartModule } from 'angular2-highcharts';
import { RouterModule, Routes } from "@angular/router";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

//importing components
import { AppComponent } from './app.component';
import { RootPageComponent } from './components/root-page/root-page.component';
import { AppRoutingModule } from './components/routes/app.routes';
import { HeaderComponent } from './components/header/header.component'
import { FooterComponent } from './components/footer/footer.component'
import { DashboardBodyComponent } from './components/dashboard-body/dashboard-body.component'
import { LoginComponent } from "./components/login/login.component";

import { BannerComponent } from "./components/banner/banner.component";
import { AdminComponent } from "./components/admin/admin.component";
import { AdminRootPageComponent } from "./components/admin/admin-rootpage.component";
import { TableComponent } from "./components/table/table.component";
import { PositionCodeComponent } from "./components/positioncode/positioncode.component";
import { ProfileRootPageComponent } from "./components/profile/profile-rootpage.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { ResetPasswordComponent } from "./components/reset-password/reset-password.component"


//importing services
import { DashboardBodyService } from "./services/dashboard-body-services/dashboard-body.service"
import { FooterService } from "./services/footer-services/footer.service"
import { HeaderService } from "./services/header-services/header.service"
import { LoginService } from "./services/login-services/login.service"
import { BannerService } from "./services/banner-services/banner.service"
import { AdminService } from "./services/admin-services/admin.service"
import { PositionCodeService } from "./services/positioncode-services/positioncode.service"
import { ProfileService } from "./services/profile-service/profile.service"
import { CookieService } from 'angular2-cookie/services/cookies.service';

export function cookieServiceFactory() { return new CookieService(); }
@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ChartModule,
        ReactiveFormsModule,
        AppRoutingModule,
        NgbModule.forRoot()
    ],

    providers: [
        DashboardBodyService,
        FooterService,
        HeaderService,
        LoginService,
        NgbActiveModal,
        BannerService,
        AdminService,
        PositionCodeService,
        ProfileService,
        { provide: CookieService, useFactory: cookieServiceFactory }
    ],

    declarations: [
        AppComponent,
        LoginComponent,
        HeaderComponent,
        FooterComponent,
        DashboardBodyComponent,
        RootPageComponent,
        BannerComponent,
        AdminComponent,
        AdminRootPageComponent,
        TableComponent,
        PositionCodeComponent,
        ProfileRootPageComponent,
        ProfileComponent,
        ResetPasswordComponent
    ],

    bootstrap: [AppComponent]
})

export class AppModule {

}