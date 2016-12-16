import { Component, OnInit }                                from '@angular/core';
import { Router, RouterOutlet }                             from '@angular/router';
import {Http, Response, Headers, RequestOptions}            from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators }    from '@angular/forms';


import { User }                                             from './ts/login/user.interface';
import {MyFcaService}                                       from './app.component.service';
import './rxjs-operators';
import {Observable}                                         from 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: '',
    templateUrl: './ts/rootPage/rootpage.html',
    //templateUrl: './login.html',
    providers: [MyFcaService]
})
export class ViewComponent implements OnInit {
    form: FormGroup;
    public user: User;
    sampleUsers = [];

    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
    constructor(private service: MyFcaService, private router: Router, private http: Http) {

    }
    ngOnInit() {
    this.userdata =JSON.parse(localStorage.getItem("CurrentUser"));
    this.tilesArray =JSON.parse(localStorage.getItem("titles"));

    }
}