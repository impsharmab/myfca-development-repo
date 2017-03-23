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
var admin_service_1 = require("../../services/admin-services/admin.service");
var Observable_1 = require("rxjs/Observable");
var forms_1 = require("@angular/forms");
var AdminComponent = (function () {
    // group dropdown options
    function AdminComponent(http, adminService, elRef) {
        this.http = http;
        this.adminService = adminService;
        this.elRef = elRef;
        this.banners = new Array;
        // public sid: any;
        this.edited = false;
        this.getAdminResponseUrl = './app/resources/json/admin-data.json';
        this.selectedCheckBox = {};
        this.submitted = {};
        this.opt = {};
        this.headerOpt = {};
        this.clickSuccessMessage = '';
        this.group = [
            {
                id: 1,
                label: "Select Language",
                value: 0
            },
            {
                id: 2,
                label: "Group 1",
                value: 1
            },
            {
                id: 3,
                label: "Group 2",
                value: 2
            },
        ];
        this.selectGroup = this.group[0];
        // Role dropdown
        this.role = [
            {
                id: 1,
                label: "Executive",
                value: 0
            },
            {
                id: 2,
                label: "BC",
                value: 1
            },
            {
                id: 3,
                label: "District",
                value: 2
            },
            {
                id: 4,
                label: "Dealer",
                value: 3
            },
            {
                id: 5,
                label: "Manager",
                value: 4
            },
            {
                id: 6,
                label: "Participant",
                value: 5
            },
        ];
        this.selectRole = this.role[0];
    }
    AdminComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        // To set attribute checkbox default value false
        for (var i = 1; i <= 30; i++) {
            this.opt['mseropt' + i] = false;
        }
        for (var i = 1; i <= 7; i++) {
            this.headerOpt[i] = false;
        }
        this.admindata = {
            role: "",
            group: "",
            header1: '', header2: '', header3: '', header4: '', header5: '', header6: '', header7: '',
            mseropt1: "", mseropt2: "", mseropt3: "", mseropt4: "", mseropt5: "", mseropt6: "", mseropt7: "",
            mseropt8: "", mseropt9: "", mseropt10: "", mseropt11: "", mseropt12: "", mseropt13: "", mseropt14: "",
            mseropt15: "", mseropt16: "", mseropt17: "", mseropt18: "", mseropt19: "", mseropt20: "", mseropt21: "",
            mseropt22: "", mseropt23: "", mseropt24: "", mseropt25: "", mseropt26: "", mseropt27: "", mseropt28: "",
            mseropt29: "", mseropt30: "",
        };
    };
    // onClickEvent(e) {
    //     console.log(e);
    //     e.stopPropagation();
    // }
    AdminComponent.prototype.onSubmit = function (f) {
        var _this = this;
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        if ($("#header1").is(":checked")) {
            f.value.header1 = true;
        }
        else {
            f.value.header1 = false;
        }
        if ($("#header2").is(":checked")) {
            f.value.header2 = true;
        }
        else {
            f.value.header2 = false;
        }
        if ($("#header3").is(":checked")) {
            f.value.header3 = true;
        }
        else {
            f.value.header3 = false;
        }
        if ($("#header4").is(":checked")) {
            f.value.header4 = true;
        }
        else {
            f.value.header4 = false;
        }
        if ($("#header5").is(":checked")) {
            f.value.header5 = true;
        }
        else {
            f.value.header5 = false;
        }
        if ($("#header6").is(":checked")) {
            f.value.header6 = true;
        }
        else {
            f.value.header6 = false;
        }
        if ($("#header7").is(":checked")) {
            f.value.header7 = true;
        }
        else {
            f.value.header7 = false;
        }
        var FormData = JSON.stringify(f.value, null, 2);
        console.log("FormData...." + FormData);
        this.adminService.getAdminData(FormData).subscribe(function (resUserData) {
            resUserData = _this.admindata = (resUserData);
        });
    };
    AdminComponent.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    AdminComponent.prototype.OnSave = function (f) {
        document.body.scrollTop = 0;
        this.clickSuccessMessage = "Success! Data saved successfully";
    };
    AdminComponent.prototype.OnCancel = function (admin, e) {
        var c = confirm("Do you want to Cancel");
        if (c === true) {
            event.preventDefault();
            this.f.reset();
        }
        else {
            event.preventDefault();
        }
    };
    AdminComponent.prototype.onClick = function () {
        $(event.target).siblings('div').toggle(500);
    };
    AdminComponent.prototype.getAccordionData = function () {
        this.accordionData = [
            {
                "accordionTitle": "MSER",
                'order': "1",
                data: [
                    { "name": "mseropt1", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt2", "title": "Excellence Cards Awards MTD", 'checked': false },
                    { "name": "mseropt3", "title": "Excellence Cards Awards YTD", 'checked': false },
                ]
            },
            {
                "accordionTitle": "MSER Graph",
                "order": "2",
                data: [
                    { "name": "mseropt4", "title": "uConnect", 'checked': false },
                    { "name": "mseropt5", "title": "wiAdvisor", 'checked': false },
                    { "name": "mseropt6", "title": "Express Lane", 'checked': false },
                    { "name": "mseropt7", "title": "Parts Counter", 'checked': false },
                    { "name": "mseropt8", "title": "Magenti Marelli", 'checked': false },
                    { "name": "mseropt9", "title": "MVP", 'checked': false },
                    { "name": "mseropt10", "title": "Mopar Parts", 'checked': false },
                ]
            },
            {
                "accordionTitle": "Top Tech",
                "order": "3",
                data: [
                    { "name": "mseropt11", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt12", "title": "Top Performers", 'checked': false },
                    { "name": "mseropt13", "title": "Count of Surveys QTD", 'checked': false },
                    { "name": "mseropt14", "title": "Average Quarterly Survey Score", 'checked': false }
                ]
            },
            {
                "accordionTitle": "Top Advisor",
                "order": "4",
                data: [
                    { "name": "mseropt15", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt16", "title": "Count of Surveys QTD", 'checked': false },
                    { "name": "mseropt17", "title": "Incentive Eligible Advisors", 'checked': false },
                    { "name": "mseropt18", "title": "Average Quarterly Survey Score", 'checked': false }
                ]
            },
            {
                "accordionTitle": "Top Tech/Advisor Graph",
                "order": "5",
                data: [
                    { "name": "mseropt19", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt20", "title": "Count of Surveys QTD", 'checked': false },
                    { "name": "mseropt21", "title": "Incentive Eligible Advisors", 'checked': false },
                    { "name": "mseropt22", "title": "Average Quarterly Survey Score", 'checked': false }
                ]
            },
            {
                "accordionTitle": "Retention",
                "order": "6",
                data: [
                    { "name": "mseropt23", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt24", "title": "Count of Surveys QTD", 'checked': false },
                    { "name": "mseropt25", "title": "Incentive Eligible Advisors", 'checked': false },
                    { "name": "mseropt26", "title": "Average Quarterly Survey Score", 'checked': false }
                ]
            },
            {
                "accordionTitle": "Retention Graph",
                "order": "7",
                data: [
                    { "name": "mseropt27", "title": "Total Dealers Enrolled", 'checked': false },
                    { "name": "mseropt28", "title": "Count of Surveys QTD", 'checked': false },
                    { "name": "mseropt29", "title": "Incentive Eligible Advisors", 'checked': false },
                    { "name": "mseropt30", "title": "Average Quarterly Survey Score", 'checked': false }
                ]
            }
        ];
    };
    // toggleItem(chapter) {
    //     console.log(chapter);
    //     chapter.checked = !chapter.checked
    //     console.log(chapter);
    // }
    AdminComponent.prototype.toggleAll = function (ev) {
        this.parentId = ev.target.value;
        var dataLength = this.accordionData.length;
        if (this.parentId === this.parentId) {
            if (this.parentId === "1") {
                var SubDataLeng = 3;
            }
            if (this.parentId === "2") {
                var SubDataLeng = 7;
            }
            if (this.parentId === "3") {
                var SubDataLeng = 4;
            }
            if (this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
                var SubDataLeng = 4;
            }
            // if ($('input.header').is(':checked')) {
            if (ev.target.checked === true) {
                for (var j = 0; j <= SubDataLeng; j++) {
                    if ($(".card-block" + this.parentId + " input#mseropt" + j).prop('checked', false)) {
                        $(".card-block" + this.parentId + " input#mseropt" + j).prop('checked', true);
                    }
                }
            }
            else {
                for (var j = 0; j <= SubDataLeng; j++) {
                    if ($(".card-block" + this.parentId + " input#mseropt" + j).prop('checked', true)) {
                        $(".card-block" + this.parentId + " input#mseropt" + j).prop('checked', false);
                    }
                }
            }
        }
    };
    AdminComponent.prototype.uncheckParent = function (ev) {
        this.parentId = ev.target.value;
        var totoalChkedChbox = $('.card-block' + this.parentId + '  input:checkbox:checked').length;
        if (this.parentId === "1") {
            var SubDataLeng = 3;
        }
        if (this.parentId === "2") {
            var SubDataLeng = 7;
        }
        if (this.parentId === "3") {
            var SubDataLeng = 4;
        }
        if (this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
            var SubDataLeng = 4;
        }
        if (ev.currentTarget.checked == true) {
            if (ev.target.value) {
                //   if($("#header"+ev.target.value).is(":checked")) {
                $("#header" + ev.target.value).prop("checked", true);
            }
        }
        if (totoalChkedChbox === 0) {
            $("#header" + ev.target.value).prop("checked", false);
        }
    };
    AdminComponent.prototype.onBlurMethod = function (sid, body) {
        console.log(this.sid);
        if (this.sid) {
            this.getAccordionData();
        }
        var bodyString = JSON.stringify(body); // Stringify payload
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
        var options = new http_1.RequestOptions({ headers: headers }); // Create a request option
        this.edited = true;
        var adminData = "./app/resources/json/serviceJson/admin-data.json";
        var AdminDataThroughService = this.http.post(adminData, body, options)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
        return AdminDataThroughService;
    };
    // getAdminData() {
    //     var adminData = "./app/resources/json/serviceJson/admin-data.json";
    //     var AdminDataThroughService = this.http.get(adminData)
    //         .map((response: Response) => response.json())
    //         .catch(this.handleError);
    //     return AdminDataThroughService;
    // }
    AdminComponent.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    return AdminComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], AdminComponent.prototype, "data", void 0);
__decorate([
    core_1.ViewChild('f'),
    __metadata("design:type", forms_1.FormGroup)
], AdminComponent.prototype, "f", void 0);
AdminComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-admin",
        templateUrl: "./admin.html"
    }),
    __metadata("design:paramtypes", [http_1.Http, admin_service_1.AdminService, core_1.ElementRef])
], AdminComponent);
exports.AdminComponent = AdminComponent;
//# sourceMappingURL=admin.component.js.map