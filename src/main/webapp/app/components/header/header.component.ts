import { Component, OnInit, Input, ViewChild, TemplateRef, Output, EventEmitter } from '@angular/core';
import { Http } from '@angular/http';
import { HeaderService } from '../../services/header-services/header.service';
import { NgbModal, ModalDismissReasons, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterOutlet } from '@angular/router';
import { CookieService } from 'angular2-cookie/services/cookies.service';

@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header.html"
    // styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"] ?
})

export class HeaderComponent implements OnInit {
    @Input() data: any;
    @Input() retweetIconHide: any;
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

    // private poscodes: any = JSON.parse(this.cookieService.get("CurrentUser")).positionCode;
    // private delcodes: any = JSON.parse(this.cookieService.get("CurrentUser")).dealerCode;
    // private booleanAdmin: any = JSON.parse(this.cookieService.get("CurrentUser")).admin;


    //  var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
    //     var positioncodes: any = JSON.parse(this.cookieService.get("selectedCodeData")).selectedPositionCode;
    //     var dealerlcodes: any = JSON.parse(this.cookieService.get("selectedCodeData")).selectedDealerCode;


    private positionCodeCancel() {
        this.positioncodeModal.close();
    }
    private positionCodeSubmit(c: any) {
        // alert("1");	
        c();
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
        // this.cookieService.remove('CurrentUser');
        // this.cookieService.remove('selectedCodeData');
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
        let dashboardUrl = ["myfcadashboard"]
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
        document
        this.cookieService.put("token", adminToken)
        debugger
        // this.cookieService.put(adminToken, "")
        

        // this.adminToken = adminToken;
        let url = ["login"]
        this.router.navigate(url); 
        // this.booleanEndEmulation();
        // alert(this.booleanAdminToken)

    }

    // booleanEndEmulation() {
    //     var booleanAdminToken = this.cookieService.get("adminToken");
    //     if (booleanAdminToken !== undefined || booleanAdminToken.length > 1) {
    //         this.booleanAdminToken = true;
    //     }
    // }

}

