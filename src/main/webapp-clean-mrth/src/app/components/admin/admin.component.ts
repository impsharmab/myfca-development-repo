import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AdminService } from '../../services/admin-services/admin.service';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Admin } from './admin.interface';
import { EmulateUserInterface } from './emulate-user.interface'
import { UploadImageInterface } from './admin-uploadImage.interface'

declare var $: any;
@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./admin.html",
    styleUrls: ["./admin.css"]

})
export class AdminComponent implements OnInit {
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
    private allBannerTableData: any = [];
    private editBannerDatum: any = {};
    private deleteBannerDatum: any = {}
    private role: string = "";
    private admin: Admin;
    private tileDataLocal: any = {}
    private tiledataresponse: any = []
    constructor(private adminService: AdminService, private cookieService: CookieService, private router: Router) { }


    ngOnInit() {
        var self = this;
        this.emulateuser = {
            sid: ''
        }
        this.admin = {
            positioncode: '',
            role: ''
        }

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
        }
        //this.getTileDataLocal();
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
        this.getImageList(self);
        //this.getTileDataResponse();

        // $(document).ready(function () {
        //     $('#example').DataTable();
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
    private constructRoles(roleID) {
        if (roleID == 1) {
            return "Executive"
        } else if (roleID == 12) {
            return "BC"
        } else if (roleID == 11) {
            return "District Manager"
        } else if (roleID == 10) {
            return "Dealer"
        } else if (roleID == 5) {
            return "Manager"
        } else if (roleID == 9) {
            return "Participants"
        }
    }

    private getAllBannerData() {
        this.adminService.getAllBannerData().subscribe(
            (allBannerTableData) => {
                this.allBannerTableData = allBannerTableData;
            },
            (error) => {
                alert("Error in getting banner data")
            }
        )
    }

    private setImage(image) {
        this.uploadImage.image = image;

    }

    private testMethod() {
        for (var k = 0; k < this.imagelist.length; k++) {
            this.projects.push({
                value: this.imagelist[k],
                label: this.imagelist[k],
                icon: this.imagelist[k]
            }
            )
        }
        var mainthis = this;
        $("#project").autocomplete({
            change: function (event, ui) {
                if (!ui.item) {
                    $("#project").val("");
                }
            },
            allowFreeEntries: false,
            minLength: 0,
            source: this.projects,
            focus: function (event, ui) {
                $("#project").val(ui.item.label);
                return false;
            },
            select: function (event, ui) {
                var self = this;
                $("#project").val(ui.item.label);
                $("#project-id").val(ui.item.value);
                $("#project-icon").attr("src", "./services/files/get/" + ui.item.icon);
                mainthis.uploadImage.image = ui.item.value;
                return false;
            }
        })
            .autocomplete("instance")._renderItem = function (ul, item) {
                return $("<li>")
                    .append("<div>" + item.label + "</div>")
                    .appendTo(ul);
            };

    }


    private getImageList(self) {
        this.adminService.getImageList().subscribe(
            (imagelist) => {
                this.imagelist = imagelist;
                self.testMethod();
            }
        )
    }

    private setRole(b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b)
        } else {
            a = b;
        }
        this.uploadImage.selectedRoleId = a;
    }

    private setBC(b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b)
        } else {
            a = b;
        }
        this.uploadImage.bc = a;
    }

    private getPositionCode() {
        this.adminService.getPositionCode().subscribe(
            (positioncode) => {
                this.positioncode = positioncode;
            }
        )
    }
    private getRoles() {
        this.adminService.getRoles().subscribe(
            (roles) => {
                this.roles = roles;
            }
        )
    }
    private getAdminData() {
        this.adminService.getAdminData().subscribe(
            (adminData) => {
                this.adminData = adminData.permissions;
            }
        )
    }

    private emulateUser() {
        this.adminService.getEmulateUserData(this.emulateuser.sid).subscribe(
            (emulateUserData) => {
                this.emulateUserData = emulateUserData;
                if (emulateUserData["item"].length > 0) {
                    var adminToken = this.cookieService.get("token");
                    this.cookieService.put("adminToken", adminToken);
                    this.cookieService.put("token", emulateUserData.item);
                    let url = ["login"]
                    this.router.navigate(url);
                }
            }
        )
    }
    private endEmulateUser() {
        this.cookieService.get("adminToken")
        this.adminService.setEndEmulateUserData(this.endEmulateUserData);
        var poscodes: any = this.emulateUserData.positionCode;
        var delcodes: any = this.emulateUserData.dealerCode;

        sessionStorage.setItem("selectedCodeData", JSON.stringify(
            {
                "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
            }))
        let url = ["myfcadashboard"]
        this.router.navigate(url);
    }

    private addBannerImage() {
        if (this.uploadImage.selectedRoleId.length == 0) {
            return false;
        } else if (this.uploadImage.bc.length == 0) {
            return false;
        } else if (this.uploadImage.orderBy == undefined) {
            return false;
        } else if (this.uploadImage.orderBy.toString() == "") {
            return false;
        } else if (this.uploadImage.image.length == 0) {
            return false;
        }
        for (var i = 0; i < this.uploadImage.bc.length; i++) {
            for (var j = 0; j < this.uploadImage.selectedRoleId.length; j++) {
                this.adminService.addBanner(this.uploadImage.selectedRoleId[j], this.uploadImage.bc[i], this.uploadImage.orderBy, this.uploadImage.image).subscribe(
                    (addBannerData) => {
                        this.addBannerData = addBannerData;
                        alert("Success in adding banner");
                        this.getAllBannerData();
                    },
                    (error) => {
                        alert("Error in adding banner");
                        this.errorUploadImageMessage = "Error in adding banner";
                    }
                )
            }
        }

    }

    private editBannerData(editBannerObj: any) {
        this.adminService.editBannerData(editBannerObj).subscribe(
            (editBannerDatum) => {
                this.editBannerDatum = editBannerDatum;
            }
        )
    }

    private deleteBannerData(dashBoardBannersID: any) {

        this.adminService.deleteBannerData(dashBoardBannersID).subscribe(
            (deleteBannerDatum) => {
                this.deleteBannerDatum = deleteBannerDatum;
                // alert(deleteBannerDatum)
                // alert(this.deleteBannerDatum)
                alert("Successfully deleted banner")
                this.getAllBannerData();
            },
            (error) => {
                alert("Error in deleting banner");
            }
        )

    }


    private getTileDataLocal() {
        this.adminService.getTileDataLocal().subscribe(
            (tileDataLocal) => {
                this.tileDataLocal = tileDataLocal;
               
                var a = tileDataLocal.permissions
                console.log(tileDataLocal)
                console.log(a)                

                 console.log(this.tileDataLocal)             
            },
            (error) => {
                alert("error");
            }
        )
    }
    private getTileDataResponse() {
        debugger
        this.adminService.getTileDataResponse(this.admin.positioncode).subscribe(
            (tiledataresponse) => {
                this.tiledataresponse = tiledataresponse;
                // console.log(tiledataresponse)
                // console.log(this.tiledataresponse)
                // console.log(this.tiledataresponse.permissions)

            },
            (error) => {

            }
        )
    }


}








