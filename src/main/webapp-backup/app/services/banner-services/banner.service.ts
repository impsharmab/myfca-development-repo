import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class BannerService {
    private getBannersServiceUrl: string = './app/resources/json/newbanners.json';
    constructor(private http: Http) { }

    getBanners() {
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes: any = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
         var getBannersServiceUrl = "https://test.myfcarewards.com/myfcarewards/services/banners/" + positioncodes + "/" + dealerlcodes + "/"
        //var getBannersServiceUrl = "./services/banners/" + positioncodes + "/" + dealerlcodes + "/";
        var headers = new Headers();
        headers.append('Authorization', validToken);

        return this.http.get(getBannersServiceUrl, { headers })
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