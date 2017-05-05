import { Component, ElementRef, OnInit, Directive, Input, ViewChild } from '@angular/core';
import { Router, RouterOutlet, ActivatedRoute, Params } from '@angular/router';

import { AdminService } from '../../services/admin-services/admin.service';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Admin } from './admin.interface';
import { EmulateUserInterface } from './emulate-user.interface'
import { UploadImageInterface } from './admin-uploadImage.interface'

declare var $: any;
@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./test-admin-uploadimage.html"
    // templateUrl: "./upload-image.html"

})
export class TestAdminComponent implements OnInit {
    private positioncode: any;
    private roles: any;
    private adminData: any;
    private data: any;
    private emulateuser: EmulateUserInterface;
    private uploadImage: UploadImageInterface;
    private emulateUserData: any;
    private endEmulateUserData: any;
    private addBannerData: any;
    private errorUploadImageMessage: string = "";
    private id: any;
    private bc: any;
    private imagelist: any = [];
    private projects: any = [];
    constructor(private adminService: AdminService, private cookieService: CookieService, private router: Router) { }

    ngOnInit() {
        var self = this;
        this.emulateuser = {
            sid: ''
        }
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

        }
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
        this.getImageList(self);
        
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
            })

            var bc = $('#bc').magicSuggest({
                allowFreeEntries: false,
                data: ["NAT", "CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"]

            });

            $(bc).on('selectionchange', function (e, m) {
                self.setBC(this.getValue());
            })


        });




    }

    testMethod() {
        for (var k = 0; k < this.imagelist.length; k++) {
            this.projects.push({
                value: this.imagelist[k],
                label: this.imagelist[k],
                icon: this.imagelist[k]
            }
            )
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
            minLength: 0,
            source: this.projects,
            focus: function (event, ui) {
                $("#project").val(ui.item.label);
                return false;
            },
            select: function (event, ui) {
                $("#project").val(ui.item.label);
                $("#project-id").val(ui.item.value);
                $("#project-icon").attr("src", "https://test.myfcarewards.com/myfcarewards/services/loadrsc?id=" + ui.item.icon);
                return false;
            }
        })
            .autocomplete("instance")._renderItem = function (ul, item) {
                return $("<li>")
                    .append("<div>" + item.label + "</div>")
                    .appendTo(ul);
            };

    }
    getImageList(self) {
        this.adminService.getImageList().subscribe(
            (imagelist) => {
                this.imagelist = imagelist;
                console.log(imagelist)
                console.log(this.imagelist)
                self.testMethod();
            }
        )
    }

    setRole(b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b)
        } else {
            a = b;
        }
        this.uploadImage.selectedRoleId = a;
    }

    setBC(b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b)
        } else {
            a = b;
        }
        this.uploadImage.bc = a;
    }

    getPositionCode() {
        this.adminService.getPositionCode().subscribe(
            (positioncode) => {
                this.positioncode = positioncode;
                // alert(positioncode[0])

            }
        )
    }
    getRoles() {
        this.adminService.getRoles().subscribe(
            (roles) => {
                this.roles = roles;
                // alert(roles[0])

            }
        )
    }
    getAdminData() {
        this.adminService.getAdminData().subscribe(
            (adminData) => {
                this.adminData = adminData.permissions;
                // this.data = this.adminData.data;
                // console.log(adminData.permissions[0].name)



            }
        )
    }
    //dealer=10, partic=9, execut=1, bc=12, dis=11, dealman=5
    //"Executive", "BC", "District Manager", "Dealer", "Manager", "Participant"

    emulateUser() {
        this.adminService.getEmulateUserData(this.emulateuser.sid).subscribe(
            (emulateUserData) => {
                this.emulateUserData = emulateUserData;
                debugger
                console.log(emulateUserData)
                if (emulateUserData["item"].length > 0) {
                    // this.adminService.setEmulateUserData(this.emulateUserData);
                    var adminToken = this.cookieService.get("token");
                    this.cookieService.put("adminToken", adminToken);
                    this.cookieService.put("token", emulateUserData.item);

                    let url = ["login"]
                    this.router.navigate(url);

                    // this.cookieService.put("adminToken", this.);


                    //sessionStorage.clear();


                    // this.adminService.setEmulateUserData(this.emulateUserData);
                    // var poscodes: any = this.emulateUserData.positionCode;
                    // var delcodes: any = this.emulateUserData.dealerCode;

                    // sessionStorage.setItem("selectedCodeData", JSON.stringify(

                    //     {
                    //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                    //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                    //     }))

                    // let url = ["myfcadashboard"]
                    // this.router.navigate(url);

                }

            }
        )


    }

    endEmulateUser() {
        this.cookieService.get("adminToken")
        this.adminService.setEndEmulateUserData(this.endEmulateUserData);
        var poscodes: any = this.emulateUserData.positionCode;
        var delcodes: any = this.emulateUserData.dealerCode;
        // this.cookieService.put("selectedCodeData", JSON.stringify(
        //     {
        //         "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
        //         "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
        //     }))


        sessionStorage.setItem("selectedCodeData", JSON.stringify(

            {
                "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
            }))

        let url = ["myfcadashboard"]
        this.router.navigate(url);

    }

    addBannerImage() {

        for (var i = 0; i < this.uploadImage.bc.length; i++) {
            for (var j = 0; j < this.uploadImage.selectedRoleId.length; j++) {
                this.adminService.addBanner(this.uploadImage.selectedRoleId[j], this.uploadImage.bc[i], this.uploadImage.orderBy, this.uploadImage.image).subscribe(
                    (addBannerData) => {
                        this.addBannerData = addBannerData;
                        debugger
                        console.log(addBannerData)
                        alert(addBannerData)
                    },
                    (error) => {
                        alert("Error in uploading images");
                        this.errorUploadImageMessage = "Error in uploading images";
                    }
                )
            }
        }



    }

}








