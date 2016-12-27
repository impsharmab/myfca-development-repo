import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './rxjs-operators';

@Injectable()
export class MyFcaService {

    private titles = new Array();
    private userdata = {
    }

    constructor(private http: Http) { }
    setTiles(titles) {
        localStorage.setItem("titles", JSON.stringify(titles));
        //sessionStorage.setItem("titles", JSON.stringify(titles));
    }

    getTiles() {
        return this.titles;
    }

    setUserData(userdata: any) {
        localStorage.setItem("CurrentUser", JSON.stringify(userdata));
    }
    getUsersData() {
        return this.userdata;
    }

    getNewServiceJSON(username, password): any {

        var daveService = "./app/resources/json/dave.json";
        // var pieChartService = "./app/resources/json/testPieChart.json";
        // var serviceurl = "services/userprofile";
        // var creds = "id=" + username + "&key=" + password;
        // var headers = new Headers();
        // headers.append('Content-Type', 'application/x-www-form-urlencoded');

        var serviceurl = "services/userprofile?id=" + username + "&key=" + password;

        var tileDataThroughService = this.http.post(serviceurl, {})
            // var tileDataThroughService = this.http.get(daveService)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return tileDataThroughService;
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
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

    getHeaders() {
        let headersObj = new Headers();
        //headers.append('Accept', 'application/json');
        headersObj.append('Access-Control-Allow-Headers', 'Content-Type');
        headersObj.append('Access-Control-Allow-Methods', 'GET');
        headersObj.append('Access-Control-Allow-Origin', '*');
        return headersObj;
    }
}