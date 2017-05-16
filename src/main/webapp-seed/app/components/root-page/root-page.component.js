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
var dashboard_body_component_1 = require("../dashboard-body/dashboard-body.component");
var RootPageComponent = (function () {
    function RootPageComponent(router, http) {
        this.router = router;
        this.http = http;
        this.sampleUsers = [];
        this.userdata = {};
    }
    RootPageComponent.prototype.ngOnInit = function () {
        this.userdata = JSON.parse(sessionStorage.getItem("CurrentUser"));
        this.tilesArray = JSON.parse(sessionStorage.getItem("tiles"));
    };
    RootPageComponent.prototype.onProfileChange = function () {
        //  alert();
        this.bodyContent.reload();
    };
    return RootPageComponent;
}());
__decorate([
    core_1.ViewChild("bodyContent"),
    __metadata("design:type", dashboard_body_component_1.DashboardBodyComponent)
], RootPageComponent.prototype, "bodyContent", void 0);
RootPageComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: '',
        templateUrl: 'rootpage.html',
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof router_1.Router !== "undefined" && router_1.Router) === "function" && _a || Object, typeof (_b = typeof http_1.Http !== "undefined" && http_1.Http) === "function" && _b || Object])
], RootPageComponent);
exports.RootPageComponent = RootPageComponent;
var _a, _b;
//# sourceMappingURL=root-page.component.js.map