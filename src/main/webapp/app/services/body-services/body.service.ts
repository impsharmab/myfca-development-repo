import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

const sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');

@Injectable()
export class BodyService {
    private getUserServiceUrl: string = './app/resources/json/newUserDetail.json';
    private getBaseServiceUrl: string = 'services/userprofile';
    private getNumberOfTiltesServiceUrl: string = "./app/resources/json/notiles-service.json";

    // private validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).validToken;
    // private validToken: any = this.tokenObject.validToken;
    private tiles = new Array();
    private userdata = {}
    constructor(private http: Http) { }

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

    getNumberOfTiltes() {
        return this.http.get(this.getNumberOfTiltesServiceUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getTilteJson(id) {
        // alert("token from sessionstorage" + this.token);
        var headers = new Headers();
        // headers.append('Parameter', this.validToken);
        
        // var tileService = "./app/resources/json/" + id + "-tile.json";
        var tileService = "services/tile/" + id;
        return this.http.get(tileService, { headers }) //headers should be in object
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getChartJson(id) {
        //  var chartService = "./app/resources/json/" + id + "-chart.json";
        var chartService = "services/tile/" + id;
        return this.http.get(chartService)
            .map((response: Response) => response.json())
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