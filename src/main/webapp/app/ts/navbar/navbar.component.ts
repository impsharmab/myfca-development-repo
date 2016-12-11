import {Component, OnInit} from '@angular/core';
import {MyFcaService} from '../../app.component.service';

var loc3=window.location.pathname;
var nloc3 = loc3.slice(0,-10);

@Component({
    selector:"nav-image",
   //npm url 
   templateUrl: "./app/ts/navbar/navbar.html", 
  //  templateUrl:"/MyFcaWebApp/MyFcaDashboard/app/ts/navbar/navbar.html",
    providers:[MyFcaService]
})

export class NavImageComponent implements OnInit{
   private navbarImageDetails:any;
    ngOnInit() { this.getNavbarImageDetails()}

    constructor(private service: MyFcaService){  }

    getNavbarImageDetails(){
        this.navbarImageDetails = this.service.getNavbarData();
        console.log(loc3);
        console.log(nloc3);
        
        }
}
