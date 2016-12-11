import { NgModule }      from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {HttpModule} from '@angular/http'
import { AppComponent }   from './app.component';
import {HeaderSection} from './ts/header/app.header.component'
import {FooterSection} from './ts/footer/app.footer.component'
import {ContentSection} from './ts/contentbody/app.contentbody.component'
import {NavImageComponent} from './ts/navbar/navbar.component';
import { ChartModule } from 'angular2-highcharts';

import {MyFcaService} from './app.component.service'
@NgModule({
  imports:      [ BrowserModule,HttpModule,FormsModule,ChartModule],
  providers:[MyFcaService],
  declarations: [ AppComponent,HeaderSection,FooterSection,ContentSection,NavImageComponent],
  bootstrap:    [ AppComponent ]
})
export class AppModule {


 }
