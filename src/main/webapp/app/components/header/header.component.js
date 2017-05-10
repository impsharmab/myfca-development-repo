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
var cookies_service_1 = require("angular2-cookie/services/cookies.service");
var HeaderComponent = (function () {
    function HeaderComponent(activeModal, http, headerService, modalService, router, cookieService) {
        this.activeModal = activeModal;
        this.http = http;
        this.headerService = headerService;
        this.modalService = modalService;
        this.router = router;
        this.cookieService = cookieService;
        this.profileChange = new core_1.EventEmitter();
        this.banners = new Array;
        this.adminToken = "";
        this.poscodes = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        this.delcodes = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
        this.booleanAdmin = JSON.parse(sessionStorage.getItem("CurrentUser")).admin;
        this.booleanAdminToken = this.cookieService.get("adminToken");
    }
    // private poscodes: any = JSON.parse(this.cookieService.get("CurrentUser")).positionCode;
    // private delcodes: any = JSON.parse(this.cookieService.get("CurrentUser")).dealerCode;
    // private booleanAdmin: any = JSON.parse(this.cookieService.get("CurrentUser")).admin;
    //  var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
    //     var positioncodes: any = JSON.parse(this.cookieService.get("selectedCodeData")).selectedPositionCode;
    //     var dealerlcodes: any = JSON.parse(this.cookieService.get("selectedCodeData")).selectedDealerCode;
    HeaderComponent.prototype.positionCodeCancel = function () {
        this.positioncodeModal.close();
    };
    HeaderComponent.prototype.positionCodeSubmit = function (c) {
        // alert("1");	
        c();
        this.profileChange.emit("");
    };
    HeaderComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        if (!this.enablePointer) {
            $(document).ready(function () {
                $("#enablePointer").css("cursor", "pointer");
                $("#enablePointer").css("text-decoration", "underline");
            });
        }
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
        // this.cookieService.remove('CurrentUser');
        // this.cookieService.remove('selectedCodeData');
        this.cookieService.removeAll();
        // let loginUrl = ["login"]
        // this.router.navigate(loginUrl);
        window.open("https://dealerconnect.chrysler.com/login/login.html", '_self');
    };
    HeaderComponent.prototype.admin = function () {
        var adminUrl = ["admin"];
        this.router.navigate(adminUrl);
    };
    HeaderComponent.prototype.dashboardPage = function () {
        var dashboardUrl = ["/myfcadashboard"];
        //Router.navigate(['/myRoute',{someProperty:"SomeValue"}]
        //this.router.navigate([dashboardUrl, {"flag":"flag"}]);
        this.router.navigate(dashboardUrl);
    };
    HeaderComponent.prototype.dropdownPositionCode = function () {
        this.modalService.open(this.positioncodeModal, { windowClass: 'position-dealercode' });
    };
    HeaderComponent.prototype.profile = function () {
        var profileUrl = ["profile"];
        this.router.navigate(profileUrl);
    };
    HeaderComponent.prototype.endEmulation = function () {
        var adminToken = this.cookieService.get("adminToken");
        this.cookieService.remove("adminToken");
        this.cookieService.remove("token");
        this.cookieService.removeAll();
        sessionStorage.clear();
        window.sessionStorage.clear();
        //document.sessionStorage.clear();
        document;
        this.cookieService.put("token", adminToken);
        debugger;
        // this.cookieService.put(adminToken, "")
        // this.adminToken = adminToken;
        var url = ["login"];
        this.router.navigate(url);
        // this.booleanEndEmulation();
        // alert(this.booleanAdminToken)
    };
    return HeaderComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "data", void 0);
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "retweetIconHide", void 0);
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], HeaderComponent.prototype, "enablePointer", void 0);
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
    __metadata("design:type", ng_bootstrap_1.NgbModalRef)
], HeaderComponent.prototype, "positioncodeModal", void 0);
HeaderComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-header",
        templateUrl: "./header.html"
    }),
    __metadata("design:paramtypes", [ng_bootstrap_1.NgbActiveModal,
        http_1.Http,
        header_service_1.HeaderService,
        ng_bootstrap_1.NgbModal,
        router_1.Router,
        cookies_service_1.CookieService])
], HeaderComponent);
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map