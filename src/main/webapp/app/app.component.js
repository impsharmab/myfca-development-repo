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
var AppComponent = (function () {
    function AppComponent(service, router, http) {
        this.service = service;
        this.router = router;
        this.http = http;
        this.sampleUsers = [];
        this.userdata = {};
        this.log = false;
    }
    AppComponent.prototype.ngOnInit = function () {
        //   this.service.getNewServiceJSON().subscribe(
        //   (resUserData) => {
        //     this.userdata["userID"] = resUserData["userID"];
        //     this.userdata["name"] = resUserData["name"];
        //     this.userdata["email"] = resUserData["email"];
        //     this.userdata["access"] = resUserData["access"];
        //     this.service.setUserData(this.userdata);
        //     this.menu = resUserData["menus"];
        //     this.banners = resUserData["banners"]
        //     this.tilesArray = resUserData.dashboard[0]["tiles"];
        //     this.tilesArray.sort((one, two) => {
        //       return one.tileOrder - two.tileOrder;
        //     })
        //     this.service.setTiles(this.tilesArray);
        //   }
        // )
        /* Login Validation */
        this.user = {
            username: '',
            password: '',
        };
        /* Login Validation End */
        /** User Details
         * Get User details tiles for the dashboard
         */
    };
    AppComponent.prototype.login = function () {
        // Call the show method to display the widget.
        var _this = this;
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
        //  this.http.post("app/userdetail.json", { username, password }, options)
        //             .catch(this.handleError);
        this.service.getNewServiceJSON(this.user.username, this.user.password).subscribe(function (resUserData) {
            alert(resUserData["userID"]);
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
            _this.log = true;
            var url = ["myfcadashboard"];
            _this.router.navigate(url);
        });
    };
    //HTTP Response Error
    AppComponent.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        var errMsg;
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err + ";\n        } else {\n            errMsg = error.message ? error.message : error.toString();\n        }\n        console.error(errMsg);\n        return Observable.throw(errMsg);\n    }\n    \n\n    getData(){};\n    getUsersDetail() {\n        this.service.getUsers()\n            .subscribe(\n                resUserData => this.sampleUsers = resUserData\n\n            )\n           // console.log(this.sampleUsers)\n    }\n\n    save(model: User, isValid: boolean) {\n        // call API to save customer\n        console.log(model, isValid);\n    }\n\n}\n";
        }
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