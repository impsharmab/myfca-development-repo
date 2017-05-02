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
var AdminService = (function () {
    function AdminService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    AdminService.prototype.setEmulateUserData = function (emulateuserData) {
        var adminToken = this.cookieService.get("token");
        this.cookieService.put("adminToken", adminToken);
        this.cookieService.put("token", emulateuserData.item);
        // sessionStorage.setItem("CurrentUser", "");
        // sessionStorage.removeItem('CurrentUser');
        // sessionStorage.removeItem('selectedCodeData');
        // sessionStorage.setItem("CurrentUser", JSON.stringify(emulateuserData));
    };
    AdminService.prototype.setEndEmulateUserData = function (endEmulateUserData) {
    };
    AdminService.prototype.getPositionCode = function () {
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        var getPositionCodeUrl = "./app/components/admin/positioncode-array.json";
        return this.http.get(getPositionCodeUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getRoles = function () {
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        var getPositionCodeUrl = "./app/components/admin/admin-chooseview.json";
        return this.http.get(getPositionCodeUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getAdminData = function () {
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        var adminService = "./app/components/admin/test-admin.json";
        return this.http.get(adminService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getEmulateUserData = function (sid) {
        //var getEmulateUserDataUrl = "app/resources/json/emulate-user.response.json";
        // var getEmulateUserDataUrl = "service/Admin/?id="+sid;
        var getEmulateUserDataUrl = "https://test.myfcarewards.com/myfcarewards/services/Admin/" + sid;
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getEmulateUserDataUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.handleError = function (error) {
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
    return AdminService;
}());
AdminService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, cookies_service_1.CookieService])
], AdminService);
exports.AdminService = AdminService;
//# sourceMappingURL=admin.service.js.map