import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';

import { User } from './user.interface';
import { MyFcaService } from '../../app.component.service';

@Component({
    moduleId: module.id,
    templateUrl: './formSubmit.html',
})
export class Login implements OnInit {
    form: FormGroup;
    private test: any;
    public user: User;
    sampleUsers = [];
    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
    private arraydata: any;
    constructor(private service: MyFcaService, private router: Router, private http: Http) {
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
        this.service.getLoginResponse(this.user.username, this.user.password).subscribe(
            (resUserData) => {
                resUserData = this.userdata = (resUserData)
                //alert(resUserData.data.token)
                // console.log("resUserData: "+resUserData["data"]["token"])
                // if (resUserData.data.status) {
                //     sessionStorage.setItem("validToken", resUserData.data.token);

                alert(resUserData["userID"]);
                if (resUserData["error"] === "" && resUserData["error"] !== null) {
                    this.userdata["userID"] = resUserData["userID"];
                    this.userdata["name"] = resUserData["name"];
                    this.userdata["email"] = resUserData["email"];
                    this.userdata["access"] = resUserData["access"];
                    this.service.setUserData(this.userdata);
                    this.menu = resUserData["menus"];
                    this.banners = resUserData["banners"];
                    // if(resUserData.dashboard.length>0){
                    this.tilesArray = resUserData.dashboard[0]["tiles"];
                    this.tilesArray.sort((one, two) => {
                        return one.tileOrder - two.tileOrder;
                    })
                    // this.service.setTiles(this.tilesArray);
                    //    this.log = true;
                    let url = ["myfcadashboard"]
                    this.router.navigate(url);
                } else {
                    alert("error in response json " + resUserData.error)
                }
               // var msg = JSON.parse(resUserData["error"])["error"]; 
                //alert(msg);

            }

        )
    }

    //form submitted
    // private login(username, password) {
    //    // alert("username: " + username),
    //      //   alert("password: " + password)
    //     this.service.getLoginResponse(this.user.username, this.user.password).subscribe(
    //         (resUserData) => {
    //             resUserData = this.userdata = (resUserData)
    //           //  alert(resUserData.tokenresponse.data.token)
    //             console.log("resUserData: " + resUserData["tokenresponse"]["data"]["token"])
    //             if (resUserData.tokenresponse.data.status && resUserData["error"] === "" && resUserData["error"] !== null) {
    //                 this.userdata["userID"] = resUserData["userID"];
    //                 this.userdata["name"] = resUserData["name"];
    //                 this.userdata["validToken"] = resUserData["tokenresponse"]["data"]["token"];
    //                 this.service.setUserData(this.userdata);

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


