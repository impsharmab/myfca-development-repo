import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

const sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');

@Injectable()
export class HeaderService {
    private getBannersServiceUrl: string = './app/resources/json/newbanners.json';

    // private validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).validToken;
    // private validToken: any = this.tokenObject.validToken;
    constructor(private http: Http) { }

    getBanners() {
        return this.http.get(this.getBannersServiceUrl)
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