import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { CookieService } from 'angular2-cookie/services/cookies.service';


@Component({
    moduleId: module.id,
    selector: "app-footer",
    templateUrl: "./footer.html"

})
export class FooterComponent implements OnInit {
    private booleanDealerEmulation: any = false;
    private hideButton: any = false;

    constructor(private cookieService: CookieService) { }

    ngOnInit() {
        var hideButton = sessionStorage.getItem("hideButton");

        if (hideButton !== undefined && hideButton == "true") {
            this.hideButton = true;
        }
    }
    private openProgramSite(url: any) {
        window.open(url)
    }
    private checkDealerToken() {
        if (this.cookieService.get("adminToken") == this.cookieService.get("token")) {
            if ((this.cookieService.get("token") !== undefined) && this.cookieService.get("token") !== null) {
                this.booleanDealerEmulation = true;
            }
        }
    }

    private openFooterSSOSite(url: any) {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        if (!this.booleanDealerEmulation) {
            window.open(url + validToken + "&positioncode=" + positioncodes + "&dealercode=" + dealerlcodes, "_self")
        }


    }
}