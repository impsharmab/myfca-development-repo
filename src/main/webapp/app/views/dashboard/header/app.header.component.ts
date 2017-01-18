import {Component,OnInit, Input} from '@angular/core';
// import {MyFcaService} from '../../app.component.service';


@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header-bootstrap.html"
   // styleUrls:["./css/carosuel.css","./css/scrolling-nav.css"]
})

export class HeaderSection implements OnInit {
    
    @Input() data: any;
    constructor() {

    }
    ngOnInit() {
        this.data = JSON.parse(localStorage.getItem("CurrentUser"))
    //    document.getElementById("profileModel").click();
    }


}