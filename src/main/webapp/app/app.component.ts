import { Component, OnInit } from '@angular/core';
import {MyFcaService} from './app.component.service';

var loc5=window.location.pathname;
//var nloc5 = loc5.slice(0,-10);

@Component({
  moduleId:module.id,
  selector: 'my-app',
 //npm url  /app/app.component.html',
 templateUrl: './app.component.html',
//tomcat url  templateUrl: '/MyFcaWebApp/MyFcaDashboard/app/app.component.html',

  providers:[MyFcaService]
})
export class AppComponent implements OnInit{

    ngOnInit(){}

    getData(){};

}
