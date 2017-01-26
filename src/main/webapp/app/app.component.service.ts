import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './rxjs-operators';

const sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');

@Injectable()
export class MyFcaService {    
    private getLoginResponseUrl: string = './app/resources/json/serviceJson/token_response.json';
    private getUserServiceUrl: string = './app/resources/json/newUserDetail.json';
    private getBaseServiceUrl: string = 'services/userprofile';
    private getBannersServiceUrl: string = './app/resources/json/newbanners.json';
    private getNumberOfTiltesServiceUrl: string = "./app/resources/json/serviceJson/notiles.json";
    
    private tokenObject: any = JSON.parse(sessionStorage.getItem("CurrentUser"));
    //private validToken: any = this.tokenObject.validToken;
    private tiles = new Array();
    private userdata = {}
    constructor(private http: Http) {}

    setTiles(tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
        //sessionStorage.setItem("tiles", JSON.stringify(tiles));
    }
    getTiles() {
        return this.tiles; 
    }
    setUserData(userdata: any) {
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    }
    getUsersData() {
        return this.userdata;
    }
    getBanners() {
        return this.http.get(this.getBannersServiceUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getNumberOfTiltes() {
        return this.http.get(this.getNumberOfTiltesServiceUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getTilteJson(id) {
        // alert("token from sessionstorage" + this.token);
        var headers = new Headers();
       // headers.append('Parameter', this.validToken);

       // var tileService = "./app/resources/json/serviceJson/" + id + "-tile.json";
         var tileService = "services/tile/" + id;
        return this.http.get(tileService, { headers }) //headers should be in object
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getChartJson(id) {
      //  var chartService = "./app/resources/json/serviceJson/" + id + "-chart.json";
        var chartService = "services/tile/" + id;
        return this.http.get(chartService)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getLoginResponse(username, password): any {
        var q_password = sha256(password);
       //console.log("encrypted q_password: " + q_password)
       // console.log("encrypted q_password: " + q_password) 
       // console.log("this.token :"+ this.validToken)       

        var body = "id=" + username + "&key=" + password;
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

          return this.http.post(this.getBaseServiceUrl, body, { headers: headers })
        // return this.http.get(this.getLoginResponseUrl)
            .map((response: Response) =>
                response.json())
            .catch(this.handleError);

    }

    // getModalJson() {
    //     var modalService = "./app/resources/json/modal.json";
    //     var modalData= this.http.get(modalService)
    //         .map((response: Response) => {
    //             response.json();
    //             console.log("inside service " + response.json());            
    //         }
    //         )
    //         .catch(this.handleError);
    //         return modalData;
    // }

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