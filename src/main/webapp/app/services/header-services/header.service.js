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
require("./../rxjs-operators");
var sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');
var HeaderService = (function () {
    // private validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).validToken;
    // private validToken: any = this.tokenObject.validToken;
    function HeaderService(http) {
        this.http = http;
        this.getBannersServiceUrl = './app/resources/json/newbanners.json';
    }
    HeaderService.prototype.getBanners = function () {
        return this.http.get(this.getBannersServiceUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HeaderService.prototype.handleError = function (error) {
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
    return HeaderService;
}());
HeaderService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], HeaderService);
exports.HeaderService = HeaderService;
//# sourceMappingURL=header.service.js.map