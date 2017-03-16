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
var positioncode_service_1 = require("../../services/positioncode-services/positioncode.service");
var dashboard_body_service_1 = require("../../services/dashboard-body-services/dashboard-body.service");
var PositionCodeComponent = (function () {
    function PositionCodeComponent(positionCodeService, dashboardBodyService) {
        this.positionCodeService = positionCodeService;
        this.dashboardBodyService = dashboardBodyService;
        this.pcode = [];
        this.dcode = [];
        this.codeData = { "selectedPositionCode": "", "selectedDealerCode": "" };
        this.poscodes = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        this.delcodes = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
    }
    PositionCodeComponent.prototype.ngOnInit = function () {
        //this.code.dealerCode=selectedPositionCode;
        this.code = {
            selectedPositionCode: '',
            selectedDealerCode: ''
        };
        this.code = this.positionCodeService.getCodeData();
        this.pcode = this.poscodes;
        this.dcode = this.delcodes;
        // this.defaultPositionDealerCode();
    };
    // defaultPositionDealerCode(): any {
    //     if (this.pcode.length == 0 || this.dcode.length == 0) {
    //         this.codeData["selectedPositionCode"] = 0;
    //         this.codeData["selectedDealerCode"] = 0;
    //     }
    //     else if (this.pcode.length > 0 || this.dcode.length >0) {
    //         this.code.positionCode=this.pcode[0];
    //         this.code.dealerCode=this.dcode[0];
    //         this.codeData["selectedPositionCode"] = this.pcode[0];
    //         this.codeData["selectedDealerCode"] = this.dcode[0];
    //     }        
    //     this.positionCodeService.setCodeData(this.codeData);
    // }
    PositionCodeComponent.prototype.selectPositionCode = function (poscode) {
        this.positionCodeService.setCodeData(this.code);
    };
    PositionCodeComponent.prototype.selectDealerCode = function (delcode) {
        this.positionCodeService.setCodeData(this.code);
    };
    PositionCodeComponent.prototype.submitSelectedCodes = function () {
        this.dashboardBodyService.getNumberOfTiltes();
        this.dashboardBodyService.getTilteJson();
        this.dashboardBodyService.getChartJson();
    };
    return PositionCodeComponent;
}());
PositionCodeComponent = __decorate([
    core_1.Component({
        selector: 'position-code',
        templateUrl: 'app/components/positioncode/positioncode.html',
    }),
    __metadata("design:paramtypes", [positioncode_service_1.PositionCodeService, dashboard_body_service_1.DashboardBodyService])
], PositionCodeComponent);
exports.PositionCodeComponent = PositionCodeComponent;
//# sourceMappingURL=positioncode.component.js.map