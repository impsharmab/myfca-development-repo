import { Component, OnInit, Compiler } from '@angular/core';
import { Router, RouterOutlet, Params, ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { SSOLoginInterface } from './ssologin.interface'


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
    public ssoLoginInterface: SSOLoginInterface;
    sampleUsers = [];
    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
    private arraydata: any;
    private ssotoken: string = "";
    private ssodealercode: string = "";
    private ssopositioncode: string = "";


    constructor(private loginService: LoginService, private router: Router, private http: Http, private _compiler: Compiler, private activatedRoute: ActivatedRoute) {
        this._compiler.clearCache();
    }
    ngOnInit() {
        this.user = {
            username: '',
            password: ''
        }

        // this.ssoLoginInterface = {
        //     ssotoken: "",
        //     ssodealercode: "",
        //     ssopositioncode: ""
        // }

        this.activatedRoute.queryParams.subscribe((params: Params) => {
            this.ssotoken = params['token'];
            this.ssodealercode = params['dc'];
            this.ssopositioncode = params['pc'];

        });

        if (this.ssotoken !== "") {
            this.ssologin(
                this.ssotoken,
                this.ssopositioncode,
                this.ssodealercode
            )
        }
    }

    private ssologin(ssotoken: string, ssopositioncode: string, ssodealercode: string) {
        this.loginService.getSSOLoginResponse(
            this.ssotoken,
            this.ssopositioncode,
            this.ssodealercode).subscribe(
            (resUserData) => {
                this.userdata = (resUserData)
                if (resUserData["token"].length > 0) {
                    this.loginService.setUserData(this.userdata);
                    var poscodes: any = this.userdata.positionCode;
                    var delcodes: any = this.userdata.dealerCode;
                    sessionStorage.setItem("selectedCodeData", JSON.stringify(
                        {
                            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                        }))

                    let url = ["myfcadashboard"]
                    this.router.navigate(url);

                } else {
                    let url = ["login"]
                    this.router.navigate(url);
                    // alert("invalid user");

                    //alert(resUserData.error)
                }
                // var msg = JSON.parse(resUserData["error"])["error"];
                // alert(msg);
            }
            )
    }

    private login(username: string, password: string) {
        this.loginService.getLoginResponse(this.user.username, this.user.password).subscribe(
            (resUserData) => {
                this.userdata = (resUserData)
                // alert(resUserData["userID"]);
                if (resUserData["token"].length > 0) {
                    this.loginService.setUserData(this.userdata);
                    var poscodes: any = this.userdata.positionCode;
                    var delcodes: any = this.userdata.dealerCode;
                    //var poscodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
                    // var delcodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
                    sessionStorage.setItem("selectedCodeData", JSON.stringify(
                        {
                            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                        }))

                    let url = ["myfcadashboard"]
                    this.router.navigate(url);

                } else {
                    alert("invalid user");

                    //alert(resUserData.error)
                }
                // var msg = JSON.parse(resUserData["error"])["error"];
                // alert(msg);
            }
        )
    }

    // if (poscodes === undefined)
    //                     var selectedPositionCode = 0;
    //                 else if (poscodes.length > 0)
    //                     selectedPositionCode = poscodes[0];

    //                 if (delcodes === undefined)
    //                     var selectedDealerCode = 0;
    //                 else if (delcodes.length > 0)
    //                     selectedDealerCode = delcodes[0];

    //                 var sessionStorageSelectedObject = '{ "selectedPositionCode": selectedPositionCode, "selectedDealerCode": selectedDealerCode }';

    //                 //sessionStorageSelectedObject.selectedPositionCode= poscodes === undefined ? 0 : poscodes.length > 0 ? poscodes[0] : 0;

    //                 // JSON.stringify(
    //                 //     {
    //                 //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes.length > 0 ? poscodes[0] : 0,
    //                 //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes.length > 0 ? delcodes[0] : 0
    //                 //     })
    //                 sessionStorage.setItem("selectedCodeData", JSON.parse(sessionStorageSelectedObject))


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


