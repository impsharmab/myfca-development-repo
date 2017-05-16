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
var BannerService = (function () {
    function BannerService(http) {
        this.http = http;
        this.getBannersServiceUrl = './app/resources/json/newbanners.json';
    }
    BannerService.prototype.getBanners = function () {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var getBannersServiceUrl = "https://test.myfcarewards.com/myfcarewards/services/banners/" + positioncodes + "/" + dealerlcodes + "/";
        //var getBannersServiceUrl = "./services/banners/" + positioncodes + "/" + dealerlcodes + "/";
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.get(getBannersServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    BannerService.prototype.handleError = function (error) {
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
    return BannerService;
}());
BannerService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object])
], BannerService);
exports.BannerService = BannerService;
var _a;
//# sourceMappingURL=banner.service.js.map