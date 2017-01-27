import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

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

    constructor(private router: Router, private http: Http) {
    }
    ngOnInit() {
        this.userdata = JSON.parse(sessionStorage.getItem("CurrentUser"));
        this.tilesArray = JSON.parse(sessionStorage.getItem("tiles"));

    }
}