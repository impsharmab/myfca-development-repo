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
require("./rxjs-operators");
var sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');
var MyFcaService = (function () {
    function MyFcaService(http) {
        this.http = http;
        this.getLoginResponseUrl = './app/resources/json/serviceJson/token_response.json';
        this.getUserServiceUrl = './app/resources/json/newUserDetail.json';
        this.getBaseServiceUrl = 'services/userprofile';
        this.getBannersServiceUrl = './app/resources/json/newbanners.json';
        this.getNumberOfTiltesServiceUrl = "./app/resources/json/serviceJson/notiles.json";
        this.tokenObject = JSON.parse(sessionStorage.getItem("CurrentUser"));
        //private validToken: any = this.tokenObject.validToken;
        this.tiles = new Array();
        this.userdata = {};
    }
    MyFcaService.prototype.setTiles = function (tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
        //sessionStorage.setItem("tiles", JSON.stringify(tiles));
    };
    MyFcaService.prototype.getTiles = function () {
        return this.tiles;
    };
    MyFcaService.prototype.setUserData = function (userdata) {
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    };
    MyFcaService.prototype.getUsersData = function () {
        return this.userdata;
    };
    MyFcaService.prototype.getBanners = function () {
        return this.http.get(this.getBannersServiceUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    MyFcaService.prototype.getNumberOfTiltes = function () {
        return this.http.get(this.getNumberOfTiltesServiceUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    MyFcaService.prototype.getTilteJson = function (id) {
        // alert("token from sessionstorage" + this.token);
        var headers = new http_1.Headers();
        // headers.append('Parameter', this.validToken);
        // var tileService = "./app/resources/json/serviceJson/" + id + "-tile.json";
        var tileService = "services/tile/" + id;
        return this.http.get(tileService, { headers: headers }) //headers should be in object
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    MyFcaService.prototype.getChartJson = function (id) {
        //  var chartService = "./app/resources/json/serviceJson/" + id + "-chart.json";
        var chartService = "services/tile/" + id;
        return this.http.get(chartService)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    MyFcaService.prototype.getLoginResponse = function (username, password) {
        var q_password = sha256(password);
        //console.log("encrypted q_password: " + q_password)
        // console.log("encrypted q_password: " + q_password) 
        // console.log("this.token :"+ this.validToken)       
        var body = "id=" + username + "&key=" + password;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        return this.http.post(this.getBaseServiceUrl, body, { headers: headers })
            .map(function (response) {
            return response.json();
        })
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
    MyFcaService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    MyFcaService.prototype.handleError = function (error) {
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
    MyFcaService.prototype.getHeaders = function () {
        var headersObj = new http_1.Headers();
        //headers.append('Accept', 'application/json');
        headersObj.append('Access-Control-Allow-Headers', 'Content-Type');
        headersObj.append('Access-Control-Allow-Methods', 'GET');
        headersObj.append('Access-Control-Allow-Origin', '*');
        return headersObj;
    };
    return MyFcaService;
}());
MyFcaService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], MyFcaService);
exports.MyFcaService = MyFcaService;
//# sourceMappingURL=app.component.service.js.map