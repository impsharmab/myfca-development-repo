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
import { BodyComponent } from './components/body/body.component'
import { SpinnerComponent } from './components/spinner/spinner.component';
import { LoginComponent } from "./components/login/login.component";
import { NgbdModalContent } from "./components/body/body.component.modal";



//importing services
import { BodyService } from "./services/body-services/body.service"
import { FooterService } from "./services/footer-services/footer.service"
import { HeaderService } from "./services/header-services/header.service"
import { LoginService } from "./services/login-services/login.service"

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
        BodyService,
        FooterService,
        HeaderService,
        LoginService,
        NgbActiveModal],

    declarations: [
        AppComponent,
        LoginComponent,
        HeaderComponent,
        FooterComponent,
        BodyComponent,
        RootPageComponent,
        SpinnerComponent,
        NgbdModalContent
    ],

    bootstrap: [AppComponent]
})

export class AppModule {

}