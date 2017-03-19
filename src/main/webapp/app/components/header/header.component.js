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
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var router_1 = require("@angular/router");
var HeaderComponent = (function () {
    function HeaderComponent(http, headerService, modalService, router) {
        this.http = http;
        this.headerService = headerService;
        this.modalService = modalService;
        this.router = router;
        this.profileChange = new core_1.EventEmitter();
        this.banners = new Array;
        this.poscodes = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        this.delcodes = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
        this.booleanAdmin = JSON.parse(sessionStorage.getItem("CurrentUser")).admin;
    }
    HeaderComponent.prototype.positionCodeCancel = function () {
    };
    HeaderComponent.prototype.positionCodeSubmit = function () {
        alert("1");
        this.profileChange.emit("");
    };
    HeaderComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
    };
    HeaderComponent.prototype.contactUs = function () {
        this.modalService.open(this.contactModal, { windowClass: 'contact-us' });
    };
    // private profileChangeEvent(value: any) {
    //     this.profileChange.emit(value);
    // }
    HeaderComponent.prototype.logout = function () {
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.clear();
        var loginUrl = ["login"];
        this.router.navigate(loginUrl);
    };
    HeaderComponent.prototype.admin = function () {
        var adminUrl = ["admin"];
        this.router.navigate(adminUrl);
    };
    HeaderComponent.prototype.dashboardPage = function () {
        var dashboardUrl = ["myfcadashboard"];
        this.router.navigate(dashboardUrl);
    };
    HeaderComponent.prototype.dropdownPositionCode = function () {
        this.modalService.open(this.positioncodeModal, { windowClass: 'position-dealercode' });
    };
    return HeaderComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "data", void 0);
__decorate([
    core_1.Output("onProfileChange"),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "profileChange", void 0);
__decorate([
    core_1.ViewChild("contactModal"),
    __metadata("design:type", core_1.TemplateRef)
], HeaderComponent.prototype, "contactModal", void 0);
__decorate([
    core_1.ViewChild("positioncodeModal"),
    __metadata("design:type", core_1.TemplateRef)
], HeaderComponent.prototype, "positioncodeModal", void 0);
HeaderComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-header",
        templateUrl: "./header.html"
    }),
    __metadata("design:paramtypes", [http_1.Http, header_service_1.HeaderService, ng_bootstrap_1.NgbModal, router_1.Router])
], HeaderComponent);
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map