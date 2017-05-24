import { Component, OnInit, Compiler, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { CookieService } from 'angular2-cookie/services/cookies.service';

import { User } from './user.interface';
import { SSOLoginInterface } from './ssologin.interface'
import { LoginService } from '../../services/login-services/login.service';

@Component({
    moduleId: module.id,
    templateUrl: './login.html',
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
        private _compiler: Compiler,
        private activatedRoute: ActivatedRoute,
        private cookieService: CookieService) {
        this._compiler.clearCache();
    }
    ngOnInit() {
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

    // private usernameRegex(uname: string) {
    //     if (uname.length == 7) {
    //         var re = (/[s|t|S|T]{1}[0-9]{4,5}[A-Z|a-z]{1,2}/)
    //         return re.test(uname)
    //     } else {
    //         return false
    //     }
    // }
    
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
                }
            }
            )
    }

    private refreshLogin() {
        var user = this.cookieService.get("token");
        if (user !== undefined) {
            if (user !== undefined && user.length > 1) {
                // sessionStorage.setItem("showWelcomePopup", "false");
                this.hideLoginPage = true;
                this.loginService.getRefreshLoginResponse(user).subscribe(
                    (refreshTokenData) => {
                        this.refreshTokenData = (refreshTokenData)
                        if (refreshTokenData !== undefined && refreshTokenData.token.length > 1) {
                            this.loginService.setUserData(this.refreshTokenData);
                            sessionStorage.setItem("showWelcomePopup", "false");
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
                        this.cookieService.removeAll();
                        location.reload();
                        //alert("error in refreshing")

                    }
                )
            }
        }
    }


    private login() { 
        // if (!this.usernameRegex(this.user.username.trim())) {
        //     this.loginFailed = "Login Failed";
        //     this.loginErrorMessage = "Please Enter your valid SID/TID";
        //     return;
        // }
         if (this.user.username.trim() === "" && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please Enter your SID/TID and Password";
            return;
        } else if (this.user.username.trim() === "" && this.user.password !== null) {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please Enter your SID/TID";
            return;
        } else if (this.user.username !== null  && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please Enter your valid SID/TID and Password";
            return;
        }
        this.loginService.getLoginResponse(this.user.username.trim(), this.user.password.trim()).subscribe(
            (resUserData) => {
                this.userdata = (resUserData)
                // console.log(resUserData)
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

                }
            },
            (error) => {
                this.loginFailed = "Login Failed";
                this.loginErrorMessage = "Please Enter your valid SID/TID and Password";
            }
        )

    }

    private resetPassword() {
        let url = ["resetpassword"]
        this.router.navigate(url);
    }

}


