import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class DashboardBodyService {
    //  private baseUrl = "";
    private baseUrl = "https://test.myfcarewards.com/myfcarewards/";
    //private baseUrl = "https://www.myfcarewards.com/myfcarewards/";
    private tiles = new Array();
    private userdata = {}
    constructor(private http: Http, private cookieService: CookieService) { }

    setTiles(tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
    }
    getTiles() {
        return this.tiles;
    }
    setUserData(userdata: any) {
        this.cookieService.put("CurrentUser", JSON.stringify(userdata))
    }
    getUsersData() {
        return this.userdata;
    }
    getNumberOfTiltes() {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var getNumberOfTilesServiceUrl: string = this.baseUrl + "services/notile/" + positioncodes + "/" + dealerlcodes;
        var getNumberOfTilesServiceUrl: string = "./src/assets/json/notiles.json";
        var headers = new Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(getNumberOfTilesServiceUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getTilteJson(id?) {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");

        var tileService = this.baseUrl + "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        return this.http.get(tileService, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getChartJson(id?) {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        var chartService = this.baseUrl + "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        var chartService = "./src/assets/json/1-chart.json";
        return this.http.get(chartService, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    private handleError(error: Response | any) {
        let errMsg: string = "";
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable.throw(errMsg);
    }
}