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
require("./../rxjs-operators");
var PositionCodeService = (function () {
    function PositionCodeService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    PositionCodeService.prototype.setCodeData = function (codeData) {
        this.selectedCodeData = sessionStorage.setItem("selectedCodeData", JSON.stringify(codeData));
        //this.selectedCodeData = this.cookieService.put("selectedCodeData", JSON.stringify(codeData))
    };
    PositionCodeService.prototype.getCodeData = function () {
        return JSON.parse(sessionStorage.getItem("selectedCodeData"));
        //return JSON.parse(this.cookieService.get("selectedCodeData")); 
    };
    return PositionCodeService;
}());
PositionCodeService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [typeof (_a = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _a || Object, typeof (_b = typeof cookies_service_1.CookieService !== "undefined" && cookies_service_1.CookieService) === "function" && _b || Object])
], PositionCodeService);
exports.PositionCodeService = PositionCodeService;
var _a, _b;
//# sourceMappingURL=positioncode.service.js.map