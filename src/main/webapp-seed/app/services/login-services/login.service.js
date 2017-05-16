"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
var cookies_service_1 = require("angular2-cookie/services/cookies.service");
//import { CookieStorage } from 'cookie-storage';
require("./../rxjs-operators");
//const cookieStorage = new CookieStorage();
var LoginService = (function () {
    function LoginService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
        this.getLoginResponseUrl = './app/resources/json/token_response.json';
        this.userdata = {};
    }
    LoginService.prototype.setUserData = function (userdata) {
        sessionStorage.setItem("CurrentUser", "");
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
        // this.cookieService.put("CurrentUser", "");
        // this.cookieService.remove('CurrentUser');
        // this.cookieService.remove('selectedCodeData');
        // this.cookieService.put("CurrentUser", JSON.stringify(userdata));
        // console.log(this.cookieService.get("CurrentUser"))
        // this.cookieService.removeAll();
        this.cookieService.put("token", (userdata.token));
    };
    LoginService.prototype.getUsersData = function () {
        return this.userdata;
    };
    LoginService.prototype.getSSOLoginResponse = function (ssotoken, ssodealercode, ssopositioncode) {
        var url = "./login/token/" + ssotoken + "/" + ssodealercode + "/" + ssopositioncode;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        headers.append("Cache-Control", "no-store");
        return this.http.get(url)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.getRefreshLoginResponse = function (token) {
        var url = "https://test.myfcarewards.com/myfcarewards/login/tokenrefresh/";
        //var url = "./login/tokenrefresh/";
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', token);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(url, { headers: headers })
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.getLoginResponse = function (username, password) {
        var url = "./login/token/";
        var url = "https://test.myfcarewards.com/myfcarewards/login/token/";
        var body = { "username": username, "password": password };
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.post(url, body, { headers: headers })
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    return LoginService;
}());
LoginService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof cookies_service_1.CookieService !== "undefined" && cookies_service_1.CookieService) === "function" && _b || Object])
], LoginService);
exports.LoginService = LoginService;
var _a, _b;
//# sourceMappingURL=login.service.js.map