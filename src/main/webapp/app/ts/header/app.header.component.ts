import {Component,OnInit, Input} from '@angular/core';
import {MyFcaService} from '../../app.component.service';

@Component({
    moduleId:module.id,
    selector:"app-header",

 templateUrl: "./newDesignHeader.html"
})
export class HeaderSection implements OnInit {
    
    @Input('data') userDetails:any;
    constructor(private service:MyFcaService){
        
    }
     ngOnInit() {}    

}