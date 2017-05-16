import { Component, OnInit, Compiler, OnDestroy } from '@angular/core';
import { Router, RouterOutlet, ActivatedRoute, Params } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { CookieService } from 'angular2-cookie/services/cookies.service';
//import { CookieStorage } from 'cookie-storage';

import { User } from './user.interface';
import { SSOLoginInterface } from './ssologin.interface'
import { LoginService } from '../../services/login-services/login.service';

//const cookieStorage = new CookieStorage();

@Component({
    moduleId: module.id,
    templateUrl: './clean-login.html',
})
export class LoginComponent implements OnInit {
    form: FormGroup;
    public user: User;
    public ssouser: SSOLoginInterface;
    private userdata: any = {};
    private ssouserdata: any = {};
    private ssotoken: string = "";
    private ssodealercode: string = "";
    private ssopositioncode: string = "";
    private loginFailed: string = "";
    private loginErrorMessage: string = "";
    private refreshTokenData: any;
    private hideLoginPage: boolean = false;

    constructor(private loginService: LoginService,
        private router: Router,
        private http: Http,
        private _compiler: Compiler,
        private activatedRoute: ActivatedRoute,
        private cookieService: CookieService) {
        this._compiler.clearCache();
    }
    ngOnInit() {
        //var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        this.user = {
            username: '',
            password: ''
        }
        this.activatedRoute.queryParams.subscribe((params: Params) => {
            this.ssotoken = params['token'];
            this.ssodealercode = params['dc'];
            this.ssopositioncode = params['pc'];
            if (this.ssotoken !== "" && this.ssotoken !== undefined) {
                this.hideLoginPage = true;
                this.ssologin(
                    this.ssotoken,
                    this.ssopositioncode,
                    this.ssodealercode
                )
            }
        });
        this.refreshLogin();
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

                    // this.cookieService.put("selectedCodeData", JSON.stringify(
                    //     {
                    //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                    //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                    //     }))

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
                }
            }
            )
    }

    private refreshLogin() {
        sessionStorage.setItem("showWelcomePopup", "false");
        var user = this.cookieService.get("token");
        if (user !== undefined) {
            if (user !== undefined && user.length > 1) {
                this.hideLoginPage = true;                
                this.loginService.getRefreshLoginResponse(user).subscribe(
                    (refreshTokenData) => {
                        //alert(refreshTokenData.token)
                        this.refreshTokenData = (refreshTokenData)
                        if (refreshTokenData.token.length > 1) {
                            this.loginService.setUserData(this.refreshTokenData);
                            var poscodes: any = this.refreshTokenData.positionCode;
                            var delcodes: any = this.refreshTokenData.dealerCode;
                            sessionStorage.setItem("selectedCodeData", JSON.stringify(
                                {
                                    "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                                    "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                                }))

                            let url = ["myfcadashboard"]
                            this.router.navigate(url);
                        } else {

                        }
                    },
                    (error) => {
                        alert("error in refreshing")
                    }

                )
            }
        }
    }


    private login() {
        if (this.user.username.trim() === "" && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your SID/TID and Password";
            return;
        } else if (this.user.username.trim() === "" && this.user.password.trim() !== null) {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your SID/TID";
            return;
        } else if (this.user.username.trim() !== null && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your Password";
            return;
        }
        this.loginService.getLoginResponse(this.user.username, this.user.password).subscribe(
            (resUserData) => {
                this.userdata = (resUserData)
                console.log(resUserData)
                if (resUserData["token"].length > 0) {
                    this.loginService.setUserData(this.userdata);

                    var poscodes: any = this.userdata.positionCode;
                    var delcodes: any = this.userdata.dealerCode;
                    // this.cookieService.put("selectedCodeData", JSON.stringify(
                    //     {
                    //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                    //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                    //     }))
                    sessionStorage.setItem("selectedCodeData", JSON.stringify(

                        {
                            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                        }))

                    let url = ["myfcadashboard"]
                    this.router.navigate(url);

                }
            },
            (error) => {
                this.loginFailed = "Login Failed";
                this.loginErrorMessage = "Please enter your valid SID/TID and password";
            }
        )

    }
}


