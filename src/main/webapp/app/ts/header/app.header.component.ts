import {Component,OnInit} from '@angular/core';
import {MyFcaService} from '../../app.component.service';

var loc1=window.location.pathname;
var nloc1 = loc1.slice(0,-10);

@Component({
    moduleId:module.id,
    selector:"app-header",

// npm url
 templateUrl: "./header.html"
 //tomcat url   templateUrl:"/MyFcaWebApp/MyFcaDashboard/app/ts/header/header.html"

})
export class HeaderSection implements OnInit {
    
    myUsers={};
    private userDetails:any;
    constructor(private service:MyFcaService){
         console.log("new test" +nloc1);
    }
     ngOnInit() {
          this.getUsersDetail();
        }

    getUsersDetail() {
      //  console.log("test" +nloc1);
        this.service.getUsersData()
            .subscribe(
                resUserData => this.myUsers = resUserData
                
            ) 
    }
}