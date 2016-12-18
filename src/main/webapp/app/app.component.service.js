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
var MyFcaService = (function () {
    function MyFcaService(http) {
        this.http = http;
        this.userDetailUrl = "/app/resources/json/userdetail.json"; // /imiservices/app/resources/json/userdetail.json"; 
        // private serviceUrl = "" //"/imiservices/services/tileslistbyrole?role=2&id=S12345";
        // private userprofiletestJsonUrl = "./app/resources/json/userprofiletest.json";
        // private userprofiletestServiceUrl = "./app/resources/json/userprofiletest.json" //imiservices/services/userprofiletest?id=S12345&key=password";
        // private newService = "/app/resources/json/userprofiletest.json";
        this.titles = new Array();
        this.userdata = {};
    }
    MyFcaService.prototype.setTiles = function (titles) {
        localStorage.setItem("titles", JSON.stringify(titles));
    };
    MyFcaService.prototype.getTiles = function () {
        return this.titles;
    };
    MyFcaService.prototype.getUsers = function () {
        var mytestUsers = this.http.get(this.userDetailUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
        return mytestUsers;
    };
    MyFcaService.prototype.setUserData = function (userdata) {
        localStorage.setItem("CurrentUser", JSON.stringify(userdata));
    };
    MyFcaService.prototype.getUsersData = function () {
        // var myFcaUsers =  this.http.get(this.userDetailUrl)
        //   .map((response:Response) => response.json()) 
        //   .catch(this.handleError);
        //   return myFcaUsers;
        return this.userdata;
    };
    MyFcaService.prototype.getNewServiceJSON = function (username, password) {
        //this is for live service url
        // var serviceurl = "services/userprofile?id=" + username + "&key=" + password;        
        // var serviceurl = "http://localhost:9123/imiservices/services/userprofile?id=" + username + "&key=" + password;
        //this is the local service url
        var serviceurl = "app/resources/json/userprofiletest.json";
        //var tileDataThroughService = this.http.post(serviceurl, {})
        var tileDataThroughService = this.http.get(serviceurl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
        return tileDataThroughService;
    };
    //     getNewServiceJSON(username, password): any {
    //         var serviceurl = "services/userprofiletest?id=" + username + "&key=" + password;
    //         setTimeout(() => 
    // {
    //     var tileDataThroughService = this.http.get(serviceurl)
    //             .map((response: Response) => response.json())
    //             .catch(this.handleError);
    //    return tileDataThroughService;
    // },
    // 1000);
    //var userprofiletestServiceUrl = "app/resources/json/userprofiletest.json"
    // getTileDataThroughService(): any {
    //    // var newServiceUrl="/services/userprofiletest?id="+this.userdata.id+"&key="+this.userdata.key;
    //    // var userprofiletestServiceUrl = "./app/resources/json/userprofiletest.json";
    //     // var serviceUrl="/imiservices/services/tileslistbyrole?role="+this.userdata.access[0].roleID+"&id="+this.userdata.userID;
    //     var tileDataThroughService = this.http.get(newServiceUrl)
    //         .map((response: Response) => response.json())
    //         .catch(this.handleError);
    //     return tileDataThroughService;
    // }
    // <nfSubmit =getTileDataThroughService();> Login</>
    MyFcaService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    MyFcaService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        var errMsg = "";
        // if (error instanceof Response) {
        //     const body = error.json() || '';
        //     const err = body.error || JSON.stringify(body);
        //     errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        // } else {
        //     errMsg = error.message ? error.message : error.toString();
        // }
        alert("Invalid credentials");
        return Observable_1.Observable.throw(errMsg);
    };
    MyFcaService.prototype.getData = function () {
        var headersObj = new http_1.Headers();
        //headers.append('Accept', 'application/json');
        headersObj.append('Access-Control-Allow-Headers', 'Content-Type');
        headersObj.append('Access-Control-Allow-Methods', 'GET');
        headersObj.append('Access-Control-Allow-Origin', '*');
        this.http
            .get("http://localhost:3000/app/json/jsonData.json", {
            headers: headersObj
        })
            .map(function (response) {
            console.log(response.json().data);
        })
            .catch(function (rr) {
            alert();
            console.log(rr);
            return rr;
        }).subscribe(function (heroes) { return console.log(heroes); }, function (error) { return console.log(error); });
    };
    return MyFcaService;
}());
MyFcaService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], MyFcaService);
exports.MyFcaService = MyFcaService;
//# sourceMappingURL=app.component.service.js.map