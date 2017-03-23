import { Component, OnInit, Input, ViewChild, TemplateRef, Output, EventEmitter } from '@angular/core';
import { Http } from '@angular/http';
import { HeaderService } from '../../services/header-services/header.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterOutlet } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header.html"
    // styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"] ?
})

export class HeaderComponent implements OnInit {
    @Input() data: any;
    @Output("onProfileChange") profileChange = new EventEmitter<any>();
    private banners: any = new Array;
    @ViewChild("contactModal") private contactModal: TemplateRef<any>;
    @ViewChild("positioncodeModal") private positioncodeModal: TemplateRef<any>;

    private poscodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
    private delcodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
    private booleanAdmin: any = JSON.parse(sessionStorage.getItem("CurrentUser")).admin;

    constructor(private http: Http, private headerService: HeaderService, private modalService: NgbModal, private router: Router) {

    }
    private positionCodeCancel(){	
    }	
    private positionCodeSubmit(){	
        alert("1");	
        this.profileChange.emit("")		
    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
    }

    private contactUs() {
        this.modalService.open(this.contactModal, { windowClass: 'contact-us' });

    }

    // private profileChangeEvent(value: any) {
    //     this.profileChange.emit(value);
    // }

    private logout() {
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.clear();
        let loginUrl = ["login"]
        this.router.navigate(loginUrl);
    }

    private admin() {
        let adminUrl = ["admin"]
        this.router.navigate(adminUrl);
    }

    private dashboardPage() {
        let dashboardUrl = ["myfcadashboard"]
        this.router.navigate(dashboardUrl);
    }

    private dropdownPositionCode() {
        this.modalService.open(this.positioncodeModal, { windowClass: 'position-dealercode' });
    }

}

