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
var banner_service_1 = require("../../services/banner-services/banner.service");
var BannerComponent = (function () {
    function BannerComponent(http, bannerService) {
        this.http = http;
        this.bannerService = bannerService;
        this.banners = new Array;
        this.getBanners();
        //console.log("banners: "+this.banners)
    }
    BannerComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        //    document.getElementById("profileModel").click();
    };
    BannerComponent.prototype.getBanners = function () {
        var _this = this;
        this.bannerService.getBanners().subscribe(function (banners) {
            _this.banners = banners;
            //console.log("banners1 : " + banners.fileName)
        });
    };
    return BannerComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], BannerComponent.prototype, "data", void 0);
BannerComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-banner",
        templateUrl: "./banner.html"
    }),
    __metadata("design:paramtypes", [http_1.Http, banner_service_1.BannerService])
], BannerComponent);
exports.BannerComponent = BannerComponent;
//# sourceMappingURL=banner.component.js.map