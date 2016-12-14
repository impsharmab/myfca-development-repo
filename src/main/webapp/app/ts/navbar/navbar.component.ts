import {Component, OnInit} from '@angular/core';
import {MyFcaService} from '../../app.component.service';



@Component({
    selector:"nav-image",
   templateUrl: "./app/ts/navbar/new-navbar.html", 
    providers:[MyFcaService]
})

export class NavImageComponent implements OnInit{
  
    ngOnInit() {}

    constructor(){}

    }
