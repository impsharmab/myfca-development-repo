import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: "app-footer",
    templateUrl: "./footer.html"

})
export class FooterComponent {


    constructor() { }

   private openProgramSite(url: any) {
        window.open(url)
    }

   private openFooterSSOSite(url: any) {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;

        window.open(url + validToken + "&positioncode=" + positioncodes + "&dealercode=" + dealerlcodes, "_self")
    }
}