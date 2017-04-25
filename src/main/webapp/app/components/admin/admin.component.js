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
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var AdminComponent = (function () {
    // group dropdown options
    function AdminComponent(http, adminService, elRef, config) {
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
        this.isOpen = false;
        config.closeOthers = true;
        // config.type = 'info';
        this.group = [
            {
                id: 1,
                label: "Select Language",
                value: "Select"
            },
            {
                id: 2,
                label: "Group 1",
                value: "Group1"
            },
            {
                id: 3,
                label: "Group 2",
                value: "Group2"
            },
        ];
        this.selectGroup = this.group[0];
        // Role dropdown
        this.role = [
            {
                id: 1,
                label: "Executive",
                value: "executive"
            },
            {
                id: 2,
                label: "BC",
                value: "BC"
            },
            {
                id: 3,
                label: "District",
                value: "district"
            },
            {
                id: 4,
                label: "Dealer",
                value: "dealer"
            },
            {
                id: 5,
                label: "Manager",
                value: "manager"
            },
            {
                id: 6,
                label: "Participant",
                value: "participant"
            },
        ];
        this.selectRole = this.role[0];
    }
    AdminComponent.prototype.ngOnInit = function () {
        // this.getAccordionData();
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        // To set attribute checkbox default value false
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
    AdminComponent.prototype.onClickEvent = function (e) {
        // console.log(e);
        e.stopPropagation();
    };
    AdminComponent.prototype.onSubmit = function (f) {
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        var datas = {};
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
        if (f.value.mseropt1 == undefined || f.value.mseropt1 == null) {
            f.value.mseropt1 = false;
        }
        if (f.value.mseropt2 == undefined || f.value.mseropt2 == null) {
            f.value.mseropt2 = false;
        }
        if (f.value.mseropt3 == undefined || f.value.mseropt3 == null) {
            f.value.mseropt3 = false;
        }
        if (f.value.mseropt4 == undefined || f.value.mseropt4 == null) {
            f.value.mseropt4 = false;
        }
        if (f.value.mseropt5 == undefined || f.value.mseropt5 == null) {
            f.value.mseropt5 = false;
        }
        if (f.value.mseropt6 == undefined || f.value.mseropt6 == null) {
            f.value.mseropt6 = false;
        }
        if (f.value.mseropt7 == undefined || f.value.mseropt7 == null) {
            f.value.mseropt7 = false;
        }
        if (f.value.mseropt7 == undefined || f.value.mseropt7 == null) {
            f.value.mseropt7 = false;
        }
        if (f.value.mseropt8 == undefined || f.value.mseropt8 == null) {
            f.value.mseropt8 = false;
        }
        if (f.value.mseropt9 == undefined || f.value.mseropt9 == null) {
            f.value.mseropt9 = false;
        }
        if (f.value.mseropt10 == undefined || f.value.mseropt10 == null) {
            f.value.mseropt10 = false;
        }
        if (f.value.mseropt11 == undefined || f.value.mseropt11 == null) {
            f.value.mseropt11 = false;
        }
        if (f.value.mseropt12 == undefined || f.value.mseropt12 == null) {
            f.value.mseropt12 = false;
        }
        if (f.value.mseropt13 == undefined || f.value.mseropt13 == null) {
            f.value.mseropt13 = false;
        }
        if (f.value.mseropt14 == undefined || f.value.mseropt14 == null) {
            f.value.mseropt14 = false;
        }
        if (f.value.mseropt15 == undefined || f.value.mseropt15 == null) {
            f.value.mseropt15 = false;
        }
        if (f.value.mseropt16 == undefined || f.value.mseropt16 == null) {
            f.value.mseropt16 = false;
        }
        if (f.value.mseropt17 == undefined || f.value.mseropt17 == null) {
            f.value.mseropt17 = false;
        }
        if (f.value.mseropt18 == undefined || f.value.mseropt18 == null) {
            f.value.mseropt18 = false;
        }
        if (f.value.mseropt19 == undefined || f.value.mseropt19 == null) {
            f.value.mseropt19 = false;
        }
        if (f.value.mseropt20 == undefined || f.value.mseropt20 == null) {
            f.value.mseropt20 = false;
        }
        if (f.value.mseropt21 == undefined || f.value.mseropt21 == null) {
            f.value.mseropt21 = false;
        }
        if (f.value.mseropt22 == undefined || f.value.mseropt22 == null) {
            f.value.mseropt22 = false;
        }
        if (f.value.mseropt23 == undefined || f.value.mseropt23 == null) {
            f.value.mseropt23 = false;
        }
        if (f.value.mseropt24 == undefined || f.value.mseropt24 == null) {
            f.value.mseropt24 = false;
        }
        if (f.value.mseropt25 == undefined || f.value.mseropt25 == null) {
            f.value.mseropt25 = false;
        }
        if (f.value.mseropt26 == undefined || f.value.mseropt26 == null) {
            f.value.mseropt26 = false;
        }
        if (f.value.mseropt27 == undefined || f.value.mseropt27 == null) {
            f.value.mseropt27 = false;
        }
        if (f.value.mseropt28 == undefined || f.value.mseropt28 == null) {
            f.value.mseropt28 = false;
        }
        if (f.value.mseropt29 == undefined || f.value.mseropt29 == null) {
            f.value.mseropt29 = false;
        }
        if (f.value.mseropt30 == undefined || f.value.mseropt30 == null) {
            f.value.mseropt30 = false;
        }
        datas =
            {
                "positioncode": f.value.group.value,
                "chooseView": f.value.role.value,
                "permissions": [
                    {
                        "show": f.value.header1,
                        "attribute": [f.value.mseropt1, f.value.mseropt2, f.value.mseropt3]
                    },
                    {
                        "show": f.value.header2,
                        "attribute": [f.value.mseropt4, f.value.mseropt5, f.value.mseropt6, f.value.mseropt7, f.value.mseropt8, f.value.mseropt9, f.value.mseropt10]
                    },
                    {
                        "show": f.value.header3,
                        'attribute': [f.value.mseropt11, f.value.mseropt12, f.value.mseropt13, f.value.mseropt14]
                    },
                    {
                        "show": f.value.header4,
                        'attribute': [f.value.mseropt15, f.value.mseropt16, f.value.mseropt17, f.value.mseropt18]
                    },
                    {
                        "show": f.value.header5,
                        'attribute': [f.value.mseropt19, f.value.mseropt20, f.value.mseropt21, f.value.mseropt22]
                    },
                    {
                        "show": f.value.header6,
                        'attribute': [f.value.mseropt23, f.value.mseropt24, f.value.mseropt25, f.value.mseropt26]
                    },
                    {
                        "show": f.value.header7,
                        'attribute': [f.value.mseropt27, f.value.mseropt28, f.value.mseropt29, f.value.mseropt30]
                    },
                ]
            };
        console.log(JSON.stringify(datas));
        // this.adminService.getAdminData(datas)
        this.adminService.getAdminData()
            .subscribe(function (resUserData) {
            resUserData = datas = JSON.stringify(resUserData);
            console.log(resUserData);
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
    AdminComponent.prototype.onClick = function (ev) {
        for (var i = 1; i <= 7; i++) {
            if (ev == i) {
                if ($('.content.card-block' + i).hasClass("hide")) {
                    $('.content.card-block' + i).removeClass('hide').addClass("show");
                }
                else {
                    $('.content.card-block' + i).removeClass('show').addClass("hide");
                }
            }
            else {
                $(".content.card-block" + i).removeClass('show').addClass("hide");
            }
        }
    };
    // getAccordionData() {
    //     this.accordionData = [
    //         {            
    //         "accordionTitle": "MSER",
    //         'order': "1",
    //             data: [
    //                 { "name": "mseropt1", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt2", "title": "Excellence Cards Awards MTD",'checked':false },
    //                 { "name": "mseropt3", "title": "Excellence Cards Awards YTD",'checked':false },
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "MSER Graph",
    //         "order": "2",
    //             data: [
    //                 { "name": "mseropt4", "title": "uConnect",'checked':false },
    //                 { "name": "mseropt5", "title": "wiAdvisor",'checked':false },
    //                 { "name": "mseropt6", "title": "Express Lane",'checked':false },
    //                 { "name": "mseropt7", "title": "Parts Counter",'checked':false },
    //                 { "name": "mseropt8", "title": "Magenti Marelli",'checked':false },
    //                 { "name": "mseropt9", "title": "MVP",'checked':false },
    //                 { "name": "mseropt10", "title": "Mopar Parts",'checked':false },
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "Top Tech",
    //         "order": "3",
    //             data: [
    //                 { "name": "mseropt11", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt12", "title": "Top Performers",'checked':false },
    //                 { "name": "mseropt13", "title": "Count of Surveys QTD",'checked':false },
    //                 { "name": "mseropt14", "title": "Average Quarterly Survey Score",'checked':false }
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "Top Advisor",
    //         "order": "4",
    //             data: [
    //                 { "name": "mseropt15", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt16", "title": "Count of Surveys QTD",'checked':false },
    //                 { "name": "mseropt17", "title": "Incentive Eligible Advisors",'checked':false },
    //                 { "name": "mseropt18", "title": "Average Quarterly Survey Score",'checked':false }
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "Top Tech/Advisor Graph",
    //         "order": "5",
    //             data: [
    //                 { "name": "mseropt19", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt20", "title": "Count of Surveys QTD",'checked':false },
    //                 { "name": "mseropt21", "title": "Incentive Eligible Advisors",'checked':false },
    //                 { "name": "mseropt22", "title": "Average Quarterly Survey Score",'checked':false }
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "Retention",
    //         "order": "6",
    //             data: [
    //                 { "name": "mseropt23", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt24", "title": "Count of Surveys QTD",'checked':false },
    //                 { "name": "mseropt25", "title": "Incentive Eligible Advisors",'checked':false },
    //                 { "name": "mseropt26", "title": "Average Quarterly Survey Score",'checked':false }
    //             ]           
    //         },
    //         {            
    //         "accordionTitle": "Retention Graph",
    //         "order": "7",
    //             data: [
    //                 { "name": "mseropt27", "title": "Total Dealers Enrolled",'checked':false },
    //                 { "name": "mseropt28", "title": "Count of Surveys QTD",'checked':false },
    //                 { "name": "mseropt29", "title": "Incentive Eligible Advisors",'checked':false },
    //                 { "name": "mseropt30", "title": "Average Quarterly Survey Score",'checked':false }
    //             ]           
    //         }
    //     ];
    // }
    /*
    Select All functions
    */
    AdminComponent.prototype.toggleAll = function (ev) {
        //     // this.parentId = ev.target.value
        //     // var dataLength = this.accordionData.length;
        //     //     if(this.parentId===this.parentId) {
        //     //         if(this.parentId === "1") {
        //     //             var SubDataLeng = 3
        //     //         } 
        //     //         if(this.parentId === "2") {
        //     //             var SubDataLeng = 7
        //     //         }
        //     //         if(this.parentId === "3") {
        //     //             var SubDataLeng = 4
        //     //         }
        //     //         if(this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
        //     //             var SubDataLeng = 4
        //     //         }
        //     //         // if ($('input.header').is(':checked')) {
        //     //         if(ev.target.checked===true) {
        //     //             for(var j=0;j<=SubDataLeng; j++) {
        //     //                 if($(".card-block"+this.parentId+" input#mseropt"+j).prop('checked', false)) {
        //     //                     $(".card-block"+this.parentId+" input#mseropt"+j).prop('checked', true)
        //     //                 }
        //     //             }               
        //     //         }  else {
        //     //             for(var j=0;j<=SubDataLeng; j++) {
        //     //                 if($(".card-block"+this.parentId+" input#mseropt"+j).prop('checked', true)) {
        //     //                     $(".card-block"+this.parentId+" input#mseropt"+j).prop('checked', false)
        //     //                 }
        //     //             }
        //     //         }           
        //     //     } 
    };
    //  uncheckParent(ev) {
    //      this.parentId = ev.target.value
    //      var totoalChkedChbox = $('.card-block'+this.parentId+'  input:checkbox:checked').length;
    //      if(this.parentId === "1") {
    //             var SubDataLeng = 3
    //         } 
    //         if(this.parentId === "2") {
    //             var SubDataLeng = 7
    //         }
    //         if(this.parentId === "3") {
    //             var SubDataLeng = 4
    //         }
    //         if(this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
    //             var SubDataLeng = 4
    //         }
    //     //  if(ev.currentTarget.checked==true){
    //     //     //  if(ev.target.value) {
    //     //     //     //   if($("#header"+ev.target.value).is(":checked")) {
    //     //     //         $("#header"+ev.target.value).prop("checked", true);
    //     //     //         // $("#header"+ev.target.value).attr("checked", "checked");
    //     //     //     //   }
    //     //     //  }
    //     //  }
    //     //  if(totoalChkedChbox===0) {
    //     //      $("#header"+ev.target.value).prop("checked", false);
    //     //  }
    //  }
    /*if SID/TID is available
    * we hve to call onBlurMethod
    */
    // onBlurMethod(sid: number, body: Object) {
    //     let bodyString = JSON.stringify(body); // Stringify payload
    //     let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    //     let options = new RequestOptions({ headers: headers }); // Create a request option
    //     // this.edited = true;
    //     var adminData = "./app/resources/json/serviceJson/admin-data.json";
    //     var AdminDataThroughService = this.http.post(adminData, body, options)
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
        //    templateUrl: "./admin-static.html",
        templateUrl: "./new-admin.html",
        providers: [ng_bootstrap_1.NgbAccordionConfig]
    }),
    __metadata("design:paramtypes", [http_1.Http, admin_service_1.AdminService, core_1.ElementRef,
        ng_bootstrap_1.NgbAccordionConfig])
], AdminComponent);
exports.AdminComponent = AdminComponent;
//# sourceMappingURL=admin.component.js.map