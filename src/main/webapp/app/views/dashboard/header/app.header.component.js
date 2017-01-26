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
var app_component_service_1 = require("../../../app.component.service");
var HeaderSection = (function () {
    function HeaderSection(http, service) {
        this.http = http;
        this.service = service;
        this.banners = new Array;
        this.getBanners();
        //console.log("banners: "+this.banners)
    }
    HeaderSection.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        //    document.getElementById("profileModel").click();
    };
    HeaderSection.prototype.getBanners = function () {
        var _this = this;
        this.service.getBanners().subscribe(function (banners) {
            _this.banners = banners;
            console.log("banners1 : " + banners.fileName);
        });
    };
    return HeaderSection;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderSection.prototype, "data", void 0);
HeaderSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-header",
        templateUrl: "./header-bootstrap.html",
        styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"]
    }),
    __metadata("design:paramtypes", [http_1.Http, app_component_service_1.MyFcaService])
], HeaderSection);
exports.HeaderSection = HeaderSection;
//# sourceMappingURL=app.header.component.js.map