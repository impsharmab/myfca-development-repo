import { Component, OnInit,ViewChild } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {DashboardBodyComponent} from "../dashboard-body/dashboard-body.component"
@Component({
    moduleId: module.id,
    selector: '',
    templateUrl: 'rootpage.html',

})
export class RootPageComponent implements OnInit {
    sampleUsers = [];
    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
    @ViewChild("bodyContent") bodyContent:DashboardBodyComponent;
    constructor(private router: Router, private http: Http) {
    }
    ngOnInit() {
        this.userdata = JSON.parse(sessionStorage.getItem("CurrentUser"));
        this.tilesArray = JSON.parse(sessionStorage.getItem("tiles"));

    }
    onProfileChange(){
      //  alert();
        this.bodyContent.reload();
    }
}