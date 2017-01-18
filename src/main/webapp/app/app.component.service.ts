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
    getNumberOfTiltes() {
        var notiles = "./app/resources/json/serviceJson/notiles.json";
        var tileDataThroughService = this.http.get(notiles)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return tileDataThroughService;
    }
    getTilteJson(id) {
        var tileService = "./app/resources/json/serviceJson/"+id+"-tile.json";
        var tileDataThroughService = this.http.get(tileService)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return tileDataThroughService;
    }
    getChartJson(id) {
        var chartService = "./app/resources/json/serviceJson/" + id + "-chart.json";
        //  var chartService = "services/tile/" + id;
        var tileDataThroughService = this.http.get(chartService)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return tileDataThroughService;
    }
    getNewServiceJSON(username, password): any {

        var daveService = "./app/resources/json/dave.json";
        var userService = "./app/resources/json/newUserDetail.json";
        var pieChartService = "" //"./app/resources/json/testPieChart.json";
        //   var cleanDaveService = "./app/resources/json/cleanDave.json";      
        //   var mikeService = "./app/resources/json/mike.json";
        //  http://localhost:9090/imiservices/services/userprofile?id=Dave&key=password    



        // var params= "id="+ username + "&key=" + password;

        var serviceurl = "services/userprofile"//?id="+ username + "&key=" + password;  

        var body = "id=" + username + "&key=" + password;
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

       // var tileDataThroughService = this.http.post(serviceurl, body, { headers: headers })

            //var tileDataThroughService = this.http.post(serviceurl, {username, password})
               var tileDataThroughService = this.http.get(userService)
            .map((response: Response) =>
                response.json()
            //console.log("response: " + response.json())
            )
            .catch(this.handleError);

        return tileDataThroughService;


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