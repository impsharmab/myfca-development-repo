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
var admin_service_1 = require("../../services/admin-services/admin.service");
var cookies_service_1 = require("angular2-cookie/services/cookies.service");
var TestAdminComponent = (function () {
    function TestAdminComponent(adminService, cookieService, router) {
        this.adminService = adminService;
        this.cookieService = cookieService;
        this.router = router;
    }
    TestAdminComponent.prototype.ngOnInit = function () {
        this.emulateuser = {
            sid: ''
        };
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
        this.setCookie();
        this.getCookie();
        $('#accordion').collapse({
            toggle: false
        });
        $(function () {
            var availablePCs = ["Executive", "BC", "District Manager", "Dealer", "Manager", "Participant"];
            var availableBCs = ["CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"];
            $("#position-code-filter-input").autocomplete({
                source: availablePCs
            });
            $("#position-code-filter-input-2").autocomplete({
                source: availablePCs
            });
            $("#business-center-input").autocomplete({
                source: availableBCs
            });
            $("#business-center-filter-input").autocomplete({
                source: availableBCs
            });
            var projects = [
                {
                    value: "feature1",
                    label: "Feature 1",
                    icon: "Feature1.jpg"
                },
                {
                    value: "feature2",
                    label: "Feature 2",
                    icon: "Feature2.jpg"
                },
                {
                    value: "feature3",
                    label: "Feature 3",
                    icon: "Feature3.jpg"
                }
            ];
            $("#project").autocomplete({
                minLength: 0,
                source: projects,
                focus: function (event, ui) {
                    $("#project").val(ui.item.label);
                    return false;
                },
                select: function (event, ui) {
                    $("#project").val(ui.item.label);
                    $("#project-id").val(ui.item.value);
                    $("#project-icon").attr("src", "app/components/admin/images/" + ui.item.icon);
                    return false;
                }
            })
                .autocomplete("instance")._renderItem = function (ul, item) {
                return $("<li>")
                    .append("<div>" + item.label + "</div>")
                    .appendTo(ul);
            };
            $('#positionCodeImage').magicSuggest({
                data: ["Executive", "BC", "District Manager", "Dealer", "Manager", "Participant"]
            });
            $('#businessCenterImage').magicSuggest({
                data: ["NAT", "CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"]
            });
        });
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
    TestAdminComponent.prototype.setCookie = function (name) {
        this.cookieService.put('test', "test test test");
    };
    TestAdminComponent.prototype.getCookie = function (name) {
        var y = this.cookieService.get('test');
        // alert(y)
    };
    TestAdminComponent.prototype.emulateUser = function () {
        var _this = this;
        this.adminService.getEmulateUserData(this.emulateuser.sid).subscribe(function (emulateUserData) {
            _this.emulateUserData = emulateUserData;
            debugger;
            console.log(emulateUserData);
            if (emulateUserData["item"].length > 0) {
                // this.adminService.setEmulateUserData(this.emulateUserData);
                var adminToken = _this.cookieService.get("token");
                _this.cookieService.put("adminToken", adminToken);
                _this.cookieService.put("token", emulateUserData.item);
                var url = ["login"];
                _this.router.navigate(url);
            }
        });
    };
    TestAdminComponent.prototype.endEmulateUser = function () {
        this.cookieService.get("adminToken");
        this.adminService.setEndEmulateUserData(this.endEmulateUserData);
        var poscodes = this.emulateUserData.positionCode;
        var delcodes = this.emulateUserData.dealerCode;
        // this.cookieService.put("selectedCodeData", JSON.stringify(
        //     {
        //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
        //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
        //     }))
        sessionStorage.setItem("selectedCodeData", JSON.stringify({
            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
        }));
        var url = ["myfcadashboard"];
        this.router.navigate(url);
    };
    return TestAdminComponent;
}());
TestAdminComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-admin",
        templateUrl: "./test-admin-uploadimage.html"
    }),
    __metadata("design:paramtypes", [admin_service_1.AdminService, cookies_service_1.CookieService, router_1.Router])
], TestAdminComponent);
exports.TestAdminComponent = TestAdminComponent;
//# sourceMappingURL=test-admin.component.js.map