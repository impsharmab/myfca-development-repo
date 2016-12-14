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
var router_1 = require("@angular/router");
var http_1 = require("@angular/http");
var app_component_service_1 = require("./app.component.service");
require("./rxjs-operators");
var Rx_1 = require("rxjs/Rx");
var AppComponent = (function () {
    function AppComponent(service, router, http) {
        this.service = service;
        this.router = router;
        this.http = http;
        this.sampleUsers = [];
        this.userdata = {};
    }
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.service.getNewServiceJSON().subscribe(function (resUserData) {
            _this.userdata["userID"] = resUserData["userID"];
            _this.userdata["name"] = resUserData["name"];
            _this.userdata["email"] = resUserData["email"];
            _this.userdata["access"] = resUserData["access"];
            _this.service.setUserData(_this.userdata);
            _this.menu = resUserData["menus"];
            _this.banners = resUserData["banners"];
            _this.tilesArray = resUserData.dashboard[0]["tiles"];
            _this.tilesArray.sort(function (one, two) {
                return one.tileOrder - two.tileOrder;
            });
        });
        /* Login Validation */
        this.user = {
            username: '',
            password: '',
        },
            /* Login Validation End */
            /** User Details
             * Get User details tiles for the dashboard
             */
            this.getUsersDetail();
    };
    AppComponent.prototype.login = function (username, password) {
        // Call the show method to display the widget.
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.post("app/userdetail.json", { username: username, password: password }, options)
            .catch(this.handleError);
    };
    ;
    //HTTP Response Error
    AppComponent.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        var errMsg;
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Rx_1.Observable.throw(errMsg);
    };
    AppComponent.prototype.getData = function () { };
    ;
    AppComponent.prototype.getUsersDetail = function () {
        var _this = this;
        this.service.getUsers()
            .subscribe(function (resUserData) { return _this.sampleUsers = resUserData; });
        // console.log(this.sampleUsers)
    };
    AppComponent.prototype.save = function (model, isValid) {
        // call API to save customer
        console.log(model, isValid);
    };
    return AppComponent;
}());
AppComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'my-app',
        //templateUrl: './app.component.html',
        templateUrl: './login.html',
        providers: [app_component_service_1.MyFcaService]
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService, router_1.Router, http_1.Http])
], AppComponent);
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map