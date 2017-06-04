import { Component, OnInit, Input, ViewChild, TemplateRef, Output, EventEmitter } from '@angular/core';
import { Http } from '@angular/http';
import { HeaderService } from '../../services/header-services/header.service';
import { NgbModal, ModalDismissReasons, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterOutlet, ActivatedRoute, Params } from '@angular/router';
import { CookieService } from 'angular2-cookie/services/cookies.service';

declare var $: any;
@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header.html"
})

export class HeaderComponent implements OnInit {
    @Input() data: any;
    @Input() retweetIconHide: any;
    @Input() enablePointer: any;
    @Output("onProfileChange") profileChange = new EventEmitter<any>();
    @ViewChild("contactModal") private contactModal: TemplateRef<any>;
    @ViewChild("positioncodeModal") private positioncodeModal: NgbModalRef;
    private banners: any = new Array;
    private adminToken: any = "";
    constructor(public activeModal: NgbActiveModal,
        private http: Http,
        private headerService: HeaderService,
        private modalService: NgbModal,
        private router: Router,
        private cookieService: CookieService) {
    }

    private poscodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
    private delcodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
    private booleanAdmin: any = JSON.parse(sessionStorage.getItem("CurrentUser")).admin;
    private booleanAdminToken: any = this.cookieService.get("adminToken");

    private positionCodeCancel() {
        this.positioncodeModal.close();
    }
    private positionCodeSubmit(c: any) {
        c();
        this.profileChange.emit("")
    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
        if (!this.enablePointer) {
            $(document).ready(function () {
                $("#enablePointer").css("cursor", "pointer");
                $("#enablePointer").css("text-decoration", "underline");
            });
        }
    }
    private contactUs() {
        this.modalService.open(this.contactModal, { windowClass: 'contact-us' });

    }

    private logout() {
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.clear();
        this.cookieService.removeAll();
        // let loginUrl = ["login"]
        // this.router.navigate(loginUrl);
        window.open("https://dealerconnect.chrysler.com/login/login.html", '_self')

    }

    private admin() {
        let adminUrl = ["admin"]
        this.router.navigate(adminUrl);
    }

    private dashboardPage() {
        sessionStorage.setItem("showWelcomePopup", "false");
        let dashboardUrl = ["/myfcadashboard"]
        this.router.navigate(dashboardUrl);
    }

    private dropdownPositionCode() {
        this.modalService.open(this.positioncodeModal, { windowClass: 'position-dealercode' });
    }
    private profile() {
        let profileUrl = ["profile"]
        this.router.navigate(profileUrl);
    }

    private endEmulation() {
        var adminToken = this.cookieService.get("adminToken");
        this.cookieService.remove("adminToken")
        this.cookieService.remove("token");
        this.cookieService.removeAll();
        sessionStorage.clear();
        window.sessionStorage.clear();
        //document.sessionStorage.clear();
        
        this.cookieService.put("token", adminToken)
        debugger
        // this.cookieService.put(adminToken, "")
        // this.adminToken = adminToken;
        let url = ["login"]
        this.router.navigate(url);
        // this.booleanEndEmulation();
        // alert(this.booleanAdminToken)
    }
}

