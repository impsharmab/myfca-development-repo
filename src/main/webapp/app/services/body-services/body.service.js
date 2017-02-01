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
var BodyService = (function () {
    function BodyService(http) {
        this.http = http;
        this.getUserServiceUrl = './app/resources/json/newUserDetail.json';
        this.getBaseServiceUrl = 'services/userprofile';
        this.getNumberOfTiltesServiceUrl = "./app/resources/json/notiles-service.json";
        // private validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).validToken;
        // private validToken: any = this.tokenObject.validToken;
        this.tiles = new Array();
        this.userdata = {};
    }
    BodyService.prototype.setTiles = function (tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
        //sessionStorage.setItem("tiles", JSON.stringify(tiles));
    };
    BodyService.prototype.getTiles = function () {
        return this.tiles;
    };
    BodyService.prototype.setUserData = function (userdata) {
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    };
    BodyService.prototype.getUsersData = function () {
        return this.userdata;
    };
    BodyService.prototype.getNumberOfTiltes = function () {
        return this.http.get(this.getNumberOfTiltesServiceUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    BodyService.prototype.getTilteJson = function (id) {
        // alert("token from sessionstorage" + this.token);
        var headers = new http_1.Headers();
        // headers.append('Parameter', this.validToken);
        // var tileService = "./app/resources/json/" + id + "-tile.json";
        var tileService = "services/tile/" + id;
        return this.http.get(tileService, { headers: headers }) //headers should be in object
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    BodyService.prototype.getChartJson = function (id) {
        //  var chartService = "./app/resources/json/" + id + "-chart.json";
        var chartService = "services/tile/" + id;
        return this.http.get(chartService)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    // getModalJson() {
    //     var modalService = "./app/resources/json/modal.json";
    //     var modalData= this.http.get(modalService)
    //         .map((response: Response) => {
    //             response.json();
    //             console.log("inside service " + response.json());            
    //         }
    //         )
    //         .catch(this.handleError);
    //         return modalData;
    // }
    BodyService.prototype.handleError = function (error) {
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
    return BodyService;
}());
BodyService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], BodyService);
exports.BodyService = BodyService;
//# sourceMappingURL=body.service.js.map