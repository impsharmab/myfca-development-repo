import { NgModule }                           from '@angular/core';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { BrowserModule }                      from '@angular/platform-browser';
import { HttpModule }                         from '@angular/http'
import { ChartModule }                        from 'angular2-highcharts';
import {RouterModule, Routes }                from "@angular/router";
import { AppComponent }                       from './app.component';
import { ViewComponent }                      from './views/rootPage/app.view.component';
import { AppRoutingModule}        from './app.routes';
import { MyFcaService }                       from './app.component.service'
import { HeaderSection }                      from './views/dashboard/header/app.header.component'
import { FooterSection }                      from './views/dashboard/footer/app.footer.component'
import { ContentSection }                     from './views/dashboard/contentbody/app.contentbody.component'
import { NavImageComponent }                  from './views/dashboard/navbar/navbar.component';
import { SpinnerComponent }                   from './views/spinner/spinner';
import { Login }                              from "./views/login/login.component";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, ChartModule, ReactiveFormsModule,AppRoutingModule,NgbModule.forRoot()
        // RouterModule.forRoot([{ path: '', redirectTo: 'login', pathMatch: 'full' },{
        //     path: "login",
        //     component: Login
        // }, {
        //     path: "myfcadashboard",
        //     component: ViewComponent
        // }])
    ],
    providers: [MyFcaService],
    declarations: [
        AppComponent, 
        Login,
        HeaderSection, 
        FooterSection, 
        ContentSection, 
        NavImageComponent, 
        ViewComponent,
        SpinnerComponent
        ],
    bootstrap: [AppComponent]
})

export class AppModule {

}