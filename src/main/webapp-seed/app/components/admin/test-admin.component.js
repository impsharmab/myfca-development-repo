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
        this.errorUploadImageMessage = "";
        this.imagelist = [];
        this.projects = [];
        this.allBannerTableData = [];
        this.editBannerDatum = {};
        this.deleteBannerDatum = {};
    }
    TestAdminComponent.prototype.ngOnInit = function () {
        var self = this;
        this.emulateuser = {
            sid: ''
        };
        this.getAllBannerData();
        this.uploadImage = {
            dashBoardBannersID: 0,
            image: "",
            roleId: 0,
            selectedRoleId: [],
            orderBy: 0,
            bc: [],
            link: "",
            createdDate: new Date,
            createdBy: "",
            updatedDate: new Date,
            updatedBy: "",
            delFlag: ""
        };
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
        this.getImageList(self);
        // $(document).ready(function () {
        //     $('#example').DataTable();
        //     "searching": false
        // });
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
            var id = $('#roleId').magicSuggest({
                allowFreeEntries: false,
                valueField: 'roleid',
                displayField: 'name',
                data: [{ roleid: 1, name: "Executive" }, { roleid: 12, name: "BC" }, { roleid: 11, name: "District Manager" }, { roleid: 10, name: "Dealer" }, { roleid: 5, name: "Manager" }, { roleid: 9, name: "Participant" }]
            });
            $(id).on('selectionchange', function (e, m) {
                self.setRole(this.getValue());
            });
            var bc = $('#bc').magicSuggest({
                allowFreeEntries: false,
                data: ["NAT", "CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"]
            });
            $(bc).on('selectionchange', function (e, m) {
                self.setBC(this.getValue());
            });
        });
    };
    TestAdminComponent.prototype.getAllBannerData = function () {
        var _this = this;
        this.adminService.getAllBannerData().subscribe(function (allBannerTableData) {
            _this.allBannerTableData = allBannerTableData;
            console.log(_this.allBannerTableData);
        });
    };
    TestAdminComponent.prototype.testMethod = function () {
        for (var k = 0; k < this.imagelist.length; k++) {
            this.projects.push({
                value: this.imagelist[k],
                label: this.imagelist[k],
                icon: this.imagelist[k]
            });
        }
        // var projects = [
        //     {
        //         value: "JeepExpert.jpg",
        //         label: "JeepExpert.jpg",
        //         icon: "JeepExpert.jpg"
        //     },
        //     {
        //         value: "feature2",
        //         label: "Feature 2",
        //         icon: "Feature2.jpg"
        //     },
        //     {
        //         value: "feature3",
        //         label: "Feature 3",
        //         icon: "Feature3.jpg"
        //     }
        // ];
        $("#project").autocomplete({
            allowFreeEntries: false,
            minLength: 0,
            source: this.projects,
            focus: function (event, ui) {
                $("#project").val(ui.item.label);
                return false;
            },
            select: function (event, ui) {
                $("#project").val(ui.item.label);
                $("#project-id").val(ui.item.value);
                //$("#project-icon").attr("src", "https://test.myfcarewards.com/myfcarewards/services/loadrsc?id=" + ui.item.icon);
                $("#project-icon").attr("src", "./services/loadrsc?id=" + ui.item.icon);
                return false;
            }
        })
            .autocomplete("instance")._renderItem = function (ul, item) {
            return $("<li>")
                .append("<div>" + item.label + "</div>")
                .appendTo(ul);
        };
    };
    TestAdminComponent.prototype.getImageList = function (self) {
        var _this = this;
        this.adminService.getImageList().subscribe(function (imagelist) {
            _this.imagelist = imagelist;
            console.log(imagelist);
            console.log(_this.imagelist);
            self.testMethod();
        });
    };
    TestAdminComponent.prototype.setRole = function (b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b);
        }
        else {
            a = b;
        }
        this.uploadImage.selectedRoleId = a;
    };
    TestAdminComponent.prototype.setBC = function (b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b);
        }
        else {
            a = b;
        }
        this.uploadImage.bc = a;
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
    //dealer=10, partic=9, execut=1, bc=12, dis=11, dealman=5
    //"Executive", "BC", "District Manager", "Dealer", "Manager", "Participant"
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
    TestAdminComponent.prototype.addBannerImage = function () {
        var _this = this;
        for (var i = 0; i < this.uploadImage.bc.length; i++) {
            for (var j = 0; j < this.uploadImage.selectedRoleId.length; j++) {
                this.adminService.addBanner(this.uploadImage.selectedRoleId[j], this.uploadImage.bc[i], this.uploadImage.orderBy, this.uploadImage.image).subscribe(function (addBannerData) {
                    _this.addBannerData = addBannerData;
                    // debugger
                    // console.log(addBannerData)
                    // alert(addBannerData)
                }, function (error) {
                    alert("Error in uploading images");
                    _this.errorUploadImageMessage = "Error in uploading images";
                });
            }
        }
    };
    TestAdminComponent.prototype.editBannerData = function (editBannerObj) {
        var _this = this;
        debugger;
        this.adminService.editBannerData(editBannerObj).subscribe(function (editBannerDatum) {
            _this.editBannerDatum = editBannerDatum;
            console.log(editBannerDatum);
        });
    };
    TestAdminComponent.prototype.deleteBannerData = function (dashBoardBannersID) {
        this.adminService.deleteBannerData(dashBoardBannersID);
        // .subscribe(
        //     (deleteBannerDatum) => {
        //         this.deleteBannerDatum = deleteBannerDatum;
        //         console.log(deleteBannerDatum)
        //     }
        //)
    };
    return TestAdminComponent;
}());
TestAdminComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-admin",
        templateUrl: "./test-admin-uploadimage.html"
    }),
    __metadata("design:paramtypes", [admin_service_1.AdminService, typeof (_a = typeof cookies_service_1.CookieService !== "undefined" && cookies_service_1.CookieService) === "function" && _a || Object, typeof (_b = typeof router_1.Router !== "undefined" && router_1.Router) === "function" && _b || Object])
], TestAdminComponent);
exports.TestAdminComponent = TestAdminComponent;
var _a, _b;
//# sourceMappingURL=test-admin.component.js.map