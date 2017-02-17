import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class BodyService {
    private getUserServiceUrl: string = './app/resources/json/newUserDetail.json';
    private getBaseServiceUrl: string = 'services/userprofile';
   // private getNumberOfTilesServiceUrl: string = "services/notile";
      private getNumberOfTilesServiceUrl: string = "./app/resources/json/notiles.json";


     private validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
    
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
        var headers = new Headers();
        headers.append('Authorization', this.validToken);
        return this.http.get(this.getNumberOfTilesServiceUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getTilteJson(id) {
        console.log("this.validToken: "+this.validToken);
        // alert("token from sessionstorage" + this.validToken);
        var headers = new Headers();
        headers.append('Authorization', this.validToken);

          var tileService = "./app/resources/json/" + id + "-tile.json";
       // var tileService = "services/tile/" + id;
        return this.http.get(tileService, { headers }) //headers should be in object
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getChartJson(id) {
        var headers = new Headers();
        headers.append('Authorization', this.validToken);
        var chartService = "./app/resources/json/" + id + "-chart.json";
      //  var chartService = "services/tile/" + id;
        return this.http.get(chartService, { headers })
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