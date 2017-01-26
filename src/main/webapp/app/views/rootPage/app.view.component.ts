import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { MyFcaService } from '../../app.component.service';

@Component({
    moduleId: module.id,
    selector: '',
    templateUrl: 'rootpage.html',
    providers: [MyFcaService]
})
export class ViewComponent implements OnInit {

    sampleUsers = [];

    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
        
    constructor(private service: MyFcaService, private router: Router, private http: Http) {
    }
    ngOnInit() {
        this.userdata = JSON.parse(sessionStorage.getItem("CurrentUser"));
        this.tilesArray = JSON.parse(sessionStorage.getItem("tiles"));

    }
}