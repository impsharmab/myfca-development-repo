import { Component, ElementRef, OnInit, Directive, Input, ViewChild } from '@angular/core';
import { Router, RouterOutlet, ActivatedRoute, Params } from '@angular/router';

import { AdminService } from '../../services/admin-services/admin.service';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Admin } from './admin.interface';
import { EmulateUserInterface } from './emulate-user.interface'

declare var $: any;
@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./test-admin-uploadimage.html"

})


export class TestAdminComponent implements OnInit {
    private positioncode: any;
    private roles: any;
    private adminData: any;
    private data: any;
    private emulateuser: EmulateUserInterface;
    private emulateUserData: any;
    private endEmulateUserData: any;
    constructor(private adminService: AdminService, private cookieService: CookieService, private router: Router) { }
    ngOnInit() {

        this.emulateuser = {
            sid: ''
        }
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

        });



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

    setCookie(name?: string) {
        this.cookieService.put('test', "test test test");

    }
    getCookie(name?: string) {
        var y = this.cookieService.get('test');
        // alert(y)
    }

    emulateUser() {
        this.adminService.getEmulateUserData(this.emulateuser.sid).subscribe(
            (emulateUserData) => {
                this.emulateUserData = emulateUserData;
                debugger
                console.log(emulateUserData)
                if (emulateUserData["token"].length > 0) {
                    sessionStorage.clear();
                    this.adminService.setEmulateUserData(this.emulateUserData);

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

}
       







