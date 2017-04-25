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
var admin_service_1 = require("../../services/admin-services/admin.service");
var TestAdminComponent = (function () {
    function TestAdminComponent(adminService) {
        this.adminService = adminService;
    }
    TestAdminComponent.prototype.ngOnInit = function () {
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
    };
    TestAdminComponent.prototype.getPositionCode = function () {
        var _this = this;
        this.adminService.getPositionCode().subscribe(function (positioncode) {
            _this.positioncode = positioncode;
            // alert(positioncode[0])
        });
    };
    TestAdminComponent.prototype.getRoles = function () {
        var _this = this;
        this.adminService.getRoles().subscribe(function (roles) {
            _this.roles = roles;
            // alert(roles[0])
        });
    };
    TestAdminComponent.prototype.getAdminData = function () {
        var _this = this;
        this.adminService.getAdminData().subscribe(function (adminData) {
            _this.adminData = adminData.permissions;
            // this.data = this.adminData.data;
            // console.log(adminData.permissions[0].name)
        });
    };
    return TestAdminComponent;
}());
TestAdminComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-admin",
        templateUrl: "./test-admin.html"
    }),
    __metadata("design:paramtypes", [admin_service_1.AdminService])
], TestAdminComponent);
exports.TestAdminComponent = TestAdminComponent;
//# sourceMappingURL=test-admin.component.js.map