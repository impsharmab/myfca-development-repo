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
var app_component_service_1 = require("../../app.component.service");
var loc1 = window.location.pathname;
var nloc1 = loc1.slice(0, -10);
var HeaderSection = (function () {
    function HeaderSection(service) {
        this.service = service;
        this.myUsers = {};
        console.log("new test" + nloc1);
    }
    HeaderSection.prototype.ngOnInit = function () {
        this.getUsersDetail();
    };
    HeaderSection.prototype.getUsersDetail = function () {
        var _this = this;
        //  console.log("test" +nloc1);
        this.service.getUsersData()
            .subscribe(function (resUserData) { return _this.myUsers = resUserData; });
    };
    return HeaderSection;
}());
HeaderSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-header",
        // npm url
        templateUrl: "./header.html"
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService])
], HeaderSection);
exports.HeaderSection = HeaderSection;
//# sourceMappingURL=app.header.component.js.map