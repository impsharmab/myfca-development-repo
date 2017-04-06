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
var DashboardBodyService = (function () {
    function DashboardBodyService(http) {
        this.http = http;
        this.tiles = new Array();
        this.userdata = {};
    }
    DashboardBodyService.prototype.setTiles = function (tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
    };
    DashboardBodyService.prototype.getTiles = function () {
        return this.tiles;
    };
    DashboardBodyService.prototype.setUserData = function (userdata) {
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    };
    DashboardBodyService.prototype.getUsersData = function () {
        return this.userdata;
    };
    DashboardBodyService.prototype.getNumberOfTiltes = function () {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var getNumberOfTilesServiceUrl = "services/notile/" + positioncodes + "/" + dealerlcodes;
        //   var getNumberOfTilesServiceUrl: string = "http://172.25.32.162/myfcarewards/services/notile/" + positioncodes + "/" + dealerlcodes;
        //var getNumberOfTilesServiceUrl: string = "./app/resources/json/notiles.json";
        //  var getNumberOfTilesServiceUrl: string = "./app/resources/json/cutomer-notiles.json";
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(getNumberOfTilesServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.getTilteJson = function (id) {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        //var tileService = "./app/resources/dc-json/" + id + "-tile.json";
        var tileService = "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        //     var tileService = "http://172.25.32.162/myfcarewards/services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        return this.http.get(tileService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.getChartJson = function (id) {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        // var chartService = "./app/resources/dc-json/" + id + "-chart.json";
        // var chartService = "./app/resources/json/customer_first.json"; //retention
        //  var chartService = "http://172.25.32.162/myfcarewards/services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        var chartService = "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        return this.http.get(chartService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.getTableJson = function (id) {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var chartService = "./app/resources/dealer-level/GL-A-Jeep-Ram-processed.json";
        //var chartService = "services/tile/" + id;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        headers.append("Cache-Control", "no-cache");
        headers.append("Cache-Control", "no-store");
        return this.http.get(chartService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.handleError = function (error) {
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
    return DashboardBodyService;
}());
DashboardBodyService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], DashboardBodyService);
exports.DashboardBodyService = DashboardBodyService;
//# sourceMappingURL=dashboard-body.service.js.map