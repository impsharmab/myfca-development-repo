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
var app_component_service_1 = require("../../app.component.service");
var Login = (function () {
    function Login(service, router, http) {
        this.service = service;
        this.router = router;
        this.http = http;
        this.sampleUsers = [];
        this.userdata = {};
        this.log = false;
    }
    Login.prototype.ngOnInit = function () {
        this.user = {
            username: '',
            password: ''
        };
    };
    Login.prototype.login = function () {
        var _this = this;
        this.service.getNewServiceJSON(this.user.username, this.user.password).subscribe(function (resUserData) {
            alert(resUserData["userID"]);
            if (resUserData["error"] === "" && resUserData["error"] !== null) {
                _this.userdata["userID"] = resUserData["userID"];
                _this.userdata["name"] = resUserData["name"];
                _this.userdata["email"] = resUserData["email"];
                _this.userdata["access"] = resUserData["access"];
                _this.service.setUserData(_this.userdata);
                _this.menu = resUserData["menus"];
                _this.banners = resUserData["banners"];
                // if(resUserData.dashboard.length>0){
                _this.tilesArray = resUserData.dashboard[0]["tiles"];
                _this.tilesArray.sort(function (one, two) {
                    return one.tileOrder - two.tileOrder;
                });
                _this.service.setTiles(_this.tilesArray);
                //    this.log = true;
                var url = ["myfcadashboard"];
                _this.router.navigate(url);
            }
            else {
                var msg = JSON.parse(resUserData["error"])["error"];
                alert(msg);
            }
        });
    };
    Login.prototype.getData = function () {
    };
    ;
    Login.prototype.save = function (model, isValid) {
        // call API to save customer
        console.log(model, isValid);
    };
    return Login;
}());
Login = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: './new-loginForm.html',
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService, router_1.Router, http_1.Http])
], Login);
exports.Login = Login;
//# sourceMappingURL=login.component.js.map