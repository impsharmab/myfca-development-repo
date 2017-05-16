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
    }
    ProfileService.prototype.getProfileData = function () {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl = './UserProfile/Profile/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        // var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.get(getProfileServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.changeProfileData = function (name, email) {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl = './UserProfile/Profile/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        //var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "name": name, "email": email };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(getProfileServiceUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.changeUserPassword = function (password) {
        //var getPasswordServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Password/';
        var getPasswordServiceUrl = './UserProfile/Password/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = { "item": password };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(getPasswordServiceUrl, body, { headers: headers })
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
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof cookies_service_1.CookieService !== "undefined" && cookies_service_1.CookieService) === "function" && _b || Object])
], ProfileService);
exports.ProfileService = ProfileService;
var _a, _b;
//# sourceMappingURL=profile.service.js.map