import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class PositionCodeService {
    private selectedCodeData: any;
    constructor(private http: Http, private cookieService: CookieService) { }

    setCodeData(codeData: any) {
        //  this.selectedCodeData = sessionStorage.setItem("selectedCodeData", JSON.stringify(codeData))
        this.selectedCodeData = this.cookieService.put("selectedCodeData", JSON.stringify(codeData))
    }
    getCodeData() {
        //return JSON.parse(sessionStorage.getItem("selectedCodeData"));
        return JSON.parse(this.cookieService.get("selectedCodeData"));
    }
}