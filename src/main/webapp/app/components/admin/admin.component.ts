import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { AdminService } from '../../services/admin-services/admin.service';

@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./admin.html"
})

export class AdminComponent implements OnInit {
    @Input() data: any;
    private banners: any = new Array;
    constructor(private http: Http, private adminService: AdminService) {

    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
        //    document.getElementById("profileModel").click();
    }




}

