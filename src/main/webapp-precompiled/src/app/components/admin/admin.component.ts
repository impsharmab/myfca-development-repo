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
    private isInternalAdmin: any = false;
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
    private emulateusermessage: string = "";
    private emulatedealermessage: string = "";
    private selectedPermissionData: any;
    private showButton: any = true;
    private tileDataLocal: any = {
        "positioncode": "0",
        "permissions": [
            {
                "name": "MSER",
                "order": "1",
                "checked": false
            },
            {
                "name": "MSER Graph",
                "order": "2",
                "checked": false
            },
            {
                "name": "Performance Institutes – Certified Professionals",
                "order": "3",
                "checked": false
            },
            {
                "name": "Certified Professionals Graph",
                "order": "4",
                "checked": false
            },
            {
                "name": "Performance Institute - Brain Boost",
                "order": "5",
                "checked": false
            },
            {
                "name": "Brain Boost-Graph-I",
                "order": "6",
                "checked": false
            },
            {
                "name": "Brain Boost-Graph-II",
                "order": "7",
                "checked": false
            },
            {
                "name": "Certified Professionals – Experts",
                "order": "8",
                "checked": false
            },
            {
                "name": "Certified Professional-Expert-Graph",
                "order": "9",
                "checked": false
            },
            {
                "name": "Certified Professional-Expert-GraphII",
                "order": "10",
                "checked": false
            },
            {
                "name": "Top Advisor",
                "order": "11",
                "checked": false
            },
            {
                "name": "Top Tech",
                "order": "12",
                "checked": false
            },
            {
                "name": "Top Tech/Top Advisor Graph",
                "order": "13",
                "checked": false
            },
            {
                "name": "Rewards Redemption - Tile",
                "order": "14",
                "checked": false
            },
            {
                "name": "Rewards Redemption - Graph",
                "order": "15",
                "checked": false
            },
            {
                "name": "Summary YTD Earnings",
                "order": "16",
                "checked": false
            },
            {
                "name": "Customer First-Graph",
                "order": "17",
                "checked": false
            },
            {
                "name": "Customer First-Pie",
                "order": "18",
                "checked": false
            },
            {
                "name": "Retention - Tile",
                "order": "19",
                "checked": false
            },
            {
                "name": "Retention - Graph",
                "order": "20",
                "checked": false
            },
            {
                "name": "Retention - Graph",
                "order": "21",
                "checked": false
            }
        ]
    };
    private tiledataresponse: any = [];
    private imageUploadMessage: string = "";
    private addBannerDataMessage: string = "";
    private deleteBannerDataMessage: string = "";
    private bannerColumnHeaders: any = [
        { "data": "roleID", "title": "Roles" },
        { "data": "businessCenter", "title": "Business Center" },
        { "data": "image", "title": "Image" },
        { "data": "orderBy", "title": "Order" },
        {
            "className": 'details-control',
            "orderable": false,
            "data": null,
            "title": "Delete",
            "defaultContent": '<button type="button" class="btn btn-primary btn-sm" ><i class="fa fa-close"></i></button>'
        }
    ]
    private columnDefs = [{
        "targets": 0,
        "data": "roleID",
        "render": function (roleID, type, full, meta) {
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
            } else if (roleID == 6) {
                return "Participants"
            }
        }
    }];


    constructor(private adminService: AdminService, private cookieService: CookieService, private router: Router) { }

    ngOnInit() {
        this.internalAdmin();
        var self = this;
        this.emulateuser = {
            sid: '',
            dealercode: ''
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

        $(document).ready(function () {
            $('#example').DataTable();
        });

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
                data: [{ roleid: 1, name: "Executive" }, { roleid: 12, name: "BC" }, { roleid: 11, name: "District Manager" }, { roleid: 10, name: "Dealer" }, { roleid: 5, name: "Manager" }, { roleid: 6, name: "Participant" }]
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
    private goBack() {
        sessionStorage.setItem("showWelcomePopup", "false");
        let dashboardUrl = ["/myfcadashboard"];
        this.router.navigate(dashboardUrl);
    }
    private imageUpload() {
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);
        var globalthis = this;
        $.ajax({
            // url: 'https://test.myfcarewards.com/myfcarewards/services/files/imageUpload',
            url: './services/files/imageUpload',
            type: 'POST',
            data: formData,
            processData: false,  // tell jQuery not to process the data
            contentType: false,  // tell jQuery not to set contentType
            success: function (data) {
                globalthis.imageUploadMessage = "Successfully uploaded image";
            },
            error:
            function (error) {
                if ($('#file')[0].files[0] == null) {
                    globalthis.imageUploadMessage = "Please choose an image";
                }
                var a = true;
                for (var i = 0; i < globalthis.imagelist.length; i++) {
                    if ($('#file')[0].files[0].name == globalthis.imagelist[i]) {
                        globalthis.imageUploadMessage = "Image already exists";
                        var a = false;
                        return;
                    }
                }
                if (a) {
                    globalthis.imageUploadMessage = "Error in uploading image";
                    return;
                }

            }
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
        } else if (roleID == 6) {
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

    private emulateAllUser() {
        if (this.emulateuser.sid === "") {
            this.emulateusermessage = "Please enter SID/TID/Dealer Code";
            return false;
        }
        if (this.emulateuser.sid.length == 5) {
            this.emulateUserWithDealerCode();
        } else {
            this.emulateUser();

        }
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
            ,
            (error) => {
                this.emulateusermessage = "User Emulation Failed, Maybe due to incorrect SID/TID/Dealer Code";
            }
        )
    }
    private emulateUserWithDealerCode() {
        this.adminService.emulateUserWithDealerCode(this.emulateuser.sid).subscribe(
            (emulateUserData) => {
                this.emulateUserData = emulateUserData;
                var adminToken = this.cookieService.get("token");
                this.cookieService.put("adminToken", adminToken);
                this.cookieService.put("dealercode", this.emulateuser.sid);
                sessionStorage.setItem("hideButton", "true");
                // this.cookieService.put("token", adminToken);
                let url = ["login"]
                this.router.navigate(url);
            }
            ,
            (error) => {
                this.emulateusermessage = "User Emulation Failed, Maybe due to incorrect SID/TID/Dealer Code";
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
            this.addBannerDataMessage = "Please select Role";
            return false;
        } else if (this.uploadImage.bc.length == 0) {
            this.addBannerDataMessage = "Please select Business Center";
            return false;
        } else if (this.uploadImage.orderBy == undefined) {
            this.addBannerDataMessage = "Please select Order";
            return false;
        } else if (this.uploadImage.orderBy.toString() == "") {
            this.addBannerDataMessage = "Please select Order";
            return false;
        } else if (this.uploadImage.image.length == 0) {
            this.addBannerDataMessage = "Please select an Image";
            return false;
        }
        for (var i = 0; i < this.uploadImage.bc.length; i++) {
            for (var j = 0; j < this.uploadImage.selectedRoleId.length; j++) {
                this.adminService.addBanner(this.uploadImage.selectedRoleId[j], this.uploadImage.bc[i], this.uploadImage.orderBy, this.uploadImage.image).subscribe(
                    (addBannerData) => {
                        this.addBannerData = addBannerData;
                        this.addBannerDataMessage = "Success in adding banner"
                        //alert("Success in adding banner");
                        this.getAllBannerData();
                    },
                    (error) => {
                        this.addBannerDataMessage = "Error in adding banner"
                        // alert("Error in adding banner");
                        //this.errorUploadImageMessage = "Error in adding banner";
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
                this.deleteBannerDataMessage = "Successfully deleted banner";
                // alert("Successfully deleted banner")
                this.getAllBannerData();
            },
            (error) => {
                this.deleteBannerDataMessage = "Error in deleting banner";
                // alert("Error in deleting banner");
            }
        )

    }

    private getTileDataLocal() {
        this.adminService.getTileDataLocal().subscribe(
            (tileDataLocal) => {
                this.tileDataLocal = tileDataLocal;
                console.log(tileDataLocal)
            },
            (error) => {
                alert("Error in getting tile data");
            }
        )
    }

    private getTileDataResponse() {
        // alert(this.admin.positioncode);
        this.adminService.getTileDataResponse(this.admin.positioncode).subscribe(
            (tiledataresponse) => {
                var permissionJson = this.tileDataLocal.permissions;
                for (var i = 0; i < permissionJson.length; i++) {
                    permissionJson[i].checked = tiledataresponse.permissions[i];
                }
                this.tiledataresponse = tiledataresponse;
            },
            (error) => {

            }
        )
    }
    private onSave() {
        var selectedPermissionJson = {
            "positioncode": this.admin.positioncode,
            "permissions": []
        }
        var changePermissionJson = this.tileDataLocal.permissions;
        for (var i = 0; i < changePermissionJson.length; i++) {
            selectedPermissionJson.permissions[i] = changePermissionJson[i].checked;
        }
        console.log(selectedPermissionJson)

        this.adminService.saveSelectedPermission(selectedPermissionJson).subscribe(
            (selectedPermissionData) => {
                this.selectedPermissionData = selectedPermissionData;
            },
            (error) => {

            }
        )
    }

    private onCancel() {
        this.getTileDataResponse();
    }
    private internalAdmin() {
        var sessionStorageItem: any = JSON.parse(sessionStorage.getItem("CurrentUser"));
        var positioncode: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        if (positioncode.indexOf("IAD") > -1) {
            this.isInternalAdmin = true;
        } else {
            this.isInternalAdmin = false;
        }
    }
}








