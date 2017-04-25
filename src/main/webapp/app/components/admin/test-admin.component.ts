import { Component, ElementRef, OnInit, Directive, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { AdminService } from '../../services/admin-services/admin.service';
import { Admin } from './admin.interface';

@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./test-admin.html"

})


export class TestAdminComponent implements OnInit {
    private positioncode: any;
    private roles: any;
    private adminData: any;
    private data: any;
    constructor(private adminService: AdminService) { }
    ngOnInit() {
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
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
}

