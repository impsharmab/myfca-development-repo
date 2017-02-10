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
var header_service_1 = require("../../services/header-services/header.service");
var HeaderComponent = (function () {
    function HeaderComponent(http, headerService) {
        this.http = http;
        this.headerService = headerService;
        this.banners = new Array;
        this.getBanners();
        //console.log("banners: "+this.banners)
    }
    HeaderComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        //    document.getElementById("profileModel").click();
    };
    HeaderComponent.prototype.getBanners = function () {
        var _this = this;
        this.headerService.getBanners().subscribe(function (banners) {
            _this.banners = banners;
            //console.log("banners1 : " + banners.fileName)
        });
    };
    return HeaderComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "data", void 0);
HeaderComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-header",
        templateUrl: "./header.html"
    }),
    __metadata("design:paramtypes", [http_1.Http, header_service_1.HeaderService])
], HeaderComponent);
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map