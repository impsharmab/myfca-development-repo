import {Component} from '@angular/core';

var loc4=window.location.pathname;
var nloc4 = loc4.slice(0,-10);

@Component({
    moduleId:module.id,
    selector:"app-footer",
 // npm url  
   templateUrl:"./footer.html"
 //tomcat url    templateUrl:"/MyFcaWebApp/MyFcaDashboard/app/ts/footer/footer.html"
 
})
export class FooterSection {
    
methodd();

    methodd(){console.log("loc44"+nloc4);}
}