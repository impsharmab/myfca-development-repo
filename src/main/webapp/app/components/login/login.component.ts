import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';

import { User } from './user.interface';
import { LoginService } from '../../services/login-services/login.service';

@Component({
    moduleId: module.id,
    templateUrl: './loginform.html',
})
export class LoginComponent implements OnInit {
    form: FormGroup;
    private test: any;
    public user: User;
    sampleUsers = [];
    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
    private arraydata: any;
    constructor(private loginService: LoginService, private router: Router, private http: Http) {
    }
    ngOnInit() {
        this.user = {
            username: '',
            password: ''
        }
    }
    log: boolean = false;
    responseUserdata: any = {
        "data": {
            "token": "",
            "status": false
        },
        "error": ""
    };


    private login(username, password) {
        this.loginService.getLoginResponse(this.user.username, this.user.password).subscribe(
            (resUserData) => {
                resUserData = this.userdata = (resUserData)
                alert(resUserData["userID"]);
                if (resUserData["error"] === "" && resUserData["error"] !== null) {
                    this.userdata["userID"] = resUserData["userID"];
                    this.userdata["name"] = resUserData["name"];
                    this.loginService.setUserData(this.userdata);
                   
                    let url = ["myfcadashboard"]
                    this.router.navigate(url);
                } else {
                    alert("error in response json " + resUserData.error)
                }
               var msg = JSON.parse(resUserData["error"])["error"]; 
                alert(msg);
            }
        )
    }

    //form submitted
    // private login(username, password) {
    //    // alert("username: " + username),
    //    //   alert("password: " + password)
    //     this.loginService.getLoginResponse(this.user.username, this.user.password).subscribe(
    //         (resUserData) => {
    //             resUserData = this.userdata = (resUserData)
    //           //  alert(resUserData.tokenresponse.data.token)
    //             console.log("resUserData: " + resUserData["tokenresponse"]["data"]["token"])
    //             if (resUserData.tokenresponse.data.status && resUserData["error"] === "" && resUserData["error"] !== null) {
    //                 this.userdata["userID"] = resUserData["userID"];
    //                 this.userdata["name"] = resUserData["name"];
    //                 this.userdata["validToken"] = resUserData["tokenresponse"]["data"]["token"];
    //                 this.loginService.setUserData(this.userdata);

    //                 let url = ["myfcadashboard"]
    //                 this.router.navigate(url);
    //                 console.log("validToken" + resUserData["tokenresponse"]["data"]["token"])
    //             } else {
    //                 alert("error in response json " + resUserData.error)
    //             }
    //             //var msg = JSON.parse(resUserData["error"])["error"];
    //             //alert(msg);

    //         }

    //     )
    // }
}


