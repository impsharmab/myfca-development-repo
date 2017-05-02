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
var cookies_service_1 = require("angular2-cookie/services/cookies.service");
var Observable_1 = require("rxjs/Observable");
require("./../rxjs-operators");
var ProfileService = (function () {
    function ProfileService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
        this.getProfileServiceUrl = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        this.getPasswordServiceUrl = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Password/';
    }
    ProfileService.prototype.getProfileData = function () {
        // var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var validToken = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.get(this.getProfileServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.postProfileData = function (name, email) {
        debugger;
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        //var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "name": name, "email": email };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(this.getProfileServiceUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.changeUserPassword = function (password) {
        debugger;
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        //        var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "item": password };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(this.getPasswordServiceUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.handleError = function (error) {
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
    return ProfileService;
}());
ProfileService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, cookies_service_1.CookieService])
], ProfileService);
exports.ProfileService = ProfileService;
//# sourceMappingURL=profile.service.js.map